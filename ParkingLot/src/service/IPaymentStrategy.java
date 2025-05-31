package service;

import model.Vehicle;

public interface IPaymentStrategy {
    public long calculatePayment(Vehicle vehicle);
}
