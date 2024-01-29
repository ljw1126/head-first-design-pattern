package headfirst.design.adapter.ducks;

public class DuckTestDrive {
    public static void main(String[] args) {
        Duck duck = new MallardDuck(); // 천둥오리
        Turkey turkey = new WildTurkey(); // 칠면조
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("칠면조가 말하길");
        turkey.gobble();
        turkey.fly();

        System.out.println("\n오리가 말하길");
        testDuck(duck);

        System.out.println("\n칠면조 어댑터가 말하길");
        testDuck(turkeyAdapter); // 오리 대신 칠면조를 넣는다

        System.out.println("\n오리 어댑터가 말하길");
        DuckAdapter duckAdapter = new DuckAdapter(duck);
        duckAdapter.gobble();
        duckAdapter.fly();
    }

    private static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
