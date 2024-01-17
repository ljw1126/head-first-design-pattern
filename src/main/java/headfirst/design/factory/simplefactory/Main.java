package headfirst.design.factory.simplefactory;

public class Main {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());

        System.out.println("<CheesePizza>");
        pizzaStore.orderPizza("cheese");

        System.out.println("<PepperoniPizza>");
        pizzaStore.orderPizza("pepperoni");
    }
}
