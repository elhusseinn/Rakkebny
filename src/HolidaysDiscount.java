public class HolidaysDiscount extends DiscountDecorator {
    final double DISCOUNT_PERCENTAGE = 0.05;

    public HolidaysDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public double getCost() {
        return (super.getCost()) - (super.getCost()*DISCOUNT_PERCENTAGE) ;
    }
}
