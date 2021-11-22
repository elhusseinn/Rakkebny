import java.util.ArrayList;
import java.util.Scanner;

public class Driver extends User{
    private String NationalID;
    private String drivingLiscence;

    @Override
    public Driver login() {
        String username;
        String password;
        Driver driver=new Driver();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter a user name: ");
            username = scanner.nextLine();
            driver.setUserName(username);
            System.out.println("Please enter a password: ");
            password = scanner.nextLine();
            driver.setPassword(password);
        } catch (Exception e) {
            System.out.println("Please try again! ");
        }
        return driver;    }

    @Override
    public Driver register() {
        Driver driver = new Driver();
        Scanner scanner = new Scanner(System.in);
        String name;
        String email;
        String password;
        String phoneNo;
        String NationalID;
        String drivingLiscence;
        try {
            System.out.println("Please enter a user name: ");
            name = scanner.nextLine();
            driver.setUserName(name);
            System.out.println("Please enter an email: ");
            email = scanner.nextLine();
            driver.setEmailAddress(email);
            System.out.println("Please enter a phone number: ");
            phoneNo = scanner.nextLine();
            driver.setPhoneNumber(phoneNo);
            System.out.println("Please enter a password: ");
            password = scanner.nextLine();
            driver.setPassword(password);
            System.out.println("Please enter your nationalID: ");
            NationalID = scanner.nextLine();
            driver.setNationalID(NationalID);
            System.out.println("Please enter a drivingLiscence: ");
            drivingLiscence= scanner.nextLine();
            driver.setNationalID(drivingLiscence);
        } catch (Exception e) {
            System.out.println("Please try again!");
        }
        return driver;    }

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
/*
    @Override
    public void register(String name , String email,String password,String phoneNumber) {
        this.setEmailAddress(email);
        this.setUserName(name);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
    }*/
}
