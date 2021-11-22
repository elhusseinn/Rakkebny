import java.util.ArrayList;

public class Driver extends User{
    private String NationalID;
    private String drivingLiscence;
    private enum status{
        Pending,Registered,Suspended
    }
    private ArrayList<Ride>notifications = new ArrayList<Ride>();
    private ArrayList<String>favouritePlaces = new ArrayList<String>();

    public String getNationalID() {
        return NationalID;
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

    public ArrayList<Ride> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Ride> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getFavouritePlaces() {
        return favouritePlaces;
    }

    public void setFavouritePlaces(ArrayList<String> favouritePlaces) {
        this.favouritePlaces = favouritePlaces;
    }

    public void addFavourites(String favourites){
        favouritePlaces.add(favourites);
    }

    public void listRides(){

    }

    public void makeOffer(Customer customer,double offer){

    }

    @Override
    public void register(String name , String email,String password,String phoneNumber) {
        this.setEmailAddress(email);
        this.setUserName(name);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
    }
}
