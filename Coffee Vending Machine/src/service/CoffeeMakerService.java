package service;

import models.Coffee;
import repository.IPaymentStrategy;
import repository.IRepository;

import java.lang.classfile.attribute.CodeAttribute;

public class CoffeeMakerService {
    IPaymentStrategy paymentStrategy;
    IRepository repository;

    public CoffeeMakerService(IPaymentStrategy paymentStrategy, IRepository repository) {
        this.paymentStrategy = paymentStrategy;
        this.repository = repository;
    }

    public void prepareCoffee(Coffee coffee)
    {
        if(!repository.checkForItems(coffee))
        {
            System.out.println("Items Not Available");
            return;
        }
        System.out.println("Please pay the below mentioned cost");
        System.out.println(paymentStrategy.calculateCoffeePrice(coffee));
    }
    public void dispenseCoffee(int payment, Coffee coffee)
    {
      repository.updateItems(coffee);

    }
}
