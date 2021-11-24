import java.util.ArrayList;

public abstract class DataBase {
protected ArrayList <User> users = new ArrayList<User>();
protected ArrayList<Driver> pendingDrivers = new ArrayList<Driver>();
protected ArrayList<User> Suspended = new ArrayList<User>();
protected ArrayList<Ride> completedRides = new ArrayList<Ride>();
public static ArrayList<User> admins = new ArrayList<User>();


}
