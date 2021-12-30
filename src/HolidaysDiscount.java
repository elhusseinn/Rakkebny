public class HolidaysDiscount extends DiscountDecrator {
    double discountPercent = 0.05;

    public HolidaysDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public double getCost() {
        return super.getCost()*discountPercent;
    }
}
