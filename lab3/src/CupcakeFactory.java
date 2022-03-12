import cupcakes.BananaCupcake;
import cupcakes.ChocolateCupcake;
import cupcakes.Cupcake;
import cupcakes.StrawberryCupcake;

import java.util.Random;

public class CupcakeFactory {

    public Cupcake makeCupcake(String name) {
        switch (name) {
            case "Chocolate":
                return new ChocolateCupcake();
            case "Banana":
                return new BananaCupcake();
            case "Strawberry":
                return new StrawberryCupcake();
            default:
                return null;
        }
    }

    public Cupcake randomCupcake() {
        Random rand = new Random();
        int num = rand.nextInt(3);

        switch (num) {
            case 0:
                return new ChocolateCupcake();
            case 1:
                return new BananaCupcake();
            case 2:
                return new StrawberryCupcake();
            default:
                return null;
        }

    }


}
