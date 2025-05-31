package parkingService;

import model.Level;
import model.Vehicle;
import model.VehicleType;
import service.IParkingStrategy;
import service.IPaymentStrategy;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ParkingService {

    HashMap<String,Vehicle> vehicles;
    HashMap<Integer, Level> levelInfo;
    IParkingStrategy parkingStrategy;
    IPaymentStrategy paymentStrategy;
    public ParkingService(IParkingStrategy parkingStrategy, IPaymentStrategy paymentStrategy) {
        this.vehicles = new HashMap<>();
        this.levelInfo = new HashMap<>();
        this.parkingStrategy = parkingStrategy;
        this.paymentStrategy =  paymentStrategy;
    }

    public String parkVehicle(Vehicle vehicle)
    {
        if(!levelInfo.isEmpty() && checkIfThereIsAnySpace(vehicle.getType().toString()))
        {
            parkingStrategy.applyParkingStrategy(vehicle,levelInfo);
            vehicles.put(vehicle.getName(),vehicle);
            return "Vehicle name " + vehicle.getName() + "parked on level "+
                    vehicle.getLevel() + "in slot " + vehicle.getSlot();
        }
        return "Invalid Operation as there is no space present";
    }
    public String exitFromParking(String name)
    {
        if(!vehicles.isEmpty() && vehicles.containsKey(name))
        {
            long cost = paymentStrategy.calculatePayment(vehicles.get(name));

            String resp = vehicles.get(name).getName() + " needs to pay " + cost;
            removeVehicle(vehicles.get(name));
            return resp;
        }
        return "Vehicle not present";
    }
    public String addLevel(Integer levelCount, List<Integer> slots)
    {
         levelInfo.put(levelCount,new Level(slots.get(0),slots.get(1),slots.get(2)));
         return "Level Added";
    }

    public String status()
    {
        String ans = "";
        for (Map.Entry<Integer,Level> mapElement : levelInfo.entrySet())
        {
            String s = "Level "+ mapElement.getKey() +
                    " buses "+ mapElement.getValue().getBuses() + "/" +mapElement.getValue().getMaxBuses() +
            "\n " + " bikes "+ mapElement.getValue().getBikes() + "/" +mapElement.getValue().getMaxbikes() +
                    "\n " +
            " Cars "+ mapElement.getValue().getCars() + "/" +mapElement.getValue().getMaxCars() +
                    "\n ";
            ans+=s;
        }
        return ans;
    }

    private void  removeVehicle(Vehicle vehicle)
    {
        if(vehicle.getType().toString().equals(VehicleType.CAR.toString()))
        {
            levelInfo.get(vehicle.getLevel()).setCars(levelInfo.get(vehicle.getLevel()).getCars() -1);
        }
        else if(vehicle.getType().toString().equals(VehicleType.BUS.toString()))
        {
            levelInfo.get(vehicle.getLevel()).setBuses(levelInfo.get(vehicle.getLevel()).getBuses() -1);
        }
        else if(vehicle.getType().toString().equals(VehicleType.BIKE.toString()))
        {
            levelInfo.get(vehicle.getLevel()).setBikes(levelInfo.get(vehicle.getLevel()).getBikes() -1);
        }
        vehicles.remove(vehicle.getName());
    }

    private Boolean checkIfThereIsAnySpace(String type)
    {
        if(type.equals(VehicleType.BUS.toString()))
        {
            for (Map.Entry<Integer,Level> mapElement : levelInfo.entrySet()) {
                if(!mapElement.getValue().isBusesFull()) {
                    return true;
                }
            }
        }
        else if (type.equals(VehicleType.CAR.toString()))
        {
            for (Map.Entry<Integer,Level> mapElement : levelInfo.entrySet()) {
                if (!mapElement.getValue().isCarsFull()) {
                    return true;
                }
            }
        }
        else if (type.equals(VehicleType.BIKE.toString()))
        {
            for (Map.Entry<Integer,Level> mapElement : levelInfo.entrySet()) {
                if(!mapElement.getValue().isBikesFull()) {
                    return true;
                }
            }
        }
        return false;
    }
}
