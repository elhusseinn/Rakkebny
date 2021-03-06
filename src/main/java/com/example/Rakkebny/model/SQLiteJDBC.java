package com.example.Rakkebny.model;

import com.example.Rakkebny.controller.Customer;
import com.example.Rakkebny.controller.Ride;
import com.example.Rakkebny.controller.User;
import com.example.Rakkebny.controller.Driver;
import java.sql.*;
import java.util.ArrayList;
public class SQLiteJDBC {
    controllerDBManager controllerDBManager = new controllerDBManager();
    DriverDBManager driverDBManager = new DriverDBManager();

    private void CreateCustomerTable() {
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE Customer " +
                    "(userName TEXT PRIMARY KEY     NOT NULL," +
                    " emailAddress         TEXT    NOT NULL, " +
                    " password            TEXT     NOT NULL, " +
                    " phoneNumber        TEXT  NOT NULL, " +
                    " BirthDate       DATE , " +
                    " status             TEXT " +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void CreateDriverTable() {
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE Driver " +
                    "(userName TEXT PRIMARY KEY NOT NULL," +
                    " emailAddress       TEXT   NOT NULL, " +
                    " password           TEXT   NOT NULL, " +
                    " phoneNumber        TEXT   NOT NULL, " +
                    " drivingLiscence    TEXT   NOT NULL," +
                    " nationalID         TEXT   NOT NULL," +
                    " averageRating         integer   ," +
                    " rideStatus         Text   ," +
                    "status              TEXT  " +

                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void CreateAdminTable() {
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            stmt = c.createStatement();
            String sql = "CREATE TABLE Admin " +
                    "(userName CHAR(50) PRIMARY KEY     NOT NULL," +
                    " emailAddress         TEXT    NOT NULL, " +
                    " password            TEXT     NOT NULL, " +
                    " phoneNumber        CHAR(50)  NOT NULL " +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void CreateRideTable() {
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE Ride " +
                    "(RideID         INTEGER PRIMARY key AUTOINCREMENT, " +
                    "customerName TEXT   NOT NULL," +
                    " source         TEXT    NOT NULL, " +
                    " destination           TEXT     NOT NULL, " +
                    " cost        TEXT , " +
                    " driverName           TEXT   ," +
                    " rate             INTEGER ,"+
                    " noOfPassengers             INTEGER ,"+
                    "foreign key (driverName) REFERENCES Driver (userName)" +
                    "foreign key (customerName) REFERENCES Customer (userName) " +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void createFavouritePlacesTable() {
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE FavouritePlaces " +
                    "(driverName TEXT ," +
                    " source     TEXT , " +
                    "foreign key (driverName) REFERENCES Driver (userName)," +
                    "foreign key (source) REFERENCES Ride (source)" +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void createDriverNotificationTable() {
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE DriverNotification " +
                    "(customerName TEXT PRIMARY KEY  ," +
                    " source     TEXT , " +
                    " destination    TEXT , " +
                    " RideID    INTEGER , " +
                    " noOfPassengers             INTEGER ,"+
                    "foreign key (customerName) REFERENCES Customer (userName)" +
                    "foreign key (RideID) REFERENCES Ride (RideID)" +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void createCustomerNotificationTable() {
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE CustomerNotification " +
                    "(driverName TEXT PRIMARY key," +
                    " cost     TEXT , " +
                    " customerName     TEXT , " +
                    "foreign key (customerName) REFERENCES Customer (userName)," +
                    "foreign key (driverName) REFERENCES Driver (userName)" +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void CreateEventTable() {
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE Event " +
                    "(RideID         INTEGER  , " +
                    "eventName TEXT   ," +
                    " eventTime         TEXT   , " +
                    " actor         TEXT   , " +
                    "foreign key (RideID) REFERENCES Ride (RideID)" +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void CreateAreaTable(){
        Connection c = DBManager.openConnection();
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE Area  " +
                    "( areaName TEXT PRIMARY key  " +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void insertAdmin(String Name, String email, String pass, String phone) {
        Connection c = DBManager.openConnection();
        try {

            System.out.println("Opened database successfully");

            String sql = "INSERT INTO Admin VALUES (? , ?, ?, ?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.setString(2, email);
            pstmt.setString(3, pass);
            pstmt.setString(4, phone);
            pstmt.executeUpdate();
            pstmt.close();

            c.commit();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Admin created successfully");
    }

    public int getRide(String customerName) {
        Connection c = DBManager.openConnection();
        ArrayList<Integer> ids = new ArrayList<Integer>();

        try {


            String sql = "SELECT RideID FROM Ride WHERE customerName =  ?  ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("RideID"));

            }

            rs.close();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return ids.get(ids.size()-1);
    }

    public int getRideNumber(String driverName) {
        Connection c = DBManager.openConnection();
        ArrayList<Integer> ids = new ArrayList<Integer>();

        try {


            String sql = "SELECT RideID FROM Ride WHERE driverName =  ?  ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, driverName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("RideID"));

            }

            rs.close();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return ids.get(ids.size()-1);
    }

    public String getRideDestination(int RideID){
        Connection c = DBManager.openConnection();


        try {


            String sql = "SELECT destination FROM Ride WHERE RideID =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, RideID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return (rs.getString("destination"));

            }

            rs.close();
            c.commit();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }


        return "";

    }

    public int getRideNoOfPassengers(int RideID){
        Connection c = DBManager.openConnection();

        try {


            String sql = "SELECT noOfPassengers FROM Ride WHERE RideID =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, RideID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return (rs.getInt("noOfPassengers"));

            }

            rs.close();
            c.commit();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }


        return 1;

    }



    public void deleteNotification(String name) {
        Connection c = DBManager.openConnection();
        try {



            String sql = "DELETE  FROM DriverNotification WHERE customerName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            pstmt.close();

            c.commit();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
    }

    public void deleteCustomerNotification(String name) {
        Connection c = DBManager.openConnection();
        try {



            String sql = "DELETE  FROM CustomerNotification WHERE customerName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            pstmt.close();

            c.commit();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
    }

    public ArrayList<Driver> getPendingDrivers() {
        ArrayList<Driver> drivers = new ArrayList<>();

        Connection c = DBManager.openConnection();
        Statement stmt = null;
        try {


            stmt = c.createStatement();
            String sql = "SELECT * FROM Driver WHERE status =  'Pending'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Driver drv = new Driver();
                drv.setUserName(rs.getString("userName"));
                drv.setNationalID(rs.getString("nationalID"));
                drv.setDrivingLiscence(rs.getString("drivingLiscence"));
                drv.setPhoneNumber(rs.getString("phoneNumber"));
                drv.setEmailAddress(rs.getString("emailAddress"));
                drv.setPassword(rs.getString("password"));
                drv.setStatus(rs.getString("status"));
                drivers.add(drv);
            }

            rs.close();
            ;
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return drivers;
    }

    public ArrayList<User> getRegisteredUsers() {
        ArrayList<User> users = new ArrayList<>();

        Connection c = DBManager.openConnection();
        Statement stmt = null;
        try {


            stmt = c.createStatement();
            String sql = "SELECT * FROM Driver WHERE status =  'Registered'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Driver drv = new Driver();
                drv.setUserName(rs.getString("userName"));
                drv.setStatus(rs.getString("status"));
                users.add(drv);
            }
            sql = "SELECT * FROM Customer WHERE status =  'Registered'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Customer cus = new Customer();
                cus.setUserName(rs.getString("userName"));
                cus.setStatus(rs.getString("status"));
                users.add(cus);
            }

            rs.close();
            ;
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return users;
    }

    public ArrayList<Ride> getCustomerNotification(Customer customer) {

        ArrayList<Ride> notifications = new ArrayList<>();
        Connection c = DBManager.openConnection();
        try {

            String sql = "SELECT cost,driverName FROM CustomerNotification WHERE customerName = ?"; // return table of sources for certain driver
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, customer.getUserName());
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Ride ride = new Ride();
                ride.setDriver(controllerDBManager.getDriver(rs.getString("driverName")));
                ride.setCost(rs.getDouble("cost"));
                ride.setCustomer(customer);


                notifications.add(ride);

            }

            rs.close();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return notifications;

    }

    public void updateRide(String driverName, double cost, int Id , int rate) {
        Connection c = DBManager.openConnection();
        try {


            String sql = "UPDATE Ride SET cost = ?, driverName = ? , rate=? WHERE RideID=?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setDouble(1, cost);
            pstmt.setString(2, driverName);
            pstmt.setInt(3, rate);
            pstmt.setInt(4, Id);

            pstmt.executeUpdate();
            c.commit();

            sql = "UPDATE Driver SET averageRating = ? WHERE userName= ?";

            pstmt = c.prepareStatement(sql);
            pstmt.setDouble(1, driverDBManager.getDriverAverageRating(controllerDBManager.getDriver(driverName)));
            pstmt.setString(2, driverName);

            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void changeStatus(Driver driver, String status) {
        Connection c = DBManager.openConnection();

        try {


            String sql = "UPDATE Driver set rideStatus = ? where userName=?;";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setString(2, driver.getUserName());
            pstmt.executeUpdate();

            c.commit();
            pstmt.close();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SQLiteJDBC sm = new SQLiteJDBC();
        sm.CreateAdminTable();
        sm.CreateCustomerTable();
        sm.CreateDriverTable();
        sm.CreateRideTable();
        sm.createCustomerNotificationTable();
        sm.createDriverNotificationTable();
        sm.createFavouritePlacesTable();
        sm.CreateEventTable();
        sm.CreateAreaTable();
        sm.insertAdmin("mido", "mido.com", "123", "561");

    }



}
