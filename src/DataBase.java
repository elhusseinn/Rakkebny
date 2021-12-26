import java.util.ArrayList;
import java.sql.*;

public abstract class DataBase {
    protected ArrayList <User> users = new ArrayList<User>();
    protected ArrayList<Driver> pendingDrivers = new ArrayList<Driver>();//mlha4 alzma
    protected ArrayList<User> Suspended = new ArrayList<User>();//same
    protected ArrayList<Ride> completedRides = new ArrayList<Ride>();
    public static ArrayList<User> admins = new ArrayList<User>();


}
