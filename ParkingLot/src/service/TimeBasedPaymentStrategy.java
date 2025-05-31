package service;

import model.Vehicle;

import java.time.Instant;

public class TimeBasedPaymentStrategy implements  IPaymentStrategy{
    @Override
    public long calculatePayment(Vehicle vehicle) {
        return (Instant.now().getEpochSecond() - vehicle.getEntryTime().getEpochSecond())*10;
    }
}
