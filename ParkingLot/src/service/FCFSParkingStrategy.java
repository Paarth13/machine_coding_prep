package service;

import model.Level;
import model.Vehicle;
import model.VehicleType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FCFSParkingStrategy implements  IParkingStrategy{
    @Override
    public void applyParkingStrategy(Vehicle vehicle, HashMap<Integer, Level> levelInfo) {

        if(vehicle.getType().toString().equals(VehicleType.BUS.toString()))
        {
            for (Map.Entry<Integer, Level> mapElement : levelInfo.entrySet()) {
                if(!mapElement.getValue().isBusesFull()) {
                    vehicle.setSlot(mapElement.getValue().getBuses());
                    vehicle.setLevel(mapElement.getKey());
                    mapElement.getValue().setBuses(mapElement.getValue().getBuses()+1);
                    break;
                }
            }
        }
        else if (vehicle.getType().toString().equals(VehicleType.CAR.toString()))
        {
            for (Map.Entry<Integer,Level> mapElement : levelInfo.entrySet()) {
                if (!mapElement.getValue().isCarsFull()) {
                    vehicle.setLevel(mapElement.getKey());
                    vehicle.setSlot(mapElement.getValue().getCars());
                    mapElement.getValue().setCars(mapElement.getValue().getCars()+1);
                    break;
                }
            }
        }
        else if (vehicle.getType().toString().equals(VehicleType.BIKE.toString()))
        {
            for (Map.Entry<Integer,Level> mapElement : levelInfo.entrySet()) {
                if(!mapElement.getValue().isBikesFull()) {
                    vehicle.setLevel(mapElement.getKey());
                    vehicle.setSlot(mapElement.getValue().getBikes());
                    mapElement.getValue().setBikes(mapElement.getValue().getBikes()+1);
                    break;
                }
            }
        }

    }
}
