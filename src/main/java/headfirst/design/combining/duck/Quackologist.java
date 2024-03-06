package headfirst.design.combining.duck;

public class Quackologist implements Observer{
    @Override
    public void update(QuackObservable duck) {
        System.out.println("오리 학자 : " + duck + "가 방금 소리를 냈다!");
    }
}
