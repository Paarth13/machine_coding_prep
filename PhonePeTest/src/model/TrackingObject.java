package model;

import java.util.List;

public class TrackingObject {
    int id;
    List<String> heirarchy;

    public TrackingObject(int id, List<String> heirarchy) {
        this.id = id;
        this.heirarchy = heirarchy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getHeirarchy() {
        return heirarchy;
    }

    public void setHeirarchy(List<String> heirarchy) {
        this.heirarchy = heirarchy;
    }
}
