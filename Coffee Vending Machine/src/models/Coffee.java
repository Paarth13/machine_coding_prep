package models;

public abstract class Coffee {
    int coffee;
    int milk;
    int sugar;

    public Coffee(int coffee, int milk, int sugar) {
        this.coffee = coffee;
        this.milk = milk;
        this.sugar = sugar;
    }
}
