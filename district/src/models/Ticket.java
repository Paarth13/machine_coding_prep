package models;

public class Ticket {
    String showId;
    int seatNumber;
    String userId;
    TicketStatus status;

    public String getShowId() {
        return showId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getUserId() {
        return userId;
    }

    public Ticket(String showId, int seatNumber, String userId) {
        this.showId = showId;
        this.seatNumber = seatNumber;
        this.userId = userId;
        this.status = TicketStatus.IN_PROGRESS;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
