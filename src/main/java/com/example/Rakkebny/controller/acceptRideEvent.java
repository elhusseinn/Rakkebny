package com.example.Rakkebny.controller;

public class acceptRideEvent implements Event{

    @Override
    public void action(int RideID,String actor) {
        eventManager.insertEvent(RideID, "Accepted Ride",actor); // customer accepts captain's price

    }
}
