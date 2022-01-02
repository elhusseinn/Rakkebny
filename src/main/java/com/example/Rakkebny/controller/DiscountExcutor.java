package com.example.Rakkebny.controller;

import com.example.Rakkebny.model.DiscountDBManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DiscountExcutor {

public double applyDiscount(double cost, String destination, int numOfPassengers, String customerName){
    DiscountDBManager discountDBManager = new DiscountDBManager();
    ArrayList<String> holidays = new ArrayList<>();

    holidays.add("01-07");
    holidays.add("01-25");
    holidays.add("04-29");
    holidays.add("05-01");
    holidays.add("05-03");
    holidays.add("05-12");
    holidays.add("05-13");
    holidays.add("05-14");
    holidays.add("05-15");
    holidays.add("05-16");
    holidays.add("06-30");
    holidays.add("07-01");
    holidays.add("07-19");
    holidays.add("07-20");
    holidays.add("07-21");
    holidays.add("07-22");
    holidays.add("07-23");
    holidays.add("07-24");
    holidays.add("08-12");
    holidays.add("10-06");
    holidays.add("10-18");
    holidays.add("10-19");



Discount discount = new BasicRide();
discount.setDiscountCost(cost);

if(discountDBManager.checkArea_Discount(destination)){ // destination is offered a discount

    discount = new areaDiscount(discount);

}
if(numOfPassengers > 1){ // user shares the ride

    discount = new PassengersDiscount(discount);

}
if(discountDBManager.checkFirstRide(customerName)){ // customer's first ride

    discount = new FirstRide(discount);

}
if(discountDBManager.checkBirthDay(customerName)){ // ride request day is the birthday of the customer

discount = new BirthdateDiscount(discount);

}

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");

if(holidays.contains(LocalDate.now().format(formatter))){ // ride request day is a holiday
discount = new HolidaysDiscount(discount);

}












return discount.getCost();
}



}
