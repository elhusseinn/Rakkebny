import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DriverDBManager {
    controllerDBManager controllerDBManager = new controllerDBManager();
    public void insertFavouritePlace(String driverName, String place) {
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
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("place added successfully");

    }

    public void insertCustomerNotification(Ride ride) {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO CustomerNotification VALUES (? , ?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, ride.getDriver().getUserName());
            pstmt.setDouble(2, ride.getCost());
            pstmt.setString(3, ride.getCustomer().getUserName());
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Notification added successfully");
    }

    public void insertDriver(String Name, String email, String pass, String phone, String drivingLiscence, String nationalID) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);


            String sql = "INSERT INTO Driver VALUES (? , ?, ?, ?, ?, ?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.setString(2, email);
            pstmt.setString(3, pass);
            pstmt.setString(4, phone);
            pstmt.setString(5, drivingLiscence);
            pstmt.setString(6, nationalID);
            pstmt.setInt(7, 0);
            pstmt.setString(8, "Pending");
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Driver created successfully");

    }

    public ArrayList<Ride> getDriverNotification(Driver driver) {
        ArrayList<Ride> notifications = new ArrayList<Ride>();
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            String sql = "SELECT source FROM FavouritePlaces WHERE driverName = ?"; // return table of sources for certain driver
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, driver.getUserName());
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Ride ride = new Ride();
                sql = "SELECT * FROM DriverNotification WHERE  source = ?"; //  and source is included in driver's fav area
                pstmt = c.prepareStatement(sql);
                pstmt.setString(1, rs.getString("source"));

                ResultSet RS = pstmt.executeQuery();
                while (RS.next()) {
                    ride = new Ride();
                    ride.setCustomer(controllerDBManager.getCustomer(RS.getString("customerName")));
                    ride.setSource(RS.getString("source"));
                    ride.setDestination(RS.getString("destination"));
                    ride.setDriver(controllerDBManager.getDriver(driver.getUserName()));
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

    public ArrayList<Ride> getDriversRides(Driver driver) {

        ArrayList<Ride> notifications = new ArrayList<>();
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            String sql = "SELECT * FROM Ride WHERE driverName = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, driver.getUserName());
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Ride ride = new Ride();
                ride.setDriver(controllerDBManager.getDriver(rs.getString("driverName")));
                ride.setCost(rs.getDouble("cost"));
                ride.setCustomer(controllerDBManager.getCustomer(rs.getString("customerName")));
                ride.setSource(rs.getString("source"));
                ride.setRate(rs.getInt("rate"));
                ride.setDestination(rs.getString("destination"));


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

    public double getDriverAverageRating(Driver driver){
        double averageRating = 0;
        int counter = 0;
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "SELECT * FROM Ride WHERE driverName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, driver.getUserName());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if(rs.getInt("rate") >= 0){
                    averageRating += rs.getInt("rate");
                    counter++;
                }
            }

            rs.close();
            pstmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return averageRating / counter;
    }
}
