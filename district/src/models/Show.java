package models;

import java.util.ArrayList;
import java.util.List;

public class Show {
    List<Seat> seats;
    int totalSeats;
    String showName;

    public List<Seat> getSeats() {
        return seats;
    }

    public String getShowName() {
        return showName;
    }

    public Show(int totalSeats, String showName) {
        this.totalSeats = totalSeats;
        updateSeats();
        this.showName = showName;
    }

    private void updateSeats()
    {
        this.seats =  new ArrayList<>();
        int count = 0;
        for (int i =0; i < totalSeats; i++)
        {
            seats.add(new Seat(count));
            count++;
        }
    }
}
