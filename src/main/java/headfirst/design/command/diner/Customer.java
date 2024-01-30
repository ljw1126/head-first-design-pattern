package headfirst.design.command.diner;

// 클라이언트 객체
// 종업원(인보커)에게 주문(커맨드)을 요청
public class Customer {
    Waitress waitress; // 인보커
    Order order; // 커맨드

    public Customer(Waitress waitress) {
        this.waitress = waitress;
    }

    public void createOrder(Order order) {
        this.order = order;
    }

    public void hungry() {
        this.waitress.takeOrder(this.order);
    }
}
