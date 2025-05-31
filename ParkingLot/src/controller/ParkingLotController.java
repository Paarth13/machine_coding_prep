package controller;

import model.Vehicle;
import model.VehicleType;
import parkingService.ParkingService;
import service.FCFSParkingStrategy;
import service.IParkingStrategy;
import service.IPaymentStrategy;
import service.TimeBasedPaymentStrategy;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotController {
    public  void formParkingLot()
    {
        Vehicle santro = new Vehicle("Santro", VehicleType.BUS, Instant.now());
        Vehicle XUV = new Vehicle("XUV", VehicleType.CAR, Instant.now());
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(santro);
        vehicles.add(XUV);
        IParkingStrategy parkingStrategy =  new FCFSParkingStrategy();
        IPaymentStrategy paymentStrategy = new TimeBasedPaymentStrategy();
        ParkingService parkingService = new ParkingService(parkingStrategy,paymentStrategy);
        System.out.println(parkingService.parkVehicle(XUV));
        List<Integer> slots =  new ArrayList<>();
        slots.add(10);
        slots.add(5);
        slots.add(20);
        System.out.println(parkingService.addLevel(1,slots));
        System.out.println(parkingService.parkVehicle(XUV));
        System.out.println(parkingService.status());
        System.out.println(parkingService.exitFromParking("XUV"));
        System.out.println(parkingService.status());

    }
}
