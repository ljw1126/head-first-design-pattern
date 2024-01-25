package headfirst.design.decorator.starbuzzWithSize.condiment;

import headfirst.design.decorator.starbuzzWithSize.beverage.Beverage;

//휘핑크림
public class Whip extends CondimentDecorator {

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 휘핑크림";
    }
}
