package headfirst.design.decorator.starbuzz.condiment;

import headfirst.design.decorator.starbuzz.beverage.Beverage;

//휘핑크림
public class Whip extends CondimentDecorator{

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
