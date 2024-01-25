package headfirst.design.observer.simple;

public class SimpleObserver implements Observer{
    private int value;

    public SimpleObserver(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update(int value) {
        this.value = value;
        display();
    }

    public void display() {
        System.out.println("value : " + value);
    }
}
