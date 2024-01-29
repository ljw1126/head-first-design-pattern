package headfirst.design.adapter.ducks;

public class TurkeyAdapter implements Duck{

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        // 칠면조는 오리 처럼 멀리 날지 못한다. 그래서 이러한 행동 대응을 한다고 표현
        for(int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
