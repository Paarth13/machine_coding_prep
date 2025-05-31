package models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    String topicId;
    String topicName;
    private final List<ISubscriber> subscriberList;
    private final List<Messages> topicMessages;

    public Topic(String topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.subscriberList =  new ArrayList<>();
        this.topicMessages =  new ArrayList<>();
    }

    public synchronized void addMessage(Messages message)
    {
        this.topicMessages.add(message);
    }

    public void addSubsribers(ISubscriber subscriber)
    {
        this.subscriberList.add(subscriber);
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<ISubscriber> getSubscriberList() {
        return subscriberList;
    }

    public List<Messages> getTopicMessages() {
        return topicMessages;
    }
}
