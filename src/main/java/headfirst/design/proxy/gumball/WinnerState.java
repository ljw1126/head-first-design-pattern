package headfirst.design.proxy.gumball;

public class WinnerState implements State {
    private static final long serialVersionUID = 2L;
    transient GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ejectQuarter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void turnCrank() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getSoldOutState());
        } else {
            gumballMachine.releaseBall();
            System.out.println("축하드립니다. 알맹이를 하나 더 받으실 수 있습니다");
            if(gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            } else {
                System.out.println("더이상 알맹이가 없습니다");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }

    @Override
    public void refill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Winner State";
    }
}
