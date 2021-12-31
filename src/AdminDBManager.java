import java.sql.*;
import java.util.ArrayList;

public class AdminDBManager {
    public boolean changeStatus(User user, String status) {
        Connection c = DBManager.openConnection();

        if (user instanceof Admin) {
            return false;
        } else if (user instanceof Driver) {
            try {
                String sql = "UPDATE Driver set status = ? where userName=?;";
                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1, status);
                pstmt.setString(2, user.getUserName());
                pstmt.executeUpdate();

                c.commit();
                pstmt.close();
                ;
                return true;
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        } else if (user instanceof Customer) {
            try {
                String sql = "UPDATE Customer set status = ? where userName=?;";
                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1, status);
                pstmt.setString(2, user.getUserName());
                pstmt.executeUpdate();

                c.commit();
                pstmt.close();
                ;
                return true;
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        return false;
    }

    public ArrayList<String> ShowEvent(int RideID) {

        ArrayList<String> events = new ArrayList<>();

        Connection c = DBManager.openConnection();
        try {
            String sql = "SELECT * FROM Event where RideID = ? ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, RideID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                switch (rs.getString("eventName")){
                    case "MakeOffer":{
                        events.add("Event name: "+"make Offer "+"Event time: " + rs.getString("eventTime") + "Driver Name: " + rs.getString("actor"));
                    }
                        break;
                    case "Accepted Ride":{

                        sql = "SELECT customerName FROM Ride WHERE RideID = ?";
                        pstmt = c.prepareStatement(sql);
                        pstmt.setInt(1, RideID);
                        ResultSet RS = pstmt.executeQuery();


                        events.add("Event name: "+"Accepted Ride "+"Event time: " + rs.getString("eventTime") + " User Name: "+ RS.getString("customerName"));
                    }
                        break;
                    case "Arrived At Location":{
                        sql = "SELECT * FROM Ride WHERE RideID = ?";
                        pstmt = c.prepareStatement(sql);
                        pstmt.setInt(1, RideID);
                        ResultSet RS = pstmt.executeQuery();


                        events.add("Event name: "+"Arrived At Location "+"Event time: " + rs.getString("eventTime") + " User Name: "+ RS.getString("customerName") + " Driver Name: " + RS.getString("driverName"));
                        RS.close();
                    }
                    break;
                    case "Arrived At destination":{
                        sql = "SELECT * FROM Ride WHERE RideID = ?";
                        pstmt = c.prepareStatement(sql);
                        pstmt.setInt(1, RideID);
                        ResultSet RS = pstmt.executeQuery();


                        events.add("Event name: "+"Arrived At Destination "+"Event time: " + rs.getString("eventTime") + " User Name: "+ RS.getString("customerName") + " Driver Name: " + RS.getString("driverName"));
                        RS.close();
                    }
                    break;

                }



            }

            rs.close();
            ;
            pstmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return events;
    }

    public ArrayList<Integer> getDistinctRideNumbers(){
        ArrayList<Integer> rides = new ArrayList<>();
        Connection c = DBManager.openConnection();
        try {

            String sql = "SELECT  RideID FROM Ride ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                rides.add(rs.getInt("RideID"));
            }

            pstmt.close();
            rs.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return rides;
    }

    public void insertAreaToDiscountOffers(String area){
        Connection c = DBManager.openConnection();
        try {
            String sql = "INSERT INTO Area VALUES (?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, area);
            pstmt.executeUpdate();
            pstmt.close();

            c.commit();
            ;
            System.out.println("Area added to discount offers successfully");
        } catch (Exception e) {
            System.err.println("area already added, cannot add due to unique constraints");
        }

    }

}
