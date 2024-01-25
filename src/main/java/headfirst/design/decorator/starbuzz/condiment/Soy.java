package headfirst.design.decorator.starbuzz.condiment;

import headfirst.design.decorator.starbuzz.beverage.Beverage;

// 두유
public class Soy extends CondimentDecorator {

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return .15 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 두유";
    }
}
