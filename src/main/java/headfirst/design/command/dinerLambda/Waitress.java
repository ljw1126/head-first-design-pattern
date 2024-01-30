package headfirst.design.command.dinerLambda;

// 인보커
public class Waitress {
    Order order;

    public Waitress() {
    }

    public void takeOrder(Order order) {
        this.order = order;
        this.order.orderUp();
    }
}
