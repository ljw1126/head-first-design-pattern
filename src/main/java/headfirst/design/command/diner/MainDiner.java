package headfirst.design.command.diner;


public class MainDiner {
    public static void main(String[] args) {
        // 손님(클라이언트)이 종업원(인보커)에게 주문 요청(커맨드) 하면 주방장(리시버)이 받아 처리한다
        // 커맨드 객체는 리시버를 가지고 있다
        // 커맨드 객체는 공통 인터페이스를 가진다 (ex. execute())
        Waitress waitress = new Waitress(); // 종업원(인보커)
        Customer customer = new Customer(waitress); // 클라이언트 객체
        customer.createOrder(new BurgerAndFiresOrder(new Cook())); // BurgerAndFiresOrder: 커맨드 객체, Cook : 리시버
        customer.hungry(); // 종업원(인보커)에게 주문(커맨드) 요청
    }
}
