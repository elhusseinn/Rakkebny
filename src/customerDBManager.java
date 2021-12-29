import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public  class customerDBManager {

    public void insertCustomer(String Name, String email, String pass, String phone, java.sql.Date birthdate) {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO Customer VALUES (? , ?, ?, ?,?, 'Registered')";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.setString(2, email);
            pstmt.setString(3, pass);
            pstmt.setString(4, phone);
            pstmt.setDate(5, birthdate);
            pstmt.executeUpdate();

            pstmt.close();

            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Customer created successfully");

    }

    public void insertRide(Ride ride) {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            String sql = "INSERT INTO Ride VALUES (null , ? , ?, ?, null , null , null )";
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
        System.out.println("Ride created successfully");

    }


}
