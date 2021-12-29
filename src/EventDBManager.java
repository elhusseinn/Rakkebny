import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Date;

public class EventDBManager {

    public void insertEvent(int ID,String eventName) {
        Connection c = null;
        String time ;
        time = new Date().toString();


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO Event VALUES (? , ?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, ID);
            pstmt.setString(2, eventName);
            pstmt.setString(3,time);
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
