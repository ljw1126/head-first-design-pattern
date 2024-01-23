package headfirst.design.state.gumballmachinestate;

public class GumballMachine {
    NoQuarterState noQuarterState;
    HasQuarterState hasQuarterState;
    SoldState soldState;
    SoldOutState soldOutState;
    WinnerState winnerState;

    State state;
    int count = 0;

    public GumballMachine(int count) {
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        winnerState = new WinnerState(this);

        this.count = count;
        if(count > 0) {
            this.state = noQuarterState;
        } else {
            this.state = soldOutState;
        }
    }

    public void insertQuarter() {
        this.state.insertQuarter(); // 동전을 넣는다
    }

    public void ejectQuarter() {
        this.state.ejectQuarter(); // 동전을 꺼내다
    }

    public void turnCrank() {
        this.state.turnCrank(); // 레버를 돌리다
        this.state.dispense(); // 알맹이를 내보내다
    }

    public void releaseBall() {
        System.out.println("알맹이를 내보내고 있습니다");
        if(count > 0) {
            count -= 1;
        }
    }

    public void refill(int count) {
        this.count += count;
        System.out.println("현재 갬블 머신의 볼 수 : " + this.count);
        this.state.refill();
    }

    public int getCount() {
        return count;
    }

    public NoQuarterState getNoQuarterState() {
        return noQuarterState;
    }

    public HasQuarterState getHasQuarterState() {
        return hasQuarterState;
    }

    public SoldState getSoldState() {
        return soldState;
    }

    public SoldOutState getSoldOutState() {
        return soldOutState;
    }

    public WinnerState getWinnerState() {
        return winnerState;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\n주식회사 왕뽑기");
        result.append("\n자바로 돌아가는 최신형 뽑기 기계\n");
        result.append("남은 개수: " + count + " 개");
        result.append("\n현재 상태 :");
        result.append(state);
        result.append("\n");
        return result.toString();
    }
}
