package headfirst.design.factory.abstractfactory;

import headfirst.design.factory.abstractfactory.Pizza;

public class CheesePizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("준비 중 : " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();

        System.out.println("공급 재료 :");
        System.out.println(" " + dough.toString());
        System.out.println(" " + sauce.toString());
        System.out.println(" " + cheese.toString());
    }
}
