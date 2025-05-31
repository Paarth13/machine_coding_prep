package workers;

import lombok.NonNull;
import lombok.SneakyThrows;
import models.ISubscriber;
import models.Messages;
import models.Subscriber;
import models.Topic;

public class SubscriberWorker implements Runnable{
    private final Topic topic;
    private final ISubscriber topicSubscriber;

    public SubscriberWorker(@NonNull final Topic topic, @NonNull final ISubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        synchronized (topicSubscriber) {
            do {
                int curOffset = topicSubscriber.getOffset().get();
                while (curOffset >= topic.getTopicMessages().size()) {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {

                    }
                }
                Messages message = topic.getTopicMessages().get(curOffset);
                try {
                    topicSubscriber.consume(message);
                } catch (InterruptedException e) {
                }

                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                topicSubscriber.getOffset().compareAndSet(curOffset, curOffset + 1);
            } while (true);
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
