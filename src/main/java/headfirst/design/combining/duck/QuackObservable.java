package headfirst.design.combining.duck;

public interface QuackObservable {
    void registerObserver(Observer observer);
    void notifyObservers();
}
