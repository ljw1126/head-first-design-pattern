package headfirst.design.decorator.starbuzzWithSize;

import headfirst.design.decorator.starbuzzWithSize.beverage.Beverage;
import headfirst.design.decorator.starbuzzWithSize.beverage.DarkRoast;
import headfirst.design.decorator.starbuzzWithSize.beverage.Espresso;
import headfirst.design.decorator.starbuzzWithSize.beverage.HouseBlend;
import headfirst.design.decorator.starbuzzWithSize.condiment.Mocha;
import headfirst.design.decorator.starbuzzWithSize.condiment.Soy;
import headfirst.design.decorator.starbuzzWithSize.condiment.Whip;

// Soy에만 Size에 따라 가격 차등 적용
public class StarbuzzCoffeeWithSize {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()
                + " $" + String.format("%.2f", beverage.cost()));

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription()
                + " $" + String.format("%.2f", beverage2.cost()));

        Beverage beverage3 = new HouseBlend();
        beverage3.setSize(Beverage.Size.VENTI);
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription()
                + " $" + String.format("%.2f", beverage3.cost()));
    }
}
