package service;

import models.Seat;
import models.Show;
import models.Ticket;
import repository.IBookingRepo;

import java.util.List;

public class BookingService implements  IBookingService{
    IBookingRepo bookingRepo;

    public BookingService(IBookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    @Override
    public Ticket bookingIntent(String showId, int seatNumber, String userid ) {
        Ticket ticket = new Ticket(showId,seatNumber,userid);
        if (bookingRepo.bookingIntent(ticket))
        {
            return ticket;
        }
        return null;
    }

    @Override
    public void bookTicket(Ticket ticket) {
        bookingRepo.bookTicket(ticket);

    }

    @Override
    public void getAvailableSeats(String showId) {
        for (Seat seat: bookingRepo.getAvailableSeats(showId))
        {
            if(!seat.isBooked())
            {
                System.out.println("O");
            }
            else
            {
                System.out.println("x");
            }
        }
    }

    @Override
    public void getShowDetails(String showId) {
        for(Show show: bookingRepo.getShowDetails(showId))
        {
            System.out.println(show.getShowName());
        }
    }

    @Override
    public Ticket getUserTicket(String userId)
    {
        return bookingRepo.getUserTicket(userId);
    }
}
