package com.example.Rakkebny.controller;

import com.example.Rakkebny.model.customerDBManager;
import com.example.Rakkebny.model.controllerDBManager;

import java.util.ArrayList;
import java.sql.Date;

public class Customer extends User implements Register{
    public Customer() {
        this.rideNotifications = new ArrayList<>() ;
    }
    customerDBManager cusreg = new customerDBManager();
    controllerDBManager controllerDBManager = new controllerDBManager();

    public ArrayList<Ride> rideNotifications;
    private Date BirthDate;

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public void register(User user) {
        cusreg.insertCustomer(user.getUserName(), user.getEmailAddress(), user.getPassword(), user.getPhoneNumber(), (java.sql.Date) getBirthDate());
    }

    public Customer(String username, String password, String phoneNumber, String email, String birthDate){
        setUserName(username);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setEmailAddress(email);
        setBirthDate(Date.valueOf(birthDate));
    }


    public int requestRide(Ride ride) {

       int ID = cusreg.insertRide(ride);
        ride.notifyDrivers(ride, ID);
        return ID;
    }

    public void acceptRide(int RideID){
        acceptRideEvent AER = new acceptRideEvent();
        AER.action(RideID, this.getUserName());

    }

    public Customer getAccount(Customer customer){
        return  controllerDBManager.getCustomer(customer.getUserName());
    }


    public ArrayList<Ride> getRideNotifications() {
        return rideNotifications;
    }

}
