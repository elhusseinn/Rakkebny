public class PassengersDiscount extends DiscountDecrator{
    public PassengersDiscount(Discount discount) {
        super(discount);
    }

    double discountPercent = 0.05;

    @Override
    public double getCost() {
        return super.getCost()*discountPercent;
    }
}
