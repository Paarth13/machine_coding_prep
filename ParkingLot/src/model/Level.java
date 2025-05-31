package model;

public class Level {
    Integer cars;
    Integer bikes;
    Integer buses;
    Integer maxCars;
    Integer maxBuses;

    public Integer getMaxbikes() {
        return maxbikes;
    }

    public Integer getMaxBuses() {
        return maxBuses;
    }

    public Integer getMaxCars() {
        return maxCars;
    }

    Integer maxbikes;

    public Level(Integer maxCars, Integer maxBuses, Integer maxbikes) {
        this.maxCars = maxCars;
        this.maxBuses = maxBuses;
        this.maxbikes = maxbikes;
        this.cars = 0;
        this.bikes = 0;
        this.buses = 0;
    }

    public Integer getBuses() {
        return buses;
    }

    public void setBuses(Integer buses) {
        this.buses = buses;
    }

    public Integer getBikes() {
        return bikes;
    }

    public void setBikes(Integer bikes) {
        this.bikes = bikes;
    }

    public Integer getCars() {
        return cars;
    }

    public void setCars(Integer cars) {
        this.cars = cars;
    }

    public Boolean isCarsFull()
    {
        return this.cars.equals(this.maxbikes);
    }
    public Boolean isBikesFull()
    {
        return this.bikes.equals(this.maxbikes);
    }
    public Boolean isBusesFull()
    {
        return this.buses.equals(this.maxBuses);
    }
}
