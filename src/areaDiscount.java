public class areaDiscount extends DiscountDecorator {
    final double DISCOUNT_PERCENTAGE = 0.10;

    public areaDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public double getCost() {
        return (super.getCost()) - (super.getCost()*DISCOUNT_PERCENTAGE);
    }
}
