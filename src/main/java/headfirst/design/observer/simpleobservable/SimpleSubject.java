package headfirst.design.observer.simpleobservable;

import java.util.Observable;

public class SimpleSubject extends Observable {
    private int value = 0;

    public SimpleSubject() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        // Observable method
        setChanged();
        notifyObservers(value);
    }
}
