package headfirst.design.strategy;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.preformQuack();
        mallard.performFly();

        System.out.println("==============");

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered()); // 동적으로 전략 변경
        model.performFly();
    }
}
