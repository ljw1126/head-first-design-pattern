package headfirst.design.command.dinerLambda;

public class Customer {
    Waitress waitress; // 인보커
    Cook cook;
    Order order; // 커맨드 객체

    public Customer(Waitress waitress, Cook cook) {
        this.waitress = waitress;
        this.cook = cook;
    }

    public void createOrder() {
        this.order = () -> {
            this.cook.makeBurger();
            this.cook.makeFires();
        };
    }
    public void hungry() {
        this.waitress.takeOrder(this.order);
    }
}
