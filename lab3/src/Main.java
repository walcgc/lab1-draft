import cupcakes.Cupcake;
import discounts.*;

public class Main {
// Main acts like a cash register
    public static void main(String[] args) {
        // Discount States
        FullPrice fullPrice = new FullPrice();
        HalfOff halfOff = new HalfOff();
        QuarterOff quarterOff = new QuarterOff();
        ThreeFourthsOff threeFourthsOff = new ThreeFourthsOff();

        DiscountState discount = quarterOff;
        double total = 0;

        //Cupcake Factory
        CupcakeFactory factory = new CupcakeFactory();
        for (int i = 0; i < 3; i++){
            Cupcake cupcake = factory.randomCupcake();
            cupcake.presentCupcake();
            total += cupcake.getPrice();
        }
        System.out.println("----------------------------------------------");

        System.out.println("Total price: " + String.valueOf(total));
        System.out.println("Discount rate: " + String.valueOf((1-discount.discount())*100) + " %");
        System.out.println("Total price w/ discount: " + String.valueOf(total*discount.discount()));




    }
}
