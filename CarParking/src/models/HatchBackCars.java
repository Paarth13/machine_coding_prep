package models;

import java.sql.Time;
import java.sql.Timestamp;

public class HatchBackCars extends Car{

    public HatchBackCars(String name, Integer id) {
        super(name,10, System.currentTimeMillis(),0,id);
    }

}
