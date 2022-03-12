package discounts;

public class FullPrice implements DiscountState {

    private double discount = 1;

    @Override
    public double discount() {
        return this.discount;
    }
}
