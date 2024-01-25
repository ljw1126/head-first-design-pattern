package headfirst.design.decorator.starbuzzWithSize.condiment;

import headfirst.design.decorator.starbuzzWithSize.beverage.Beverage;

// 두유
public class Soy extends CondimentDecorator {

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        double cost = beverage.cost();
        Size size = beverage.getSize();
        if(size.isTall()) {
            cost += .10;
        } else if(size.isGrande()) {
            cost += .15;
        } else if(size.isVenti()) {
            cost += .20;
        }

        return cost;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 두유";
    }
}
