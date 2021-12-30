public class FirstRide extends  DiscountDecrator{
    double discountPercent = 0.10;
    public FirstRide(Discount discountDecrator) {
        super(discountDecrator);
    }

    @Override
    public double getCost() {
        return super.getCost()*discountPercent;
    }
}
