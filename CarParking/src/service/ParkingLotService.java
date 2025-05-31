package service;

import models.Car;
import models.HatchBackCars;
import models.SUVCars;
import repository.IParkingLotRepo;

public class ParkingLotService {
    IParkingLotRepo parkingLotRepo;

    public ParkingLotService(IParkingLotRepo parkingLotRepo) {
        this.parkingLotRepo = parkingLotRepo;
    }

    public Boolean addCar(Car car) {
        return parkingLotRepo.addCar(car);
    }


    public Integer exitCar(Car car) {
        return parkingLotRepo.exitCar(car);
    }
}
