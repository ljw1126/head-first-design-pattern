package headfirst.design.singleton.chocolate;

public class ChocolateController {
    public static void main(String[] args) {
        ChocolateBoiler boiler = ChocolateBoiler.getInstance();
        boiler.fill();
        boiler.boil();
        boiler.drain();

        ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();

        System.out.println(boiler == boiler2);

        System.out.println("=============================");

        ChocolateBoilerV2 boilerV2 = ChocolateBoilerV2.INSTANCE;
        boilerV2.fill();
        boilerV2.boil();
        boilerV2.drain();
    }
}
