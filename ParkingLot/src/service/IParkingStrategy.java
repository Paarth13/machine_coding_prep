package service;

import model.Level;
import model.Vehicle;

import java.util.HashMap;

public interface IParkingStrategy {
    void applyParkingStrategy(Vehicle vehicle, HashMap<Integer, Level> levelInfo);
}
