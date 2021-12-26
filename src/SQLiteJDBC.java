import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC {
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
                    " status             TEXT "+
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
                    " drivingLiscence    TEXT   NOT NULL,"+
                    " nationalID         TEXT   NOT NULL,"+
                    "status              TEXT  "+
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
                    "(customerName TEXT PRIMARY KEY  NOT NULL," +
                    " source         TEXT    NOT NULL, " +
                    " destination           TEXT     NOT NULL, " +
                    " cost        TEXT , " +
                    " driverName           TEXT   ," +
                    "foreign key (driverName) REFERENCES Driver (userName)" +
                    "foreign key (customerName) REFERENCES Customer (userName) "+
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

    private void createFavouritePlacesTable(){
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

    private void createDriverNotificationTable(){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE DriverNotification " +
                    "(customerName TEXT ," +
                    " source     TEXT , " +
                    " destination    TEXT , " +
                    " status    TEXT  ," +
                    "foreign key (CustomerName) REFERENCES Customer (userName)" +
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

    private void createCustomerNotificationTable(){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");


            stmt = c.createStatement();
            String sql = "CREATE TABLE CustomerNotification " +
                    "(driverName TEXT ," +
                    " cost     TEXT , " +
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

    public void insertCustomer(String Name, String email,String pass, String phone ){
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO Customer VALUES (? , ?, ?, ?, 'Registered')";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.setString(2, email);
            pstmt.setString(3, pass);
            pstmt.setString(4, phone);
            pstmt.executeUpdate();

            pstmt.close();

            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Customer created successfully");
        
    }

    public void insertDriver(String Name, String email,String pass, String phone, String drivingLiscence, String nationalID ){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);


            String sql = "INSERT INTO Driver VALUES (? , ?, ?, ?, ?, ?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.setString(2, email);
            pstmt.setString(3, pass);
            pstmt.setString(4, phone);
            pstmt.setString(5, drivingLiscence);
            pstmt.setString(6, nationalID);
            pstmt.setString(7, "Pending");
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Driver created successfully");

    }

    private void insertAdmin(String Name, String email,String pass, String phone){
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
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Admin created successfully");
    }

    public void insertRide(Ride ride){
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO Ride VALUES (? , ?, ?, NULL, NULL)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, ride.getCustomer().getUserName());
            pstmt.setString(2, ride.getSource());
            pstmt.setString(3, ride.getDestination());
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Ride created successfully");

    }

    public void insertFavouritePlace(String driverName, String place){
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO FavouritePlaces VALUES (? , ?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, driverName);
            pstmt.setString(2, place);
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("place added successfully");

    }

    public void insertDriverNotification(String customerName, String source, String destination){
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO DriverNotification VALUES (? , ?, ?,'Pending')";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, customerName);
            pstmt.setString(2, source);
            pstmt.setString(3, destination);
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Notification added successfully");
    }

    public void insertCustomerNotification(String driverName, double cost){
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO CustomerNotification VALUES (? , ?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, driverName);
            pstmt.setDouble(2, cost);
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Notification added successfully");
    }


    public Customer getCustomer(String name) {
        Customer cus = new Customer();
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "SELECT * FROM CUSTOMER WHERE userName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while ( rs.next() ) {
                cus.setUserName(rs.getString("userName"));
                cus.setEmailAddress(rs.getString("emailAddress"));
                cus.setPassword(rs.getString("password"));
                cus.setPhoneNumber(rs.getString("phoneNumber"));
                cus.setStatus(rs.getString(("status")));
            }

            rs.close();
            pstmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return cus;
    }

    public Driver getDriver(String name) {
        Driver driv = new Driver();
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);


            String sql = "SELECT * FROM Driver WHERE userName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while ( rs.next() ) {
                driv.setUserName(rs.getString("userName"));
                driv.setEmailAddress(rs.getString("emailAddress"));
                driv.setPassword(rs.getString("password"));
                driv.setPhoneNumber(rs.getString("phoneNumber"));
                driv.setDrivingLiscence(rs.getString("drivingLiscence"));
                driv.setNationalID(rs.getString("nationalID"));
                driv.setStatus(rs.getString(("status")));
            }

            rs.close();
            pstmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return driv;
    }

    public Admin getAdmin(String name) {
        Admin admin = new Admin();
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "SELECT * FROM ADMIN WHERE userName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while ( rs.next() ) {
                admin.setUserName(rs.getString("userName"));
                admin.setEmailAddress(rs.getString("emailAddress"));
                admin.setPassword(rs.getString("password"));
                admin.setPhoneNumber(rs.getString("phoneNumber"));
            }

            rs.close();
            pstmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return admin;
    }

    public void getPlaceDrivers(ArrayList<Driver> drivers, String place){ // returns all the drivers that has the source in the favourite
        Driver driv = new Driver();
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);


            String sql = "SELECT * FROM FavouritePlaces WHERE source =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, place);
            ResultSet rs = pstmt.executeQuery();

            while ( rs.next() ) {
                driv = getDriver(rs.getString("driverName"));
                drivers.add(driv);
            }

            rs.close();
            pstmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

    }

    public void deleteCustomer(String name){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);


            String sql = "DELETE  FROM CUSTOMER WHERE userName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Customer deleted successfully");
            pstmt.close();

            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
    }

    public void deleteDriver(String name){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "DELETE  FROM Driver WHERE userName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Driver deleted successfully");
            pstmt.close();

            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
    }

    public ArrayList<Driver> getPendingDrivers(){
        ArrayList <Driver> drivers = new ArrayList<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM Driver WHERE status =  'Pending'" ;
            ResultSet rs = stmt.executeQuery(sql);

            while ( rs.next() ) {
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

    public ArrayList<User> getRegisteredUsers(){
        ArrayList <User> users = new ArrayList<>();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM Driver WHERE status =  'Registered'";
            ResultSet rs = stmt.executeQuery(sql);
            while ( rs.next()) {
                Driver drv= new Driver();
                drv.setUserName(rs.getString("userName"));
                drv.setStatus(rs.getString("status"));
                users.add(drv);
            }
            sql = "SELECT * FROM Customer WHERE status =  'Registered'";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
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

    public ArrayList<Ride> getDriverNotification(Driver driver){

        ArrayList <Ride> notifications = new ArrayList<>();
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            String sql = "SELECT source FROM FavouritePlaces WHERE driverName = ?"; // return table of sources for certain driver
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1,driver.getUserName());
            ResultSet rs = pstmt.executeQuery();


            while ( rs.next() ) {
                Ride ride = new Ride();

                sql = "SELECT * FROM DriverNotification WHERE status = 'Pending' and source = ?"; // ride is pending and source is included in driver's fav area
                pstmt = c.prepareStatement(sql);
                pstmt.setString(1, rs.getString("source"));

                ResultSet RS = pstmt.executeQuery();
                while (RS.next()){
                     ride = new Ride();
                    ride.setCustomer(getCustomer(RS.getString("customerName")));
                    ride.setSource(RS.getString("source"));
                    ride.setDestination(RS.getString("destination"));
                    notifications.add(ride);
                }

            }

            rs.close();
            pstmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return notifications;

    }  // return list of rides certain driver interested in

    public  void updateRide(Ride ride, String driverName,double cost,String customerName){
        Connection c = null;
        ride.setCost(cost);
        ride.setDriver(getDriver(driverName));
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "UPDATE Ride SET cost = ?, driverName = ? WHERE customerName = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setDouble(1, cost);
            pstmt.setString(2, driverName);
            pstmt.setString(3, customerName);
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Ride created successfully");
    }

    public boolean changeStatus(User user, String status){
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        if (user instanceof Admin) {
            return false;
        }
        else if(user instanceof Driver){
            try {
                String sql = "UPDATE Driver set status = ? where userName=?;";
                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1,status);
                pstmt.setString(2,user.getUserName());
                pstmt.executeUpdate();

                c.commit();
                pstmt.close();
                c.close();
                return true;
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }else if(user instanceof Customer){
            try {
                String sql = "UPDATE Customer set status = ? where userName=?;";
                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1,status);
                pstmt.setString(2,user.getUserName());
                pstmt.executeUpdate();

                c.commit();
                pstmt.close();
                c.close();
                return true;
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }
         return false;
    }

    public boolean changeStatusRide(Ride ride, String status){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
            try {
                String sql = "UPDATE DriverNotification set status = ? where customerName=? ;";
                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1,status);
                pstmt.setString(2,ride.getCustomer().getUserName());
                pstmt.executeUpdate();

                c.commit();
                pstmt.close();
                c.close();
                return true;
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);

        }
        return false;
    }


    public static void main(String[] args) {
        SQLiteJDBC db = new SQLiteJDBC();
        db.createCustomerNotificationTable();


    }



}
