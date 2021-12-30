public class BirthdateDiscount extends DiscountDecrator{
    public BirthdateDiscount(Discount discount) {
        super(discount);
    }
    double discountPercent = 0.10;

    @Override
    public double getCost() {
        return super.getCost()*discountPercent;
    }
}
