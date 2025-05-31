package repository;

import models.Car;

public interface IParkingLotRepo {
    public Integer getSUVCount();
    public Integer getHachBackCount();
    public Integer getTotalCarCount();
    public Integer getAmountForCar(Car exitCar);
    public Boolean addCar(Car car);
    public Integer exitCar(Car car);
}
