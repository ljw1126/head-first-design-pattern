package headfirst.design.command.dinerLambda;

public class MainDiner {
    public static void main(String[] args) {
        Cook cook = new Cook(); // 리시버
        Waitress waitress = new Waitress(); // 종업원 (인보커)
        Customer customer = new Customer(waitress, cook); // 클라이언트
        customer.createOrder(); // Order 생성 (커맨드 객체)
        customer.hungry(); // 종업원(인보커)에게 주문(커맨드)을 요청한다
    }
}
