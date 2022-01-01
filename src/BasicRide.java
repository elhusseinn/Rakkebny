public class BasicRide implements Discount{ // ride without any discounts
    double cost;

public void setDiscountCost(double cost){
    this.cost = cost;
}
    @Override
    public double getCost() {
        return this.cost;
    }
}
