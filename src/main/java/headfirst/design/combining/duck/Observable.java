package headfirst.design.combining.duck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observable implements QuackObservable{

    private List<Observer> observers = new ArrayList<>();
    private QuackObservable duck; // 행동을 Observer에게 인자로 전달(전략 패턴), 이게 곧 오리 객체네

    public Observable(QuackObservable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        Iterator<Observer> iterator = observers.iterator();
        while(iterator.hasNext()) {
            Observer o = iterator.next();
            o.update(duck);
        }
    }
}
