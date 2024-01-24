# 요약 
### 상태 패턴 (State Pattern)
- 객체의 내부 상태가 바뀜에 따라서 객체의 행동을 바꿀 수 있는 디자인 패턴 
- 마치 객체의 클래스가 바뀌는 것과 같은 효과를 얻을 수 있다

= 객체 내부 상태 변경에 따라 객체의 행동이 달라지는 패턴 

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/state/stateUML.png?raw=true"/>

**장점**
- 상태에 따른 동작을 개별 클래스로 옮겨서 관리할 수 있다
- Context 기존 코드를 변경하지 않고 새로운 State 추가할 수 있다 (OCP 원칙 준수) 
- 코드 복잡도를 줄일 수 있다 (-> 여러 if 조건문 같은 부분) 

**단점**
- 클래스 복잡도가 증가한다 -> 오버엔지니어링이 될 수 있는데 읽기 편하다면 상관없다고 생각

<br/>

### 상태 패턴과 전략 패턴 비교
- 상태 패턴은 Context에 미리 정해진 상태 전환 규칙에 따라 스스로 상태와 행동를 변경한다
- 전략 패턴은 구성과 위임을 활용하여 다른 행동과 알고리즘을 보이도록 한다. 런타임에 동적으로 변경도 가능하다.

**목적**
- 상태 패턴은 객체가 여러 상태를 가질 때, 상태에 따라 객체의 행동을 동적으로 변경하고 싶을 때 사용
- 전략 패턴은 알고리즘을 캡슐화하고 클라이언트에서 알고리즘을 독립적으로 선택할 수 있도록 하는데 주로 사용

**구조**
- 상태 패턴 : Context와 State로 클래스 구성된다. 현재 State에 따라 다른 행동을 수행한다
- 전략 패턴 : Context와 Strategy로 클래스 구성된다. 특정 알고리즘을 실행할 때 해당 전략 객체를 사용한다

상태 패턴은 객체의 상태에 따라 동작을 동적으로 변경하는데 중점을 두며, 전략 패턴은 알고리즘을 독립적으로 선택가능하도록 하는 데 중점을 둔다

---

# 예시, 최첨단 뽑기 기계

### 요구사항
다음 그림과 같이 동작하는 뽑기 기계를 자바 코드로 구현한다

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/state/state1.png?raw=true"/>

**상태 구분**
- 동전 없음 (NoQuarterState)
- 동전 있음 (HasQuarterState)
- 알맹이 판매 (SoldState)
- 알맹이 매진 (SoldOutState)

**행동 구분**
- 동전을 투입한다 (insertQuarter)
- 동전을 반환한다 (ejectQuarter)
- 손잡이를 돌린다 (turnCrank)
- 알맹이를 내보낸다 (dispense)
- 알맹이를 채운다 (refill)

### 구현 코드 (상태 패턴 적용하기 전)
```java
public class GumballMachine {
    // 상태
    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1; // QUARTER : 동전 투입구
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;

    int state = SOLD_OUT;
    int count = 0; // 기계에 들어있는 알맹이 개수

    public GumballMachine(int count) {
        this.count = count;
        if(count > 0) {
            state = NO_QUARTER;
        }
    }

    // 행동 : 동전 투입
    public void insertQuarter() {
        if(state == HAS_QUARTER) {
            System.out.println("동전은 한 개만 넣어주세요");
        } else if(state == NO_QUARTER) {
            state = HAS_QUARTER; // 상태 변경
            System.out.println("동전을 넣으셨습니다");
        } else if(state == SOLD_OUT) {
            System.out.println("매진되었습니다. 다음 기회에 이용해 주세요.");
        } else if(state == SOLD) {
            System.out.println("알멩이를 내보내고 있습니다.");
        }
    }

    // 행동 : 동전 반환(꺼냄)
    public void ejectQuarter() {
        if(state == HAS_QUARTER) {
            System.out.println("동전이 반환됩니다");
            state = NO_QUARTER;
        } else if(state == NO_QUARTER) {
            System.out.println("동전을 넣어주세요");
        } else if(state == SOLD) {
            System.out.println("이미 알맹이를 뽑으셨습니다");
        } else if(state == SOLD_OUT) {
            System.out.println("동전을 넣지 않으셨습니다. 동전이 반환되지 않습니다");
        }
    }

    // 행동 : 손잡이 돌린다
    public void turnCrank() {
        if(state == SOLD) {
            System.out.println("손잡이는 한 번만 돌려 주세요");
        } else if(state == NO_QUARTER) {
            System.out.println("동전을 넣어주세요");
        } else if(state == SOLD_OUT) {
            System.out.println("매진되었습니다");
        } else if(state == HAS_QUARTER) {
            System.out.println("손잡이를 돌리셨습니다");
            state = SOLD; // 상태 변경
            dispense();
        }
    }

    // 행동 : 알맹이 내보내기
    public void dispense() {
        if(state == SOLD) {
            System.out.println("알맹이를 내보내고 있습니다");
            count -= 1;
            if(count == 0) {
                System.out.println("더 이상 알맹이가 없습니다");
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
        } else if(state == NO_QUARTER) {
            System.out.println("동전을 넣어 주세요");
        } else if(state == SOLD_OUT) {
            System.out.println("매진입니다");
        } else if(state == HAS_QUARTER) {
            System.out.println("알맹이를 내보낼 수 없습니다.");
        }
    }

    // 행동 : 알맹이를 채운다
    public void refill(int numGumBalls) {
        this.count = numGumBalls;
        state = NO_QUARTER;
    }

    public String toString() {
        // 뽑기 머신 정보 출력 
    }
}
```

<br/>

**현재 문제점**
<br/>
① OCP 원칙 지켜지지 않음 -> 확장이 어렵다
<br/>
② 변경되는 부분을 캡슐화하지 않음
<br/>
③ 복잡한 조건문으로 인해 상태 전환을 한 눈에 파악하기 힘듦
<br/>
④ 새로운 기능을 추가하는 과정에서 기존 코드에 없던 새로운 버그가 생길 가능성이 높음

<br/>

### 상태 패턴 적용

**절차**
<br/>
① 모든 행동에 관한 메소드가 들어있는 State 인터페이스를 정의한다
<br/>
② 모든 상태를 대상으로 상태 클래스를 구현한다
<br/>
③ 조건부 코드를 제거하고, 상태 클래스에 모든 작업을 위임하도록 변경한다

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/state/state2.png?raw=true"/>

**NoQuarterState**
<br/>
insertQuarter() -> HasQuarterState로 상태 전환
```java
public class NoQuarterState implements State {
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("동전을 넣습니다");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    // 이외 적절한 메시지 출력(생략)
}
```
<br/>

**HasQuarterState**
<br/>
- ejectQuarter()  -> NoQuarterState로 상태 전환 
- turnCrank() -> SoldState로 상태 전환

```java
public class HasQuarterState implements State {
    Random randomWinner = new Random(System.currentTimeMillis());
    GumballMachine gumballMachine;

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
            gumballMachine.setState(gumballMachine.getWinnerState()); // 추가 요구사항 : 1/10확률로 두번뽑기
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
```
<br/>

**SoldState**
<br/>
dispense() -> 알맹이를 내보내고, count 존재 여 에 따라 NoQuarterState 또는 SoldOutState로 전환
```java
public class SoldState implements State{
    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    // 이외 적절한 메시지 출력(생략) 
    
    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumballs");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }

}
```
<br/>

**SoldStateState**
<br/>
refill() -> NoQuarterState로 전환
```java
public class SoldOutState implements State{
    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    // 적절한 메시지 출력 (생략)

    @Override
    public void refill() {
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public String toString() {
        return "매진(Sold Out State)";
    }
}
```


**뽑기 기계 코드**
```java
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
```

<br/>

**실행**
```java
public class GumballMachineTestDrive {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);

        System.out.println(gumballMachine);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();

        System.out.println(gumballMachine);
    }
}
```

```text
주식회사 왕뽑기
자바로 돌아가는 최신형 뽑기 기계
남은 개수: 5 개
현재 상태 :동전 투입 대기중(No Quarter State)

동전을 넣습니다
손잡이를 돌리셨습니다
알맹이를 내보내고 있습니다

주식회사 왕뽑기
자바로 돌아가는 최신형 뽑기 기계
남은 개수: 4 개
현재 상태 :동전 투입 대기중(No Quarter State)

동전을 넣습니다
손잡이를 돌리셨습니다
알맹이를 내보내고 있습니다
동전을 넣습니다
손잡이를 돌리셨습니다
알맹이를 내보내고 있습니다
동전을 넣습니다
동전이 반환됩니다

주식회사 왕뽑기
자바로 돌아가는 최신형 뽑기 기계
남은 개수: 2 개
현재 상태 :동전 투입 대기중(No Quarter State)
```

새로운 상태를 추가할 경우 State 인터페이스를 구현하여 행동에 따라 다음 상태로 전환되도록 하면 된다 
-> **OCP 원칙 지킬 수 있게 됨**