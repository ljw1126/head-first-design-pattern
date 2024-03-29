package headfirst.design.observer.weather;


public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(); // 주제의 상태가 변경되었을 때 모든 옵저버에게 변경 내용을 알리려고 호출하는 메소드
}
