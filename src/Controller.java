import java.util.ArrayList;

public class Controller extends DataBase implements rideFeatures {

    private static Controller instance = null;
    SQLiteJDBC db = new SQLiteJDBC();

    private Controller() {
    }

    public static Controller GetInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;

    }

    @Override
    public ArrayList<Driver> searchDriver(String place) { // returns array of drivers that has place in their favourite
        ArrayList<Driver> drivers = new ArrayList<>();
        db.getPlaceDrivers(drivers, place);
        return drivers;
    }


    public User Authenticate(User user) { // check instance of->
        String name = user.getUserName();
        String lol = "registered";

        if (user instanceof Customer) {
            if (db.getCustomer(name).getPassword().equals(user.getPassword()) && db.getCustomer(name).getStatus().equals("Registered")) {
                return user;
            } else {
                System.out.println("Credentials doesn't match");
                return null;
            }
        } else if (user instanceof Driver) {
            if (db.getDriver(name).getPassword().equals(user.getPassword()) && db.getDriver(name).getStatus().equals("Registered")) {
                return user;
            } else {
                System.out.println("Credentials doesn't match");
                return null;
            }

        } else if (user instanceof Admin) {
            if (db.getAdmin(name).getPassword().equals(user.getPassword())) {
                return user;
            } else {
                System.out.println("Credentials doesn't match");
                return null;
            }
        } else {
            System.out.println("Credentials doesn't match");
            return null;
        }


    }


    public String accountCheck(User user) {
       /* if (user.getStatus().equalsIgnoreCase("suspended")){
            return "you are suspended";
        }*/

        for (int i = 0; i < users.size(); i++) {
            if (user.getUserName().equalsIgnoreCase(users.get(i).getUserName())) {
                return "Error User name already exists, user name must be unique";
            }
        }
        return "";
    }

    public void addDriver(Driver driver) {
        pendingDrivers.add(driver);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addCompletedRides(Ride ride) {
        completedRides.add(ride);
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }


}
