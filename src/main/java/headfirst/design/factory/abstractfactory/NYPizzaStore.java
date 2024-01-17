package headfirst.design.factory.abstractfactory;

public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if(type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 치즈 피자");
        } else if(type.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 대합(조개) 피자");
        }

        return pizza;
    }
}
