package com.example.Rakkebny.controller;

import com.example.Rakkebny.model.EventDBManager;

public interface Event {
    boolean is_done=false;
    EventDBManager eventManager = new EventDBManager();
    public void action(int RideID , String actor);

}
