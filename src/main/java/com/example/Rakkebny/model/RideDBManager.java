package com.example.Rakkebny.model;

import com.example.Rakkebny.controller.Ride;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RideDBManager {
    public void insertDriverNotification(Ride ride, int ID) {
        Connection c = DBManager.openConnection();

        try {


            String sql = "INSERT INTO DriverNotification VALUES (? , ?, ?, ?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, ride.getCustomer().getUserName());
            pstmt.setString(2, ride.getSource());
            pstmt.setString(3, ride.getDestination());
            pstmt.setInt(4, ID);
            pstmt.setInt(5, ride.getNoOfPassengers());

            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Notification added successfully");
    }

}
