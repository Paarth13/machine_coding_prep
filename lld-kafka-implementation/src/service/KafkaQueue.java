package service;

import handlers.TopicHandler;
import lombok.NonNull;
import models.ISubscriber;
import models.Messages;
import models.Subscriber;
import models.Topic;

import java.util.HashMap;
import java.util.UUID;

public class KafkaQueue {
    private HashMap<String, TopicHandler> topicProcessors;

    public KafkaQueue() {
        this.topicProcessors = new HashMap<>();
    }

    public Topic createTopic(String topicName)
    {
        Topic topic1 =  new Topic(UUID.randomUUID().toString(),topicName);
        TopicHandler topicHandler = new TopicHandler(topic1);
        topicProcessors.put(topic1.getTopicId(),topicHandler);
        System.out.println("Topic was created : " +  topicName);
        return  topic1;
    }

    public void addSubscribers(Topic topic, ISubscriber subscriber)
    {
        topic.addSubsribers(subscriber);
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void publish(@NonNull final Topic topic, @NonNull final Messages message) {
        topic.addMessage(message);
        System.out.println(message.getMessage() + " published to topic: " + topic.getTopicName());
        new Thread(() -> topicProcessors.get(topic.getTopicId()).publish()).start();
    }

    public void resetOffset(@NonNull final Topic topic, @NonNull final ISubscriber subscriber, @NonNull final Integer newOffset) {
        for (ISubscriber topicSubscriber : topic.getSubscriberList()) {
            if (topicSubscriber.equals(subscriber)) {
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(topicSubscriber.getId() + " offset reset to: " + newOffset);
                new Thread(() -> topicProcessors.get(topic.getTopicId()).startWorkers(topicSubscriber)).start();
                break;
            }
        }
    }
}
