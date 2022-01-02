package com.example.Rakkebny.controller;
import com.example.Rakkebny.model.AdminDBManager;

import java.util.ArrayList;

public class Admin extends User {

AdminDBManager db = new AdminDBManager();
    public void verifyRegistration(Driver driver) {
        db.changeStatus(driver, "Registered");
        System.out.println("Verified successfully Driver now can login into his account");
    }

    public void SuspendedUser(User user) {
        db.changeStatus(user, "Suspended");
    }

    public ArrayList<String> showEvents(int RideID){
        ArrayList<String> temp = new ArrayList<>();
        for(String event : db.ShowEvent(RideID)){
            temp.add(event);
            System.out.println(event);
        }
        return temp;
    }

public void addAreaToDiscountOffers(String area){
        db.insertAreaToDiscountOffers(area);
}



}
