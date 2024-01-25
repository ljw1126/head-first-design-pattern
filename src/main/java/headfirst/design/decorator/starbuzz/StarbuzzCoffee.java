package headfirst.design.decorator.starbuzz;

import headfirst.design.decorator.starbuzz.beverage.Beverage;
import headfirst.design.decorator.starbuzz.beverage.DarkRoast;
import headfirst.design.decorator.starbuzz.beverage.Espresso;
import headfirst.design.decorator.starbuzz.beverage.HouseBlend;
import headfirst.design.decorator.starbuzz.condiment.Mocha;
import headfirst.design.decorator.starbuzz.condiment.Soy;
import headfirst.design.decorator.starbuzz.condiment.Whip;

public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());

        Beverage beverage4 = new Whip(new Mocha(new Soy(new HouseBlend())));
        System.out.println(beverage4.getDescription() + " $" + beverage4.cost());
    }
}
