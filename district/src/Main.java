import models.Show;
import models.Ticket;
import models.User;
import repository.IBookingRepo;
import repository.InMemBookingRepo;
import service.BookingService;
import service.IBookingService;

public class Main {
    public static void main(String[] args) {

        IBookingRepo  bookingRepo = new InMemBookingRepo();
        IBookingService bookingService =  new BookingService(bookingRepo);

        User u1 = new User("Paarth","1");
        User u2 =  new User("Neha", "2");

        Show chhava = new Show(10,"Chhava");
        Show Sam = new Show(10,"Sam");

        Ticket t1 = bookingService.bookingIntent(chhava.getShowName(),5,u1.getId());
        Ticket t2 = bookingService.bookingIntent(chhava.getShowName(),5,u1.getId());

        bookingService.getShowDetails(chhava.getShowName());

        bookingService.bookTicket(t1);
        bookingService.getShowDetails(chhava.getShowName());
        bookingService.getUserTicket(u1.getId());

        System.out.println("Hello, World!");
    }
}