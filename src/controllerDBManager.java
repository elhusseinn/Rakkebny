import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class controllerDBManager {
    public void getPlaceDrivers(ArrayList<Driver> drivers, String place) { // returns all the drivers that has the source in the favourite
        Driver driv = new Driver();
        Connection c = DBManager.openConnection();
        try {



            String sql = "SELECT * FROM FavouritePlaces WHERE source =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, place);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                driv = getDriver(rs.getString("driverName"));
                drivers.add(driv);
            }

            rs.close();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

    }

    public Customer getCustomer(String name) {
        Customer cus = new Customer();
        Connection c = DBManager.openConnection();
        try {


            String sql = "SELECT * FROM CUSTOMER WHERE userName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                cus.setUserName(rs.getString("userName"));
                cus.setEmailAddress(rs.getString("emailAddress"));
                cus.setPassword(rs.getString("password"));
                cus.setPhoneNumber(rs.getString("phoneNumber"));
                cus.setStatus(rs.getString(("status")));
                cus.setBirthDate(rs.getDate("birthDate"));
            }

            rs.close();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return cus;
    }

    public Driver getDriver(String name) {
        Driver driv = new Driver();
        Connection c = DBManager.openConnection();
        try {



            String sql = "SELECT * FROM Driver WHERE userName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
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
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return driv;
    }

    public Admin getAdmin(String name) {
        Admin admin = new Admin();
        Connection c = DBManager.openConnection();

        try {


            String sql = "SELECT * FROM ADMIN WHERE userName =  ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                admin.setUserName(rs.getString("userName"));
                admin.setEmailAddress(rs.getString("emailAddress"));
                admin.setPassword(rs.getString("password"));
                admin.setPhoneNumber(rs.getString("phoneNumber"));
            }

            rs.close();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return admin;
    }

    public void applyDiscount( double cost, int Id) {
        Connection c = DBManager.openConnection();
        try {


            String sql = "UPDATE Ride SET cost = ? WHERE RideID=?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setDouble(1, cost);
            pstmt.setInt(2, Id);
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            ;
            System.out.println("discount made successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
