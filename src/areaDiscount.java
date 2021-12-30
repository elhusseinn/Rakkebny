public class areaDiscount extends DiscountDecrator{
    double discountPercent = 0.10;

    public areaDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public double getCost() {
        return super.getCost()*discountPercent;
    }
}
