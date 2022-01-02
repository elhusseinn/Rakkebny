package com.example.Rakkebny.controller;

public class BirthdateDiscount extends DiscountDecorator {
    public BirthdateDiscount(Discount discount) {
        super(discount);
    }
    final double DISCOUNT_PERCENTAGE = 0.10;

    @Override
    public double getCost() {
        return (super.getCost()) - (super.getCost()*DISCOUNT_PERCENTAGE) ;
    }
}
