package headfirst.design.factory.abstractfactory;

public class ClamPizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("준비 중 : " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        clams = ingredientFactory.createClam();

        System.out.println("공급 재료 :");
        System.out.println(" " + dough.toString());
        System.out.println(" " + sauce.toString());
        System.out.println(" " + cheese.toString());
        System.out.println(" " + clams.toString());
    }
}
