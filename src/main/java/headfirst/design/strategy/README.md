# 요약
### 전략패턴 (Strategy Pattern)
- 알고리즘 군을 정의하고 캡슐화해서 각각의 알고리즘을 수정해서 쓸 수 있게 해줍니다. <br/>
- 전략 패턴을 사용하면 클라이언트로부터 알고리즘을 분리해서 독립적으로 변경할 수 있습니다.

= 행위를 클래스로 캡슐화하여 해당 계열(집합)안에서 자유롭게 바꿀 수 있게 해주는 패턴

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/strategy/strategyUML.png?raw=true"/>


### 객체지향 원칙 (디자인 원칙)
① 바뀌는 부분을 따로 뽑아서 캡슐화한다.
<br/>
② 구현보다는 인터페이스에 맞춰서 프로그래밍한다.
<br/>
③ 상속보다는 구성(Composition)을 활용한다.

---

# 오리 시뮬레이션 게임, SimUDuck
오리 시뮬레이션 게임을 만들 때 Duck이라는 슈퍼 클래스를 만들고, 이를 상속하는 서로 다른 종류의 오리를 만들었습니다. <br/>

```java
public abstract class Duck {
    void quack() {
        System.out.println("꽥꽥");
    }
    
    void swim() {
        System.out.println("수영을 합니다");
    }
    
    abstract void display(); // 하위 서브 클래스에서 구현
}
```

```java
public class MallardDuck extends Duck {
    @Override
    public void display() {
        System.out.println("천둥 오리입니다");
    }
}

public class RedHeadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("아메리카 흰죽지 입니다");
    }
} 
```

이후 오리가 날 수 있도록 fly() 기능을 추가하라는 요구 사항을 받았습니다. <br/>

그래서 Duck(superclass)에 fly()를 추가한 후 기가 막히게 서브 클래스에서 상속해서 구현하면 될거라 생각했지만, <br/>

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/strategy/strategy1.png?raw=true"/>

**[문제점]** <br/>
1)&nbsp;RubberDuck, DecoyDuck과 같은 fly()가 필요없은 서브 클래스까지 기능을 구현해야 함 <br/>
2)&nbsp;서브 클래스에서 코드가 중복됨 <br/>
3)&nbsp;메소드를 마음대로 오버라이딩 했을 때 캡슐화가 깨져 다른 오리 클래스에 영향을 줄 수 있는 잠재적 문제 요소 가짐

```java
public class RubberDuck extends Duck {
    @Override
    public void quack() {
        System.out.println("삑삑");
    }
    
    @Override
    public void fly() {
        // 아무것도 하지 않음
    }
    
    @Override
    public void display() {
        System.out.println("러버덕입니다");
    }
}

public class DecoyDuck extends Duck {
    @Override
    public void fly() {
        // 아무것도 하지 않음
    }

    @Override
    public void display() {
        System.out.println("장식용 오리입니다");
    }
}

```

<br/>

### 전략 패턴 적용하기
우선 코드에서 바뀌는 부분과 바뀌지 않는 부분을 찾는다.
- 바뀌는 부분 : quack(), fly() 
- 바뀌지 않는 부분 : display(), swim() 

fly(), quack()는 오리 종류에 따라 달라지므로, Duck 클래스에서 메소드를 분리하여 각 행동을 나타낼 수 있는 클래스 집합을 만들어야 한다. (클래스 집합 -> 인터페이스와 구현체들)
<br/><br/>
각 행동은 인터페이스 (FlyBehavior, QuackBehavior)로 표현하고, 해당 인터페이스 구현체를 만든다.

```java
public interface FlyBehavior {
    void fly();
}
```

```java
public class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("하늘을 납니다");
    }
}

public class FlyNoWay() implements FlyBehavior {
    public void fly() {
        System.out.println("날지 못합니다");
    }
}

```

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/strategy/strategy2.png?raw=true"/>

이렇게 행동을 인터페이스로 선언하여 사용할 경우<br/> 
장점1) 재사용할 수 있게 되고, 런타임에 동적으로 전략(행동)을 바꿔서 사용할 수 있게 된다.<br/>
장점2) 그리고 Duck 클래스의 기존 코드를 건드리지 않고, 행동을 추가하거나 삭제하는 것이 가능해진다 (**OCP 원칙 준수**)

```java
public abstract class Duck {
    protected FlyBehavior flyBehavior; // 다른 클래스에 위임
    protected QuackBehavior quackBehavior;
    
    void performFly() {
        flyBehavior.fly();
    }
    
    void performQuack() {
        quackBehavior.quack(); 
    }

    void quack() {
        System.out.println("꽥꽥");
    }

    void swim() {
        System.out.println("수영을 합니다");
    }

    abstract void display(); // 하위 서브 클래스에서 구현
}
```
<br/>

**서브 클래스의 경우**
```java
public class MallardDuck extends Duck {
    public MallardDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    // 기타 코드
}

public class RubberDuck extends Duck {
    public RubberDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Squeak();
    }
    
    // 기타 코드
}
```
<br/>

**실행**
```java
public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.preformQuack(); // 꽥꽥
        mallard.performFly(); // 하늘을 납니다
        
        Duck rubberDuck = new RubberDuck();
        rubberDuck.performQuack(); // 삑삑
        rubberDuck.performFly(); // 날지 못합니다
    }
}
```

만약 동적으로 런타임에 행동을 변경하고 싶은 경우, setter 사용하여 전략을 바꿀 수도 있다.

**예시**
```java
public abstract class Duck {
    // 동일
    
    void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
    
    void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
```

```java
public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck rubberDuck = new RubberDuck();
        rubberDuck.performQuack(); // 삑삑
        rubberDuck.performFly(); // 날지 못합니다
        rubberDuck.setFlyBehavior(new FlyRocketPowered());
        rubberDuck.performFly(); // 로켓 추진력으로 날아갑니다 !!
    }
}
```
<br/>
<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/strategy/strategy3.png?raw=true"/>

- Duck에는 FlyBehavior가 있다 (true)
- Duck에는 QuackBehavior가 있다 (true)
- MallardDuck은 Duck이다 (true)
- Duck은 MallardDuck이다 (false, MallardDuck md = new Duck() 생각하기)
- FlyWithWings는 FlyBehavior다 (true)
- Quack은 QuackBehavior다(true)

---

