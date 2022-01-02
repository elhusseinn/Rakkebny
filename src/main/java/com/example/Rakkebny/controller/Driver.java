package com.example.Rakkebny.controller;

import com.example.Rakkebny.model.DriverDBManager;
import com.example.Rakkebny.model.controllerDBManager;

import java.sql.DriverManager;
import java.util.ArrayList;


public class Driver extends User implements Register {
    private String NationalID;
    private String drivingLiscence;

    DriverDBManager db = new DriverDBManager();
    com.example.Rakkebny.model.controllerDBManager controllerDBManager = new controllerDBManager();
    makeOfferEvent makeOfferEvent = new makeOfferEvent();

    public String getNationalID() {
        return NationalID;
    }

    public Driver() {

        this.setStatus("Pending");
    }

    public void setNationalID(String nationalID) {
        NationalID = nationalID;
    }

    public String getDrivingLiscence() {
        return drivingLiscence;
    }

    public void setDrivingLiscence(String drivingLiscence) {
        this.drivingLiscence = drivingLiscence;
    }

    public void setFavouritePlaces(String place) {
        db.insertFavouritePlace(this.getUserName(), place);
    }

    public void makeOffer(Ride ride, double cost) {

        ride.setCost(cost);
        makeOfferEvent.action(ride.getRideID(),this.getUserName());
        db.insertCustomerNotification(ride);

    }

    public void arrived(int id,String name){

        if(name.equalsIgnoreCase("Location")){
            arrivedLocationEvent arrivedLocationEvent = new arrivedLocationEvent();
            arrivedLocationEvent.action(id, this.getUserName());
            System.out.println("Arrived at Location");


        }else if (name.equalsIgnoreCase("Destination")){
            arrivedDestinationEvent arrivedDestinationEvent=new arrivedDestinationEvent();
            arrivedDestinationEvent.action(id, this.getUserName());
            System.out.println("Arrived at Destination");

        }
    }

    public void register(User user) {
            db.insertDriver(user.getUserName(), user.getEmailAddress(), user.getPassword(), user.getPhoneNumber(), getDrivingLiscence(), getNationalID());

    }

    public ArrayList<Ride> getNotifications() {
        return db.getDriverNotification(this);
    }

    public ArrayList<Ride> listPreviousRides() {
        ArrayList<Ride> rides = new ArrayList<>();
        if(db.getDriversRides(this).size() == 0){
            System.out.println("You haven't make any rides yet, CHECK YOUR NOTIFICATIONS!");
        }
        for (int i = 0; i <db.getDriversRides(this).size() ; i++) {
            db.getDriversRides(this).get(i).setDriver(this);
            rides.add(db.getDriversRides(this).get(i));

            System.out.println((i+1) + " customerName-> " + db.getDriversRides(this).get(i).getCustomer().getUserName() + ", Source-> " +db.getDriversRides(this).get(i).getSource() +", Destination-> "+ db.getDriversRides(this).get(i).getDestination()+ ", Cost-> "+ db.getDriversRides(this).get(i).getCost() + ", Rate-> " + db.getDriversRides(this).get(i).getRate());
        }
        return rides;
    }

    public double getAverageRating() {
       return db.getDriverAverageRating(this);
    }







}