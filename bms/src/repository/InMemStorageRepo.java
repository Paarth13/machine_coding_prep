package repository;

import models.Screen;
import models.Seat;
import models.SeatStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemStorageRepo implements IStorageRepo{

    Map<String, List<Screen>> theatres;

    public InMemStorageRepo() {
        this.theatres = new HashMap<>();
    }

    public void addTheatres(String name,List<Screen> screens)
    {
        this.theatres.put(name,screens);
    }

    public void selectSeats(List<Seat> seats)
    {
        for(Seat s: seats)
        {
            s.SeatType = SeatStatus.TEMPORARY_UNAVAILABLE;
        }
    }
    public void 
}
