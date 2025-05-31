package model;

import java.time.Instant;

public class Vehicle {
    String name;
    VehicleType type;
    Instant entryTime;
    Integer slot;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Instant getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Instant entryTime) {
        this.entryTime = entryTime;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Integer level;

    public Vehicle(String name, VehicleType type, Instant entryTime) {
        this.name = name;
        this.type = type;
        this.entryTime = entryTime;
    }

    public Vehicle(String name, VehicleType type, Instant entryTime, Integer level) {
        this.name = name;
        this.type = type;
        this.entryTime = entryTime;
        this.level = level;
    }
}
