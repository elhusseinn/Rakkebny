package com.example.Rakkebny.controller;

import com.example.Rakkebny.model.controllerDBManager;

import java.util.ArrayList;

public class Controller implements rideFeatures {

    private static Controller instance = null;
    controllerDBManager db = new controllerDBManager();

    private Controller() {
    }

    public static Controller GetInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;

    }

    @Override
    public ArrayList<Driver> searchDriver(String place) { // returns array of drivers that has place in their favourite
        ArrayList<Driver> drivers = new ArrayList<>();
        db.getPlaceDrivers(drivers, place);
        return drivers;
    }


    public User Authenticate(User user) { // check instance of->
        String name = user.getUserName();
        String lol = "registered";

        if (user instanceof Customer) {
            if (db.getCustomer(name).getPassword().equals(user.getPassword()) && db.getCustomer(name).getStatus().equals("Registered")) {
                return user;
            } else {
                System.out.println("Credentials doesn't match");
                return null;
            }
        } else if (user instanceof Driver) {
            if (db.getDriver(name).getPassword().equals(user.getPassword()) && db.getDriver(name).getStatus().equals("Registered")) {
                return user;
            } else {
                System.out.println("Credentials doesn't match");
                return null;
            }

        } else if (user instanceof Admin) {
            if (db.getAdmin(name).getPassword().equals(user.getPassword())) {
                return user;
            } else {
                System.out.println("Credentials doesn't match");
                return null;
            }
        } else {
            System.out.println("Credentials doesn't match");
            return null;
        }


    }




}
