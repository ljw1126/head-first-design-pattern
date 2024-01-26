## 요약
### 데코레이터(Decorator) 패턴
> 기존 코드를 변경하지 않고, 추가 요소/기능을 동적으로 더할 수 있는 패턴

**장점**
- 새로운 클래스를 만들지 않고 기존 기능을 **조합**할 수 있다 -> 각 클래스는 SRP(단일 책임 원칙) 준수
- 컴파일 타임이 아닌 런타임에 동적으로 기능을 변경/확장할 수 있다 -> OCP 원칙 준수, wrapper class 처럼 감쌈
<br/>
-> 구체 클래스에 직접 의존하는게 아니라 인터페이스(wrappee 보기)에 의존성을 가진다는 점에서 DIP 원칙도 준수하고 있다

**단점**
- 데코레이터를 조합하는 코드가 복잡할 수 있다

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/decorator/decorator3.png?raw=true">

※ 자바 I/O에서 데코레이터 패턴을 사용

---

## 예제. 커피 주문 시스템 코드 만들기
추상 클래스와 상속으로 형식을 맞춘다

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/decorator/decorator.png?raw=true">

**상속과 구성 차이**
- 상속의 경우 컴파일시 행동이 정적으로 결정된다. 단, 런타임시 변경 불가
- 구성의 경우 런타임시 동적으로 조합해서 사용가능

**상속이 가지는 단점**
<br/>
① 부모-자식 클래스강의 강결합
<br/>
② 부모 클래스 메소드 변경시 자식 클래스에도 영향
<br/>
③ 부모 클래스에 행동(메소드) 추가시 자식 클래스에도 영향 
<br/>
④ 자바는 단일 상속만을 지원
<br/>
⑤ 상속을 중첩해서 사용할 경우 클래스 계층이 복잡해지고, 유지보수 어려워짐 

**Beverage 추상 클래스 정의**
```java
public abstract class Beverage {
    String description = "제목 없음";
    
    public String getDescription() {
        return description;
    }
    
    abstract double cost();
}
```
<br/>

**첨가물(condiment)을 나타내는 추상 클래스**
```java
public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;
    public abstract String getDescription(); 
}
```
<br/>

**음료 클래스 구현**
```java
public class DarkRoast extends Beverage{
    public DarkRoast() {
        description = "다크 로스트 커피";
    }

    @Override
    public double cost() {
        return .99;
    }
}
```
<br/>

**첨가물 클래스 구현**
```java
public class Mocha extends CondimentDecorator {
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 모카";
    }

    @Override
    public double cost() {
        return beverage.cost() + .20;
    }
}
```

```java
public class Whip extends CondimentDecorator{

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 휘핑크림";
    }
}
```
<br/>

**실행 테스트**
```java
public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage2 = new Whip(new Mocha(new DarkRoast()));
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
    }
}
```

```text
다크 로스트 커피, 모카, 휘핑크림 $1.29
다크 로스트 커피, 모카, 휘핑크림 $1.29
```

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/decorator/decorator2.png?raw=true">


**참고. 상속으로만 구현할 경우 문제점**
<br/>
① 조합에 따른 클래스 수 증가로 유지보수 어려워 짐 -> MilkAndSugarCoffee 생각해보기
<br/>
② 클래스 간 결합도 (커플링)이 높다
<br/>
③ OCP 원칙 위반
```java
class Coffee {
    public String getDescription() {
        return "Coffee";
    }
    
    public double cost() {
        return 2.0;
    }
}

class MilkCoffee extends Coffee {
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }
    
    public double cost() {
        return super.cost() + 0.5;
    }
}

class SugarCoffee extends Coffee {
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }
    
    public double cost() {
        return super.cost() + 0.3;
    }
}

```