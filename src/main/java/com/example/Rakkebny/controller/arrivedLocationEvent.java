package com.example.Rakkebny.controller;

public class arrivedLocationEvent implements Event{


    @Override
    public void action(int RideID,String actor) {
        eventManager.insertEvent(RideID, "Arrived At Location",actor);
    }
}
