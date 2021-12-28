
import java.util.ArrayList;
import java.util.Date;

public class Customer extends User implements Register{
    public Customer() {
        this.rideNotifications = new ArrayList<>() ;
    }

    public ArrayList<Ride> rideNotifications;
    private Date BirthDate;

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public void register(User user) {
        db.insertCustomer(user.getUserName(), user.getEmailAddress(), user.getPassword(), user.getPhoneNumber(),getBirthDate());
    }


    public void requestRide(Ride ride) {

         db.insertRide(ride);
        ride.notifyDrivers(ride);
    }

    public ArrayList<Ride> getRideNotifications() {
        return rideNotifications;
    }
}
