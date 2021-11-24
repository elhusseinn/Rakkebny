
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User implements Register{
    public Customer() {
        this.rideNotifications = new ArrayList<>() ;
    }

    public ArrayList<Ride> rideNotifications;

    public void register(User user) {
        String message= system.GetInstance().accountCheck(user);
        if (message.equals("")){
            user.setStatus(status.Registered);
            system.GetInstance().users.add(user);
        }else{
            System.out.println(message);
        }
    }


    public void requestRide(String src, String dest, Customer customer) {
        Ride ride = new Ride();
        ride.setCustomer(customer);
        ride.setSource(src);
        ride.setDestination(dest);
        ride.notifyDrivers(ride);
    }

    public void getRideNotifications() {
        for (int i=0;i<rideNotifications.size();i++){
            System.out.println(i+1+"-"+rideNotifications.get(i).toString());
        }
    }
}
