package com.example.Rakkebny.controller;

public class makeOfferEvent implements Event{
    @Override
    public void action(int RideID,String actor) {
        eventManager.insertEvent(RideID, "makeoffer",actor);



    }



}
