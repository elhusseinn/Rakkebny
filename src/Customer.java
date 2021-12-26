
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
        db.insertRide(ride);
        ride.notifyDrivers(ride);
    }

    public void getRideNotifications() {
        for (int i=0;i<rideNotifications.size();i++){
            System.out.println(i+1+"-"+rideNotifications.get(i).toString());
        }
    }
}
