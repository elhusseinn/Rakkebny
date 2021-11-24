import java.util.ArrayList;


public class Driver extends User implements Register,Notifications,DriverFeatures {
    private String NationalID;
    private String drivingLiscence;
    private Rate rate;
    private ArrayList<Rate> rates = new ArrayList<Rate>();
    private ArrayList<Ride> notifications = new ArrayList<Ride>();
    private ArrayList<String> favouritePlaces = new ArrayList<String>();

    public String getNationalID() {
        return NationalID;
    }

    public Driver() {
        this.rate = new Rate();
        this.setStatus(status.Pending);
    }
    public void addNotification(Ride ride){
        notifications.add(ride);
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

    public void setNotifications(ArrayList<Ride> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getFavouritePlaces() {

        return favouritePlaces;
    }

    public void setFavouritePlaces(String place) {
        favouritePlaces.add(place);
    }

    public void makeOffer(Ride ride, double offer) {
        ride.setCost(offer);
    }



    public void register(User user) {
        String message = system.GetInstance().accountCheck(user);
        if (message.equals("")) {
            user.setStatus(status.Pending);
             system.GetInstance().addDriver((Driver) user);

        } else {
            System.out.println("Something wrong in Driver registration");
        }
    }

    @Override
    public void showAverageRating() {
        System.out.println(rate.displayRating());
    }

    public ArrayList<Ride> getNotifications() {
        return notifications;
    }
    public boolean notificationsArray(){
        if (notifications.isEmpty()){
            return true;
        }else{
            for (int i=0;i<notifications.size();i++){
                System.out.println(i+1+"-"+ notifications.get(i));
            }
            return false;
        }
    }



    public void listRides() {
        for (Ride ride : notifications) {
            System.out.println(ride.getRideReceipt().printRideReceipt_());
        }
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }


    @Override
    public void notifyCustomer(Ride ride) {

        ride.getCustomer().rideNotifications.add(ride);
    }
}