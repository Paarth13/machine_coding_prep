package models;

public class Seat {
    int seatId;
    SeatStatus status;

    public Seat(int seatId) {
        this.seatId = seatId;
        this.status = SeatStatus.OPEN;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public boolean isBooked()
    {
        return this.status == SeatStatus.BOOKED;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
