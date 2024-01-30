package headfirst.design.command.diner;

// 종업원 (=인보커)
public class Waitress {
    Order order; // 커맨드 객체

    public Waitress() {}

    public void takeOrder(Order order) {
        this.order = order;
        this.order.orderUp();
    }
}
