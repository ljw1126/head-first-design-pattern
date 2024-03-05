package headfirst.design.proxy.gumball;

public class NoQuarterState implements State {
    private static final long serialVersionUID = 2L;

    // transient : 해당 필드를 직렬화 하지 않는다 (JVM)
    transient GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("동전을 넣습니다");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("동전을 넣어주세요");
    }

    @Override
    public void turnCrank() {
        System.out.println("동전을 넣어주세요");
    }

    @Override
    public void dispense() {
        System.out.println("동전을 넣어주세요");
    }

    @Override
    public void refill() {

    }

    @Override
    public String toString() {
        return "동전 투입 대기중(No Quarter State)";
    }
}
