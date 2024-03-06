## 복합 패턴 (Compound)
- 단순히 여러 패턴을 섞어 썻다고 해서 복합 패턴이라 하지 않는다
- 복합 패턴은 몇 개의 패턴을 복합적으로 사용해서 일반적인 문제를 해결 할 수 있어야 한다
- 대표적인 복합 패턴으로 MVC(Model-View-Controller) 패턴이 있다

---

### 오리 시뮬레이션 예제 학습 
아래 인터페이스를 구현하는 오리 구현체를 생성
```java
public interface Quackable {
    void quack();
}
```

#### 요구상황1. "오리 있는 곳에 거위도 있다"
Goose 클래스가 오리 시뮬레이션에서 동일하게 quack() 호출시 동작하도록 한다
```java
public class Goose {
    public void honk() {
        System.out.println("끽끽");
    }
}
```

방법. **어답터 패턴** 활용
```java
public class GooseAdapter implements Quackable {
    private Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }
}
```

// 이미지 gooseadopter

<br/>

#### 요구상황2. 꽥꽥 소리를 낸 횟수를 세 주는 기능을 추가한다
데코레이터 패턴을 활용한다
```java
public class DuckCounter implements Quackable {
    Quackable duck;
    static int numberOfQuacks; // 정적 필드

    public DuckCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        numberOfQuacks += 1; // 꽥꽥 카운트
    }

    public static int getCount() {
        return numberOfQuacks;
    }
}
```
<br/>

#### 요구사항3. 추상 팩토리 패턴 적용
- 문제 : 데코레이터 클래스를 누락할 경우 카운트가 제대로 되지 않음
- 추상 팩토리 패턴을 사용해서 인스턴스 생성을 한 군데서만 관리하도록 함
```java
public abstract class AbstractDuckFactory {
    public abstract Quackable createMallardDuck();
    public abstract Quackable createRedheadDuck();
    public abstract Quackable createDuckCall();
    public abstract Quackable createRubberDuck();
}
```

// 이미지 추상 팩토리 

<br/>

#### 요구사항4. 오리 무리를 관리하는 기능 
- 추상 팩토리를 통해 오리 인스턴스를 개별적으로 생성하고 있는데, 무리를 관리하고 싶음
- **"일급 컬렉션"** 만들어서 관리하도록 한다 
- **반복자 패턴** 적용: 내부 컬렉션에 대해 직접 접근하지 않고, 순차적으로 요소에 접근 가능하도록 기능 지원
- **컴포지트 패턴** 적용: quack() 공통 인터페이스를 통해 모든 인스턴스가 동일하게 동작하도록 지원
```java
public class Flock implements Quackable {
    List<Quackable> quackers = new ArrayList<>();

    public Flock() {
    }
    
    public void add(Quackable quackable) {
        quackers.add(quackable);
    }

    @Override
    public void quack() {
        Iterator<Quackable> iterator = quackers.iterator();
        while(iterator.hasNext()) {
            Quackable quackable = iterator.next();
            quackable.quack();
        }
    }
}
```

<br/>

#### 요구사항5. 꽥꽥 소리를 내는 오리들을 한마리씩 추적할 수 있는 기능
옵저버 패턴 적용 (구조가 조금 복잡)
```java
// Subject 객체
public interface QuackObservable {
    void registerObserver(Observer observer);
    void notifyObservers();
}

// 옵저버 : Quacklogist 클래스 해당
public interface Observer {
    void update(QuackObservable duck);
}
```
<br/>

- 모든 오리 클래스들은 QuackObservalbe 인터페이스를 확장해서 구현한다
- 각 오리 클래스들이 Subject(주제)가 되고, Observer를 등록 후 관리하게 된다
```java
public class RubberDuck implements Quackable{
    private Observable observable;

    public RubberDuck() {
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("삑삑");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    public String toString() {
        return "Rubber Duck";
    }
}
```
<br/>

Observer 인터페이스를 구현은 아래와 같다 
```java
public class Quackologist implements Observer{
    @Override
    public void update(QuackObservable duck) {
        System.out.println("오리 학자 : " + duck + "가 방금 소리를 냈다!");
    }
}
```

---

### MVC 패턴
- **뷰** : 모델을 표현하는 방법을 제공한다. 일반적으로 화면에 표시할 때 필요한 상태와 데이터는 모델에서 직접 가져온다
- **컨트롤러** : 사용자 입력을 받아 모델에 요청하고, 응답을 뷰에 전달한다 (중간에 위치)
- **모델** 
  - 모든 데이터, 상태와 애플리케이션 로직이 들어있다. 
  - 뷰와 컨트롤러에서 모델의 상태를 조작하거나 가져올 때 필요한 인터페이스 제공한다
  - 모델이 자신의 상태 변화를 옵저버들에게 연락해 주긴 하지만, 기본적으로 모델은 뷰와 컨트롤러에 별 관심이 없다


**MVC 패턴에 사용되는 패턴 알아보기**
<br/>
<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/combining/mvc.png?raw=true">

- **뷰** 
  - 컴포지트 패턴
  - 애플리케이션의 겉 모습만 신경 씀
  - 화면은 여러 태그로 트리 형태의 계층 구조를 이루고 있다.
  - 컨트롤러가 뷰에게 화면 갱신을 요청하면 컴포지트 패턴을 통해 공통 인터페이스 실행되어 처리된다
- **컨트롤러** 
  - 전략 패턴
  - 뷰와 모델을 분리하는 역할 (View에서는 Model에서 어떤 내역이 처리되는지 알지 못함)
  - 컨트롤러는 뷰의 전략 객체에 해당한다
  - 컨트롤러를 바꾸면 뷰의 행동도 바꿀 수 있다
- **모델** 
  - 옵저버 패턴
  - 상태가 변경되었을 때 연관된 옵저버(컨트롤러, 뷰)들에게 연락한다

