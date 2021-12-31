public class DiscountDecorator implements Discount {
    private Discount discount;
    double cost = 0;

    public DiscountDecorator(Discount discount) {
        this.discount = discount;
    }

    @Override
    public double getCost() {
        return this.discount.getCost();
    }

    @Override
    public void setDiscountCost(double cost) {
        this.cost = cost;
    }
}
