public class PassengersDiscount extends DiscountDecorator {
    public PassengersDiscount(Discount discount) {
        super(discount);
    }

     final double DISCOUNT_PERCENTAGE = 0.05;

    @Override
    public double getCost() {
        return (super.getCost()) - (super.getCost()*DISCOUNT_PERCENTAGE) ;
    }
}
