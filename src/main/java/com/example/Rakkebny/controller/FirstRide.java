package com.example.Rakkebny.controller;

public class FirstRide extends DiscountDecorator {
    final double  DISCOUNT_PERCENTAGE = 0.10;
    public FirstRide(Discount discountDecrator) {
        super(discountDecrator);
    }

    @Override
    public double getCost() {
        return (super.getCost()) - (super.getCost()*DISCOUNT_PERCENTAGE) ;
    }
}
