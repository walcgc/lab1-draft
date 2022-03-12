package cupcakes;

public abstract class Cupcake {

    private String name;
    private double price;
    private String flavor;
    private String recipeType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public void presentCupcake(){
        System.out.println(this.name + " picked - " + String.valueOf(this.price));

    }
}
