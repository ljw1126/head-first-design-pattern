package headfirst.design.proxy.gumball;

import java.util.Random;

public class HasQuarterState implements State {
    private static final long serialVersionUID = 2L;
    Random randomWinner = new Random(System.currentTimeMillis());
    transient GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("동전은 한 개만 넣어주세요");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("동전이 반환됩니다");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("손잡이를 돌리셨습니다");
        int winner = randomWinner.nextInt(10); // 0 ~ 9
        if(winner == 0 && gumballMachine.getCount() > 1) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        } else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() { // 해당 상태에서 부적절한 메소드
        System.out.println("알맹이를 내보낼 수 없습니다");
    }

    @Override
    public void refill() {

    }

    @Override
    public String toString() {
        return "Has Quarter State";
    }
}
