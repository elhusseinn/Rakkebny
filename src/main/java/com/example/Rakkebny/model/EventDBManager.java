package com.example.Rakkebny.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Date;

public class EventDBManager {

    public void insertEvent(int ID,String eventName,String actor) {
        Connection c = DBManager.openConnection();
        String time ;
        time = new Date().toString();



        try {


            String sql = "INSERT INTO Event VALUES (? , ?,?,?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, ID);
            pstmt.setString(2, eventName);
            pstmt.setString(3,time);
            pstmt.setString(4, actor);

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

    public static void main(String[] args) {
        EventDBManager eventDBManager=new EventDBManager();
        eventDBManager.insertEvent(2,"makeoffer","driver");
    }
}
