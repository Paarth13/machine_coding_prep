package models;

import java.util.concurrent.atomic.AtomicInteger;

public interface ISubscriber {
    AtomicInteger getOffset();

    String getId();
    void consume(Messages message) throws InterruptedException;
}
