package models;

public abstract class Car {
    String name;
    Integer price;
    long entryTime;
    long exitTime;
    Integer id;

    public Car(String name, Integer price, long entryTime, long exitTime, Integer id) {
        this.name = name;
        this.price = price;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.id =  id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
