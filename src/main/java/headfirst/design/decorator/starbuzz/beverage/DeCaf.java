package headfirst.design.decorator.starbuzz.beverage;

public class DeCaf extends Beverage {
    public DeCaf() {
        description = "디카페인";
    }

    @Override
    public double cost() {
        return 1.65;
    }
}

