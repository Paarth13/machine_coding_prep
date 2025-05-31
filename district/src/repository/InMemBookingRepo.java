package repository;

import models.Seat;
import models.SeatStatus;
import models.Show;
import models.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemBookingRepo implements IBookingRepo{

    Map<String,Ticket> bookedTicketDetails;
    Map<String,Show> shows;

    public InMemBookingRepo() {
        this.bookedTicketDetails = new HashMap<>();
        this.shows = new HashMap<>();
    }


    @Override
    public boolean bookingIntent(Ticket ticket) {
        if(shows.containsKey(ticket.getShowId()))
        {
            List<Seat> seats = shows.get(ticket.getShowId()).getSeats();
            if(!seats.get(ticket.getSeatNumber()-1).isBooked())
            {
                seats.get(ticket.getSeatNumber()-1).setStatus(SeatStatus.LOCKED);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean bookTicket(Ticket ticket) {
        if(shows.containsKey(ticket.getShowId()))
        {
            List<Seat> seats = shows.get(ticket.getShowId()).getSeats();
            if(!seats.get(ticket.getSeatNumber()-1).isBooked())
            {
                seats.get(ticket.getSeatNumber()-1).setStatus(SeatStatus.BOOKED);
                bookedTicketDetails.put(ticket.getUserId(),ticket);
                System.out.println("Tickets booked for "+ ticket.getShowId());
                return  true;
            }
        }
        return  false;
    }

    @Override
    public List<Seat> getAvailableSeats(String showId) {
        if(shows.containsKey(showId)) {
            return shows.get(showId).getSeats();
        }
        return null;
    }

    @Override
    public List<Show> getShowDetails(String showId) {
        return shows.values().stream().toList();
    }

    @Override
    public Ticket getUserTicket(String userId) {
        return bookedTicketDetails.getOrDefault(userId,null);
    }
}
