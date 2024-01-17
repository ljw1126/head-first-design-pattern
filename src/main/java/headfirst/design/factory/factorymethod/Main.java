package headfirst.design.factory.factorymethod;

public class Main {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        System.out.println("<NYStyleCheesePizza>");
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("애단이 주문한 " + pizza.getName() + "\n");

        System.out.println("<ChicagoStyleVeggiePizza>");
        pizza = chicagoStore.orderPizza("veggie");
        System.out.println("조엘이 주문한 " + pizza.getName() + "\n");
    }
}
