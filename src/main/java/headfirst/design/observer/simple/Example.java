package headfirst.design.observer.simple;

public class Example {
    public static void main(String[] args) {
        SimpleSubject simpleSubject = new SimpleSubject();
        SimpleObserver simpleObserver = new SimpleObserver(simpleSubject);

        simpleSubject.setValue(99); // 옵저버에게 값이 변경될 걸 알림

        simpleSubject.registerObserver(simpleObserver); // 옵저버 제거
    }
}
