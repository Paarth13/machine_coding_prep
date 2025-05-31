import model.TrackingObject;
import repository.IPendencyRepository;
import repository.InMemoryPendencyRepo;
import service.IPendencySystemService;
import service.PendencyService;

import java.util.ArrayList;
import java.util.List;

// Assumptions made
// No to trackings would have same id

public class Main {
    public static void main(String[] args) {

        IPendencyRepository pendencyRepository =  new InMemoryPendencyRepo();
        IPendencySystemService pendencySystemService = new PendencyService(pendencyRepository);
        TrackingObject trackingObject1 = new TrackingObject(1112, new ArrayList<>(List.of("UPI", "Karnataka", "Bangalore")));
        TrackingObject trackingObject2 = new TrackingObject(2451, new ArrayList<>(List.of("UPI", "Karnataka", "Mysore")));
        TrackingObject trackingObject3 = new TrackingObject(3421, new ArrayList<>(List.of("UPI", "Rajasthan", "Jaipur")));
        TrackingObject trackingObject4 = new TrackingObject(1221, new ArrayList<>(List.of("Wallet", "Karnataka", "Bangalore")));
        TrackingObject trackingObjectEmptyHeirarachy = new TrackingObject(13, new ArrayList<>());
        pendencySystemService.startTracking(trackingObject1);
        pendencySystemService.startTracking(trackingObject2);
        pendencySystemService.startTracking(trackingObject3);
        pendencySystemService.startTracking(trackingObject4);
        pendencySystemService.startTracking(trackingObjectEmptyHeirarachy);
        pendencySystemService.getCount(new ArrayList<>(List.of("UPI")));
        pendencySystemService.getCount(new ArrayList<>(List.of("UPI", "Karnataka")));
        pendencySystemService.getCount(new ArrayList<>(List.of("UPI", "Karnataka", "Bangalore")));
        pendencySystemService.getCount((new ArrayList<>(List.of("Bangalore"))));
        pendencySystemService.endTracking(1112);
        pendencySystemService.endTracking(2451);
        // Test for invalid key
        pendencySystemService.endTracking(2451);
        TrackingObject trackingObject5 = new TrackingObject(4221, new ArrayList<>(List.of("Wallet", "Karnataka", "Bangalore")));
        pendencySystemService.startTracking(trackingObject5);
        pendencySystemService.getCount(new ArrayList<>(List.of("UPI")));
        pendencySystemService.getCount(new ArrayList<>(List.of("Wallet")));

        pendencySystemService.getCount(new ArrayList<>(List.of("UPI", "Karnataka")));
    }
}
/**
 * Test Case mentioned in question. Also added a check for ending task not present in list Line 33
 * startTracking(1112, ["UPI", "Karnataka", "Bangalore"]);
 * startTracking(2451, ["UPI", "Karnataka", "Mysore"]);
 * startTracking(3421, ["UPI", "Rajasthan", "Jaipur"]);
 * startTracking(1221, ["Wallet", "Karnataka", "Bangalore"]);
 *
 * HashMap<name,node></name,node>
 *
 * clas Node{
 *     String value,
 * int count,
 * HashMap<Name,Node> children}
 *
 * getCounts(["UPI"])   # represents the counts of all pending "UPI" transactions
 * Output: 3
 * getCounts(["UPI", "Karnataka"])  # represents the counts of all pending "UPI" transactions in "Karnataka"
 * Output: 2
 * getCounts(["UPI", "Karnataka", "Bangalore"]) # represents the counts of all pending "UPI" transactions in "Karnataka" and "Bangalore"
 * Output: 1
 *
 * getCounts(["Bangalore"]) # Does not represent a valid hierarchy in the sample
 * Output: 0
 *
 * startTracking(4221, ["Wallet", "Karnataka", "Bangalore"]);
 * stopTracking(1112);
 * stopTracking(2451);
 *
 * getCounts(["UPI"])
 * Output: 1
 * getCounts(["Wallet"])
 * Output: 2
 * getCounts(["UPI", "Karnataka", "Bangalore"])
 * Output: 0
 *
 *
 **/