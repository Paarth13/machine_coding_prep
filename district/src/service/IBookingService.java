package service;

import models.Seat;
import models.Show;
import models.Ticket;

import java.util.List;

public interface IBookingService {
    public Ticket bookingIntent(String showId, int seatNumber, String userid );
    public void bookTicket(Ticket ticket);
    public void getAvailableSeats(String showId);
    public void getShowDetails(String showId);
    public Ticket getUserTicket(String userId);
}
