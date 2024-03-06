package headfirst.design.combining.duck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flock implements Quackable {
    private List<Quackable> quackers = new ArrayList<>();

    public Flock() {
    }

    public void add(Quackable quackable) {
        quackers.add(quackable);
    }

    @Override
    public void quack() {
        Iterator<Quackable> iterator = quackers.iterator();
        while(iterator.hasNext()) {
            Quackable quackable = iterator.next();
            quackable.quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        Iterator<Quackable> iterator = quackers.iterator();
        while(iterator.hasNext()) {
            Quackable duck = iterator.next();
            duck.registerObserver(observer); // 각 오리 객체가 주제(Subject)에 해당하고, 각각 옵저버를 등록하여 관리한다
        }
    }

    @Override
    public void notifyObservers() {

    }

    public String toString() {
        return "Flock of Ducks";
    }
}
