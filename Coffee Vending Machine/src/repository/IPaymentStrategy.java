package repository;

import models.Coffee;

public interface IPaymentStrategy {
    public int calculateCoffeePrice(Coffee coffee);
}
