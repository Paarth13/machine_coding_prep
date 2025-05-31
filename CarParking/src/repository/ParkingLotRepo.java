package repository;

import models.Car;
import models.HatchBackCars;
import models.SUVCars;

import java.util.HashMap;


public class ParkingLotRepo implements IParkingLotRepo{
    HashMap<Integer,HatchBackCars> hbCars;
    HashMap<Integer,SUVCars> suvCars;
    Integer totalCars;
    Integer id;
    Integer offset;

    public ParkingLotRepo() {
        this.hbCars = new HashMap<>();
        this.suvCars = new HashMap<>();
        this.totalCars = 20;
        this.id = 0;
        this.offset = 0;
    }


    @Override
    public Integer getSUVCount() {
        return  suvCars.size();
    }

    @Override
    public Integer getHachBackCount() {
        return hbCars.size();
    }

    @Override
    public Integer getTotalCarCount() {
        return hbCars.size() + suvCars.size();
    }

    @Override
    public Integer getAmountForCar(Car exitCar) {
        return (int)((System.currentTimeMillis() - exitCar.getEntryTime()) * exitCar.getPrice());
    }

    @Override
    public Boolean addCar(Car car) {
        Integer carCount  = getTotalCarCount();
        if(totalCars <= carCount)
            return false;
        if( car instanceof SUVCars && suvCars.size()+offset <= totalCars/2)
        {
            car.setId(id);
            suvCars.put(id,(SUVCars) car);
            id++;
        }
        else if (car instanceof HatchBackCars)
        {
            if(hbCars.size() >= totalCars/2 && suvCars.size()< totalCars/2)
            {
               offset++;
               car.setId(id);
                hbCars.put(id,(HatchBackCars) car);
            }
            else if( hbCars.size() < totalCars/2)
            {
                car.setId(id);
                hbCars.put(id,(HatchBackCars) car);
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer exitCar(Car car) {
        Integer amount = getAmountForCar(car);
        if(car instanceof HatchBackCars)
        {
            if(hbCars.size() > totalCars/2)
            {
                offset--;
            }
            hbCars.remove(car.getId());
        } else if (car instanceof SUVCars) {
            suvCars.remove(car.getId());
        }
        return amount;
    }
}
