package headfirst.design.decorator.starbuzz.condiment;

import headfirst.design.decorator.starbuzz.beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;
    public abstract String getDescription();
}
