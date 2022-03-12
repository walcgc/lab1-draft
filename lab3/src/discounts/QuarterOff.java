package discounts;

public class QuarterOff implements DiscountState {

    private double discount = .75;

    @Override
    public double discount() {
        return this.discount;
    }

}
