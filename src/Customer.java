
import java.util.ArrayList;

public class Customer extends User implements Register{
    public Customer() {
        this.rideNotifications = new ArrayList<>() ;
    }

    public ArrayList<Ride> rideNotifications;

    public void register(User user) {
        String message= Controller.GetInstance().accountCheck(user);
        if (message.equals("")){
            user.setStatus("Registered");
            Controller.GetInstance().users.add(user);
        }else{
            System.out.println(message);
        }
    }


    public void requestRide(String src, String dest, Customer customer) {
        Ride ride = new Ride(src, dest, customer);
        db.insertRide(ride, rideNotifications);
        ride.notifyDrivers(ride);
    }

    public ArrayList<Ride> getRideNotifications() {
        return rideNotifications;
    }
}
