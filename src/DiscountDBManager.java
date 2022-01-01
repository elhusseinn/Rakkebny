import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DiscountDBManager {

    public boolean checkArea_Discount(String area) {
        Connection c = DBManager.openConnection();


        try {


            String sql = "SELECT areaName FROM Area WHERE areaName =  ?  ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, area);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

            rs.close();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        return false;
    }

    public boolean checkFirstRide(String customerName) {
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

        return ids.size() == 1;
    }

    public boolean checkBirthDay(String customerName){
        Connection c = DBManager.openConnection();
        String date="";

        try {


            String sql = "SELECT BirthDate FROM Customer WHERE userName =  ?  ";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                date = rs.getDate("BirthDate").toString();
            }

            rs.close();
            pstmt.close();
            ;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");

        String [] splittedDate = date.split("-");
        date = splittedDate[1] + "-" + splittedDate[2];

        if(date.equalsIgnoreCase(LocalDate.now().format(formatter))){
            return true;
        }
        else {

            return false;
        }
    }


    public static void main(String[] args) {
        DiscountDBManager d = new DiscountDBManager();

        System.out.println(d.checkBirthDay("mark"));
    }




}

