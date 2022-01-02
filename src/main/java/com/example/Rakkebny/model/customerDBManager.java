package com.example.Rakkebny.model;

import com.example.Rakkebny.controller.Ride;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public  class customerDBManager {

    public void insertCustomer(String Name, String email, String pass, String phone, java.sql.Date birthdate) {
        Connection c = DBManager.openConnection();

        try {


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
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Customer created successfully");

    }

    public int insertRide(Ride ride) {
        Connection c = DBManager.openConnection();
        ArrayList<Integer> RideIDs= new ArrayList<>();

        try {


            String sql = "INSERT INTO Ride VALUES (null , ? , ?, ?, null , null , null,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, ride.getCustomer().getUserName());
            pstmt.setString(2, ride.getSource());
            pstmt.setString(3, ride.getDestination());
            pstmt.setInt(4, ride.getNoOfPassengers());

            pstmt.executeUpdate();
            c.commit();

            sql = "SELECT RideID FROM Ride WHERE customerName = ?";
            pstmt = c.prepareStatement(sql);
            pstmt.setString(1, ride.getCustomer().getUserName());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RideIDs.add(rs.getInt("RideID"));
            }



            pstmt.close();
            c.commit();
            ;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Ride created successfully");

        return RideIDs.get(RideIDs.size()-1);
    }



}
