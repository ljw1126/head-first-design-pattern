package headfirst.design.combining.duck;

public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();
        simulator.simulate(duckFactory);
    }

    private void simulate(AbstractDuckFactory duckFactory) {

        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable mallardDuck = duckFactory.createMallardDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable goose = duckFactory.createGoose();

        Flock flock = new Flock();
        flock.add(redheadDuck);
        flock.add(rubberDuck);
        flock.add(duckCall);
        flock.add(goose);


        Quackable mallardDuck1 = duckFactory.createMallardDuck();
        Quackable mallardDuck2 = duckFactory.createMallardDuck();
        Quackable mallardDuck3 = duckFactory.createMallardDuck();
        Quackable mallardDuck4 = duckFactory.createMallardDuck();

        Flock flockOfWallards = new Flock();
        flockOfWallards.add(mallardDuck1);
        flockOfWallards.add(mallardDuck2);
        flockOfWallards.add(mallardDuck3);
        flockOfWallards.add(mallardDuck4);

        System.out.println("\n오리 시뮬레이션 게임 : 전체 무리");
        flock.quack();

        System.out.println("\n오리 시뮬레이션 게임 : 옵저버");
        Quackologist quackologist = new Quackologist();
        flockOfWallards.registerObserver(quackologist); // 각각의 오리 객체(Subject)에게 Observer 등록
        simulate(flockOfWallards);

        System.out.println("오리가 소리 낸 횟수 : " + QuackCounter.numberOfQuacks);
    }

    private void simulate(Quackable duck) {
        duck.quack();
    }
}
