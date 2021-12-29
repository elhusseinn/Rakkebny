import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class SQLiteJDBC {
    controllerDBManager controllerDBManager = new controllerDBManager();
    DriverDBManager driverDBManager = new DriverDBManager();

    private void CreateCustomerTable() {
        Connection c = null;
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void CreateDriverTable() {
        Connection c = null;
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
                    "status              TEXT  " +

                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void CreateAdminTable() {
        Connection c = null;
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void CreateRideTable() {
        Connection c = null;
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
                    "foreign key (driverName) REFERENCES Driver (userName)" +
                    "foreign key (customerName) REFERENCES Customer (userName) " +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void createFavouritePlacesTable() {
        Connection c = null;
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void createDriverNotificationTable() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE DriverNotification " +
                    "(customerName TEXT PRIMARY KEY  ," +
                    " source     TEXT , " +
                    " destination    TEXT , " +
                    "foreign key (customerName) REFERENCES Customer (userName)" +
                    " )";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void createCustomerNotificationTable() {
        Connection c = null;
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private void insertAdmin(String Name, String email, String pass, String phone) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Admin created successfully");
    }

    public int getRide(String customerName) {
        Connection c = null;
        ArrayList<Integer> ids = new ArrayList<Integer>();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "SELECT RideID FROM Ride WHERE customerName =  ?  ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("RideID"));

            }

            rs.close();
            pstmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return ids.get(ids.size()-1);
    }

    public void deleteNotification(String name) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);


            String sql = "DELETE  FROM DriverNotification WHERE customerName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            pstmt.close();

            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
    }

    public void deleteCustomerNotification(String name) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);


            String sql = "DELETE  FROM CustomerNotification WHERE customerName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            pstmt.close();

            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
    }

    public ArrayList<Driver> getPendingDrivers() {
        ArrayList<Driver> drivers = new ArrayList<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

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
            c.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return drivers;
    }

    public ArrayList<User> getRegisteredUsers() {
        ArrayList<User> users = new ArrayList<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

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
            c.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return users;
    }

    public ArrayList<Ride> getCustomerNotification(Customer customer) {

        ArrayList<Ride> notifications = new ArrayList<>();
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
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
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return notifications;

    }

    public void updateRide(String driverName, double cost, int Id , int rate) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        SQLiteJDBC sm = new SQLiteJDBC();
        sm.CreateRideTable();
    }



}
