import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RideDBManager {
    public void insertDriverNotification(Ride ride) {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO DriverNotification VALUES (? , ?, ?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, ride.getCustomer().getUserName());
            pstmt.setString(2, ride.getSource());
            pstmt.setString(3, ride.getDestination());
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

}
