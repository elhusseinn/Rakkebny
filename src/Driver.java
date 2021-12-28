import java.util.ArrayList;


public class Driver extends User implements Register {
    private String NationalID;
    private String drivingLiscence;


    public String getNationalID() {
        return NationalID;
    }

    public Driver() {

        this.setStatus("Pending");
    }

    public void setNationalID(String nationalID) {
        NationalID = nationalID;
    }

    public String getDrivingLiscence() {
        return drivingLiscence;
    }

    public void setDrivingLiscence(String drivingLiscence) {
        this.drivingLiscence = drivingLiscence;
    }

    public void setFavouritePlaces(String place) {
        db.insertFavouritePlace(this.getUserName(), place);
    }

    public void makeOffer(Ride ride, double cost) {

        ride.setCost(cost);
        db.insertCustomerNotification(ride);

    }

    public void register(User user) {
            db.insertDriver(user.getUserName(), user.getEmailAddress(), user.getPassword(), user.getPhoneNumber(), getDrivingLiscence(), getNationalID());

    }

    public ArrayList<Ride> getNotifications() {
        return db.getDriverNotification(this);
    }

    public void listPreviousRides() {
        if(db.getDriversRides(this).size() == 0){
            System.out.println("You haven't make any rides yet, CHECK YOUR NOTIFICATIONS!");
        }
        for (int i = 0; i <db.getDriversRides(this).size() ; i++) {
            System.out.println((i+1) + " customerName-> " + db.getDriversRides(this).get(i).getCustomer().getUserName() + ", Source-> " + db.getDriversRides(this).get(i).getSource() +", Destination-> "+ db.getDriversRides(this).get(i).getDestination()+ ", Cost-> "+ db.getDriversRides(this).get(i).getCost() + ", Rate-> " + db.getDriversRides(this).get(i).getRate());
        }
    }

    public double getAverageRating() {
       return db.getDriverAverageRating(this);
    }





}