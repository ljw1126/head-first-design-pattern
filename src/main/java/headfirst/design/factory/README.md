# 팩토리 패턴

아래에 피자 주문하는 코드를 살펴보자
```java
Pizza orderPizza(String type) {
    Pizza pizza;
    
    // 상황에 따라 변경되는 부분
    // 피자의 종류가 바뀔 때마다 코드를 계속 고쳐야한다
    if(type.equals("cheese")) {
        pizza = new CheesePizza();
    } else if(type.equals("pepperoni")) {
        pizza = new PepperoniPizza();
    } //..

    // 변경되지 않는 부분
    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();

    return pizza;
}
```


**문제점. 인스턴스를 만드는 구상 클래스를 선택하는 부분** <br/>
① 구상 클래스를 직접 사용함으로써 특정 구상 클래스에 의존적 <br/>
② 새로운 피자가 추가 될 때마다 코드를 고쳐야 하므로 변경이나 확장에 어려워짐

-> 우선 "변경되는 부분"과 "변경되지 않는 부분"을 구분하고, **변경되는 부분**을 찾아 캡슐화할 수 있도록 한다

## 간단 팩토리 (Simple Factory)

> 팩토리 (fatory) : 객체 생성을 처리하는 클래스 <br/>
> Simple Factory != Factory Pattern

#### 객체 생성 팩토리 만들기 
```java
public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        
        // orderPizza() 메소드에서 뽑아낸 코드
        if(type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if(type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else if(type.equals("clam")) {
            pizza = new ClamPizza();
        } else if(type.equals("veggie")) {
            pizza = new VeggiePizza();
        }

        return pizza;
    }
}
```
Q. 이렇게 캡슐화했을 때 장점은? <br/>
A. 피자 객체 생성 작업을 팩토리 클래스로 캡슐화해 놓으면 구현을 변경할 때 팩토리 클래스 하나만 고치면 되기 때문에 관리 용이해짐

#### 클라이언트 코드 수정하기

```java
public class PizzaStore {
    private SimplePizzaFactory factory; // PizzaStore에 SimplePizzaFactory 레퍼런스 저장

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) { 
        Pizza pizza = factory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    //기타 메소드
}
```

<p align="center">
<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/factory/simpleFactory.png?raw=true">
</p>

## 팩토리 메소드 패턴(Factory Method Pattern)

#### 팩토리 메소드 패턴의 정의 
- 팩토리 메서드 패턴(Factory Method Pattern)은 객체를 생성할 때 필요한 인터페이스(=추상 메서드)를 만듭니다. 
- 어떤 클래스의 인스턴스를 만들지는 "서브 클래스에서 결정"합니다.
- 팩토리 메소드 패턴을 사용하면 클래스 인스턴스 만드는 일을 서브클래스에게 맡기게 됩니다.

<p align="center">
<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/factory/factoryMethodPattern.png?raw=true">
</p>

> PizzaStore 의 서브 클래스인 NYPizzaStore 와 ChicagoPizzaStore 에게 객체 생성 책임을 맡김

- 팩토리 메소드 패턴은 서브 클래스에서 어떤 클래스를 만들지 결정함으로써 객체 생성을 캡슐화합니다.
- 즉, 여기서는 PizzaStore 를 상속한 서브 클래스가 대신 팩토리 역할 수행하게 된다. (createPizza() 추상 메소드 오버라이드 함으로써)
- 그러면 슈퍼 클래스에 있는 클라이언트 코드(orderPizza())와 서브 클래스에 있는 객체 생성 코드(createPizza())를 분리할 수 있게 된다.

#### Creator 클래스
```java
// 생상자(Creator) 클래스
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type); // 서브 클래스에서 객체 결정, PizzaStore는 어떤 피자가 만들어질지 모른다

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    // 팩토리 메소드* 
    protected abstract Pizza createPizza(String type);
}
```

#### ConcreteCreator 클래스 - 실제로 제품 생상하는 팩토리 메소드 구현하는 클래스
```java
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if(type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if(type.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else {
            return null;
        }
    }
}
```

#### Product 클래스
```java
// 제품(Product) 클래스
public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    List<String> toppings = new ArrayList<>();

    void prepare() {
        System.out.println("준비 중 : " + name);
        System.out.println("도우를 돌리는 중..");
        System.out.println("소스를 뿌리는 중...");
        System.out.println("토핑을 올리는 중....");
        for(String topping : toppings) {
            System.out.println(" " + topping);
        }
    }

    void bake() {
        System.out.println("175도에서 25분 간 굽기");
    }

    void cut() {
        System.out.println("피자를 사선으로 자르기");
    }

    void box() {
        System.out.println("상자에 피자 담기");
    }

    public String getName() {
        return name;
    }
}
```

#### ConcreteProduct 클래스 
```java
public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "뉴욕 스타일 소스와 치즈 피자";
        dough = "씬 크러스트 도우";
        sauce = "마리나라 소스";

        toppings.add("잘게 썬 레지아노 치즈");
    }
}
```
<br/>

#### 간단한 팩토리 vs 팩토리 메소드 패턴 비교

Simple Factory의 경우
```java
PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());
pizzaStore.orderPizza("cheese");
```

Factory Method Pattern의 경우 
```java
PizzaStore nyStore = new NYPizzaStore();
Pizza pizza = nyStore.orderPizza("cheese");
```

간단한 팩토리의 경우 피자 가게와 피자를 생성하는 부분이 나눠져 있었다면, 팩토리 메소드 패턴에서는 피자 가게가 피자를 생성하는 부분을 포함하게 되었다
(NYPizzaStore는 PizzaStore 이다 -> true)

<br/>

### 의존성 뒤집기 원칙 (DIP, Dependency Inversion Principle)

> 추상화 된 것에 의존하게 만들고 구상 클래스에 의존하지 않게 만든다

이 원칙은 고수준 구성 요소가 저수준 구성 요소에 의존하게 하면 안되고, 항상 추상화 된 것에 의존하게 만들어야 한다는 뜻이 담겨 있다.

- 고수준 구성 요소 : PizzaStore (추상 클래스)
- 저수준 구성 요소 : Pizza 구상 클래스 (NYCheesePizza, ChicagoPepperoniPizza 등)

처음 의존관계를 살펴볼 때, PizzaStore가 모든 피자 클래스에 직접 의존함에 따라 피자 클래스가 추가되거나 변경될 경우 PizzaStore의 로직까지 변경해야 하는 문제가 발생할 수 있었다
<p align="center">
    <img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/factory/dependency.png?raw=true">
</p>

의존성 뒤집기 원칙(DIP)에 따라 중간에 Pizza 추상 클래스를 의존하게 함으로써 PizzaStore를 만들때는 구상 피자 클래스를 신경쓰지 않아도 되게 되었다
<p align="center">
    <img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/factory/DIP.png?raw=true">
</p>

> 고수준 구성요소와 저수준 구성요소 모두 Pizza 추상 클래스에 의존하고 있다.

<br/>

**의존성 뒤집기 원칙을 지키는 방법** <br/>
① 변수에 구상 클래스의 레퍼런스를 저장하지 맙시다 <br/>
-> new 생성자() 를 사용할 경우 직접 구상 클래스의 레퍼런스를 사용하게 된다. 그러니 팩토리를 써서 구상 클래스 레퍼런스를 직접 변수에 저장하는 일을 방지합시다.
<br/>

② 구상 클래스에서 유도된 클래스를 만들지 맙시다 <br/> 
-> 구상 클래스에서 유도된 클래스를 만들면 특정 구상 클래스에 의존하게 된다. 인터페이스나 추상 클래스처럼 추상화된 클래스를 만들어 의존관계를 형성합시다.
<br/> 

③ 베이스 클래스(PizzaStore)에 이미 구현되어 있는 메소드를 오버라이드 하지 맙시다 <br/>
-> 이미 구현되어 있는 메소드를 오버라이드하면 베이스 클래스가 제대로 추상화 되지 않습니다. 베이스 클래스에서 메소드를 정의할 때는 모든 서브 클래스에서 공유할 수 있는 것만 정의합니다.
<br/>


## 추상 팩토리 패턴(Abstract Factory Pattern)

#### 추상 팩토리 패턴의 정의 
구상 클래스에 직접 의존하지 않고도 서로 연관되거나 의존적인 객체로 이루어진 제품군을 생상하는 인터페이스를 제공하는 디자인 패턴

<p align="center">
    <img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/factory/abstractFactoryPattern.png?raw=true">
</p>


피자 가게 별로 서로 다른 원재료를 공급하기 위한 추상 팩토리를 도입한다

```java
public interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClam(); // clam : 대합
}
```
<br/>

**뉴욕 원재료 팩토리 만들기**
```java
public class NYPizzaIngredientFactory implements PizzaIngredientFactory{

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        Veggies[] veggies = {new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicePepperoni();
    }

    @Override
    public Clams createClam() {
        return new FreshClams();
    }
}
```
<br/>

**Pizza 클래스 변경하기**
```java
public abstract class Pizza {
    String name;

    Dough dough;
    Sauce sauce;
    Veggies[] veggies;
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    abstract void prepare();

    void bake() {
        System.out.println("175도에서 25분 간 굽기");
    }

    void cut() {
        System.out.println("피자를 사선으로 자르기");
    }

    void box() {
        System.out.println("상자에 피자 담기");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "완료 : " + getName();
    }
}
```
<br/>

**원재료 팩토리에서 재료 공급받아 피자 만들기**
```java
public class CheesePizza extends Pizza {

    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("준비 중 : " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();

        System.out.println("공급 재료 :");
        System.out.println(" " + dough.toString());
        System.out.println(" " + sauce.toString());
        System.out.println(" " + cheese.toString());
    }
}
```
<br/>

**피자 가게-피자별 원재료 공장 주입하기**
```java
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory(); // 원재료 공장 선택

        if(type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 치즈 피자");
        } else if(type.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 대합(조개) 피자");
        }

        return pizza;
    }
}
```

```java
public class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory(); // 원재료 공장 선택

        if(type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("Chicago Style Cheese Pizza");
        } else if(type.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("Chicago Style Clam Pizza");
        }

        return pizza;
    }
}
```
CheesePizza 클래스는 하나지만 주입 받는 원재료 공장에 따라 가게 특색에 맞는 피자가 만들어 질 수 있었다.
<br/>

- 추상 팩토리 패턴을 사용하면 클라이언트에서 추상 인터페이스로 일련된 제품을 공급받을 수 있다. 
- 이때 실제로 어떤 제품이 생상되는지는 클라이언트 코드(*PizzaStore)에서는 전혀 알 필요가 없다. 
- 따라서 클라이언트와 팩토리에서 생상되는 제품을 분리할 수 있다


---

## 장/단점

**팩토리 메서드 패턴**
<br/>
> 구체적으로 어떤 객체 생성을 할지 서브 클래스에서 결정한다 (ex. NYPizzaStore, ChicagoPizzaStore)

**장점**
- 객체를 생성할 때 필요한 인터페이스(추상 메서드)를 만들고, 서브 클래스에서 이를 구현하여 객체 생성을 책임지게 함으로써 기존 클라이언트 코드를 건드리지 않고, 
확장 가능하고 유지 보수 용이한 형태를 가지게 된다
- 즉, Product와 Creator의 Coupling을 느슨하게 가져갔기 때문에 확장에 열려있고 변경(수정)에 닫혀 있게 된다 (OCP원칙)

**단점**
- 클래스가 늘어나는건 피할 수 없다

<br/>

**추상 팩토리 패턴**
<br/>
> 구체적인 클래스를 지정하지 않고, 서로 관련있는 여러 객체를 생성하는 디자인 패턴 (ex. 원재료 팩토리)

**장점**
- 구체적인 제품과 클라이언트 코드 간의 긴밀한 결합을 피할 수 있다 -> CheesPizza에서 직접 원재료를 지정하지 않고, 주입받은 원재료 팩토리 통해 공급받게 된다
- 기존 클라이언트 코드를 변경하지 않고, 새로운 제품을 도입할 수 있다. -> CheesePizza에서 주입받는 원재료 팩토리만 교체하면 된다

**단점**
- 많은 새로운 인터페이스와 구체 클래스가 패턴과 함께 도입되기 때문에 코드량, 복잡도가 올라갈 수 있다.

<br/>

## 팩토리 메소드 패턴과 추상 팩토리 패턴의 차이
**관점의 차이**
- 팩토리 메소드 패턴은 "팩토리를 구현하는 방법(inheritance, 상속)"에 초점을 둔다
- 추상 팩토리 패턴은 "팩토리를 사용하는 방법(composition, 구성)"에 초점을 둔다

**목적의 차이**
- 팩토리 메소드 패턴은 구체적인 객체 생성 과정을 서브 클래스로 옮기는 것을 목적 
- 추상 팩토리 패턴은 관련 있는 여러 객체를 구체 클래스에 의존하지 않고 만들 수 있게 해주는 것이 목적