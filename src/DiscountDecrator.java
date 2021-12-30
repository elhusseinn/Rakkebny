public class DiscountDecrator implements Discount {
    private Discount discount;

    public DiscountDecrator(Discount discount) {
        this.discount = discount;
    }

    @Override
    public double getCost() {
        return 0;
    }
}
