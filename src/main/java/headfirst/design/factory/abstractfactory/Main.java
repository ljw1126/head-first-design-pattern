package headfirst.design.factory.abstractfactory;

public class Main {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        System.out.println("<<뉴욕 스타일 치즈 피자>>");
        Pizza cheesePizza = nyStore.orderPizza("cheese");
        System.out.println(cheesePizza.toString() + "\n");

        System.out.println("<<뉴욕 스타입 대합 피자>>");
        Pizza clamPizza = nyStore.orderPizza("clam");
        System.out.println(clamPizza.toString() + "\n");

        System.out.println("<<시카고 스타일 치즈 피자>>");
        Pizza chicagoCheesePizza = chicagoStore.orderPizza("cheese");
        System.out.println(chicagoCheesePizza.toString() + "\n");
    }
}
