package headfirst.design.decorator.starbuzzWithSize.condiment;

import headfirst.design.decorator.starbuzzWithSize.beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;
    public abstract String getDescription();
}
