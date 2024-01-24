## 요약 
### 템플릿 메소드 패턴 (Template Method Pattern)
알고리즘 골격(템플릿 메소드)을 정의하고, 알고리즘의 일부 단계를 서브 클래스에서 구현하도록 한다 

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/templateMethod/templateMethod.png?raw=true"/>

**장점**
- 템플릿 코드를 재사용하고 중복 코드를 줄일 수 있다.
- 템플릿 코드를 변경하지 않고 상속 받아서 구체적인 알고리즘만 변경할 수 있다

**단점**
- LSP 원칙을 위반할 수도 있다 (-> 서브 클래스에서 오버라이딩할 경우)
- 알고리즘 구조가 복잡할 수록 템플릿 유지보수가 어려워진다

<br/>

### Hook
템플릿 메소드에 정의한 알고리즘에서 작업을 추가로 처리할지 말지 결정하게 하는 용도로 후크를 사용한다.
```java
public abstract class CaffeineBeverage {

    // 템플릿 메소드
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }
    
    // 생략..
    
    // 필요할 경우 서브 클래스에서 오버라이드할 수 있는 메소드 (= 후크) 
    boolean customerWantsCondiments() {
        return true; 
    }
}

```

<br/>

### 템플릿 콜백 패턴 (Template Callback Pattern)
- 상속 대신 위임(Composition)을 사용
- 템플릿 메소드 인자로 익명 내부 클래스 또는 람다 표현식으로 전달하여 알고리즘에 사용
- ex. JdbcTemplate, RestTemplate 등

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/templateMethod/templateCallback.png?raw=true"/>

<br/>

### 할리우드 원칙 
> 먼저 연락하지 마세요. 저희가 연락드리겠습니다.

저수준 구성요소(ex.서브 클래스, 구체 클래스)가 시스템에 접속할 수 있지만, 
언제, 어떻게 그 구성 요소를 사용할 지는 고수준 구성 요소(ex.템플릿 메소드가 정의된 추상 클래스)가 결정합니다.

---

## 예제. 커피와 홍차 만들기 

**커피 만드는 방법** <br/>
① 물을 끓인다
<br/>
② 끓는 물에 커피를 우려낸다
<br/>
③ 커피를 컵에 따른다
<br/>
④ 설탕과 우유를 추가한다 

**홍차 만드는 방법** <br/>
① 물을 끓인다
<br/>
② 끓는 물에 찻잎을 우려낸다
<br/>
③ 홍차를 컵에 따른다
<br/>
④ 레몬을 추가한다

위의 레시피에서 "① 물을 끓인다" 와 "③ 컵에 따른다"가 동일하고 나머지는 다른 것을 확인할 수 있다.
<br/> <br/>
그리고 둘 다 4단계 절차를 가진다는 점에서 공통 알고리즘(템플릿 메소드)으로 추상화하고, 서로 다른 ②, ④만 추상 메소드로 정의 후 구현하여 사용할 수 있게 한다.

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/templateMethod/example.png?raw=true"/>

```java
public abstract class CaffeineBeverage {

    // 템플릿 메소드
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();
    abstract void addCondiments();

    void pourInCup() {
        System.out.println("컵에 따르는 중");
    }

    void boilWater() {
        System.out.println("물 끓이는 중");
    }
}
```

```java
public class Coffee extends CaffeineBeverage{
    @Override
    void brew() {
        System.out.println("필터로 커피를 우려내는 중");
    }

    @Override
    void addCondiments() {
        System.out.println("설탕과 우유를 추가하는 중");
    }
}
```

```java
public class Tea extends CaffeineBeverage{
    @Override
    void brew() {
        System.out.println("찻잎을 우려내는 중");
    }

    @Override
    void addCondiments() {
        System.out.println("레몬을 추가하는 중");
    }
}
```

<br/>

**실행**
```java
public class BeverageTestDrive {
    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        System.out.println("Making tea...");
        tea.prepareRecipe();
        System.out.println("Making coffee...");
        coffee.prepareRecipe();
    }
}
```

```text
Making tea...
물 끓이는 중
찻잎을 우려내는 중
컵에 따르는 중
레몬을 추가하는 중

Making coffee...
물 끓이는 중
필터로 커피를 우려내는 중
컵에 따르는 중
설탕과 우유를 추가하는 중
```


