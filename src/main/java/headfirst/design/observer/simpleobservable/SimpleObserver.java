package headfirst.design.observer.simpleobservable;

import java.util.Observable;
import java.util.Observer;

public class SimpleObserver implements Observer {
    private int value;

    public SimpleObserver(Observable observable) {
        observable.addObserver(this);
    }

    public void display() {
        System.out.println("value : " + value);
    }

    // 두번 출력될 뿐
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
        this.value = (int) arg;
        display();
        if(o instanceof SimpleSubject) {
            SimpleSubject simpleSubject = (SimpleSubject) o;
            this.value = simpleSubject.getValue();
            display();
        }
    }
}
