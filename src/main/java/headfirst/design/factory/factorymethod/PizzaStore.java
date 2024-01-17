package headfirst.design.factory.factorymethod;

// 생상자(Creator) 클래스
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    // 팩토리 메소드*
    protected abstract Pizza createPizza(String type);
}
