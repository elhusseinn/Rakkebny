import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AdminDBManager {
    public boolean changeStatus(User user, String status) {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

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
                c.close();
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
                c.close();
                return true;
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        return false;
    }

}
