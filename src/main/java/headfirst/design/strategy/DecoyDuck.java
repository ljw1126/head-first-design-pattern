package headfirst.design.strategy;

public class DecoyDuck extends Duck{
    public DecoyDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new MuteQuack();
    }

    @Override
    void display() {
        System.out.println("장식용(가짜) 오리 입니다");
    }
}
