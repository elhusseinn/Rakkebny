package com.example.Rakkebny.controller;

public class arrivedDestinationEvent implements Event {


    @Override
    public void action(int RideID,  String actor) {
        eventManager.insertEvent(RideID, "Arrived At destination",actor);
    }
}
