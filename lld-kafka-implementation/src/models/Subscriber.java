package models;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;


public class Subscriber implements ISubscriber{
    private final String id;
    private final AtomicInteger offset;
    private final int sleepTimeInMillis;
    public Subscriber(String id, int sleepTimeInMillis) {
        this.id = id;
        this.offset = new AtomicInteger(0);
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public AtomicInteger getOffset() {
        return  offset;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Messages message) throws InterruptedException {
        System.out.println("Subscriber: " + id + " started consuming: " + message.getMessage());
        Thread.sleep(sleepTimeInMillis);
        System.out.println("Subscriber: " + id + " done consuming: " + message.getMessage());
    }
}
