import models.ISubscriber;
import models.Messages;
import models.Subscriber;
import models.Topic;
import service.KafkaQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final KafkaQueue queue = new KafkaQueue();
        final Topic topic1 = queue.createTopic("t1");
        final Topic topic2 = queue.createTopic("t2");
        final ISubscriber sub1 = new Subscriber("sub1", 1000);
        final ISubscriber sub2 = new Subscriber("sub2", 1000);
        queue.addSubscribers(topic1,sub1);
        queue.addSubscribers(topic1,sub2);

        final ISubscriber sub3 = new Subscriber("sub3", 1000);
        queue.addSubscribers(topic2,sub3);

        queue.publish(topic1, new Messages("m1"));
        queue.publish(topic1, new Messages("m2"));

        queue.publish(topic2, new Messages("m3"));

        Thread.sleep(1500);
        queue.publish(topic2, new Messages("m4"));
        queue.publish(topic1, new Messages("m5"));

        queue.resetOffset(topic1, sub1, 0);
    }
}