package repository;

import models.Seat;
import models.Show;
import models.Ticket;

import java.util.List;

public interface IBookingRepo {
    public boolean bookingIntent(Ticket ticket);
    public boolean bookTicket(Ticket ticket);
    public List<Seat> getAvailableSeats(String show);
    public List<Show> getShowDetails(String showId);
    public  Ticket getUserTicket(String userId);
}
