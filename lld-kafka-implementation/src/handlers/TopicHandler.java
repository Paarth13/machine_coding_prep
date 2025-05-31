package handlers;

import models.ISubscriber;
import models.Subscriber;
import models.Topic;
import workers.SubscriberWorker;

import java.util.HashMap;

public class TopicHandler {
    private HashMap<String, SubscriberWorker> subscriberWorkers;
    private Topic topic;
    public TopicHandler(Topic topic) {
        this.topic  = topic;
        this.subscriberWorkers = new HashMap<>();
    }

    public void publish()
    {
        for(ISubscriber sub : topic.getSubscriberList())
        {
            startWorkers(sub);
        }
    }

    public void startWorkers(ISubscriber subscriber)
    {
        final String subId =  subscriber.getId();
        if(!subscriberWorkers.containsKey(subId))
        {
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic, subscriber);
            subscriberWorkers.put(subId,new SubscriberWorker(topic,subscriber));
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subscriberWorkers.get(subId);
        subscriberWorker.wakeUpIfNeeded();
    }

}
