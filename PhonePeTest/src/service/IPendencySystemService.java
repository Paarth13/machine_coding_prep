package service;

import model.TrackingObject;

import java.util.List;

public interface IPendencySystemService {
    public int getCount(List<String> heirarchy);
    void startTracking(TrackingObject trackingObject);
    void endTracking(int id);
}
