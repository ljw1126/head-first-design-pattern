## 요약 
### 옵저버 패턴(observer)
- 특정한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체에게 알림이 가고 반응을 하는 디자인 패턴
- 일대다(one-to-many) 의존성 가진다 (이때 주인은 주제(Subject) 쪽이다)

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/observer/observer.png?raw=true">
<br/>

**장점**
- 상태를 변경하는 객체와 변경을 감지하는 객체의 관계를 느슨하게 유지할 수 있다
- Subject의 상태 변경을 주기적으로 조회하지 않고 자동으로 감지할 수 있다
- 런타임에 옵저버를 추가하거나 제거할 수 있다 -> composition 사용

**단점**
- 클래스 복잡도가 증가한다
- 등록되어 있는 옵저버를 해지하지 않으면, 인스턴스가 쌓여서 memory leak(메모리 누수) 발생 가능

※ week reference 찾아보기 : 참조 해지는 보장하지 못하는 방식, 시나리오에 적합한 경우에만 적용
<br/><br/>

### 옵저버 패턴의 느슨한 결합(Loose Coupling)
① 주제는 옵저버가 특정 인터페이스를 구현한다는 사실만 안다.  
<br/>
② 옵저버는 언제든지 새로 추가할 수 있다 <br/> 
-> *.addListener(Observer o)와 같은 메소드 호출해서 런타임에 동적 추가 가능
<br/>

③ 새로운 형식의 옵저버를 추가할 때도 주제를 변경할 필요가 전혀 없다 <br/>
-> Observer 인터페이스가 구현되어 있다면 상관없음
<br/>

④ 주제와 옵저버는 서로 독립적으로 재사용할 수 있다
<br/>

⑤ 주제나 옵저버가 달라져도 서로에게 영향을 미치지 않는다
<br/>

> **디자인 원칙**
> <br/> 상호작용하는 객체 사이에는 가능하면 느슨한 결합을 사용해야 한다
<br/><br/>

### Observable, Observer 인터페이스
- 자바에서 제공
- java 9 부터 **Deprecated**
- 대안(권장)
    - Flow API : Publisher, Subscriber, Subscription -> 동기/비동기 지원
    - PropertyChangeListener, PropertyChangeSupport
    - 그외 Rx Java, Reactor 등

상태 변경이 일어난 경우 
```java
public class WeatherData extends Observable {
    // 생략
    
    // 상태 변경시 호출하는 메서드
    public void measurementsChanged() {
        // Observable method
        setChanged(); // 상태 변경시 무조건 호출
        notifyObservers();
    }
}
```

Observer 구현체의 경우 
```java
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    // 멤버변수 
    
    public CurrentConditionsDisplay(Observable observable) {
        observable.addObserver(this); // 옵저버(this) 등록
    }

    @Override
    public void display() {
        // 출력문
    }

    // Observer 인터페이스에서 update(..) 구현, subject 상태 변경시 호출
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData) { // 단점. 형 체크, 형 변환
            WeatherData weatherData = (WeatherData)  o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
```




---
<br/>

## 예시. 가상 모니터링 애플리케이션

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/observer/observer2.png?raw=true">

### 옵저버 패턴 적용 전
```java
public class WeatherData {
    
    // 인스턴스 변수 선언
    
    // 데이터 변경 감지시 실행
    public void measurementsChanged() {
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();
        
        // 각 디스플레이를 갱신 (*바뀔 수 있는 부분) 
        currentConditionsDiplay.update(temp, humidity, pressure);
        statisticsDisplay.update(temp, humidity, pressure);
        forecastDisplay.update(temp, humidity, pressure);
    }
}
```

위 코드의 문제점 아래와 같다
<br/>
① 인터페이스가 아닌 구체적인 구현을 바탕으로 코딩하고 있다
<br/>
② 새로운 디스플레이 항목이 추가될 대 마다 코드를 변경해야 한다 (OCP 원칙 위반)
<br/>
③ 실행 중에 디스플레이 항목을 추가하거나 제거할 수 없다 (static, 정적)
<br/>
④ 바뀌는 부분을 캡슐화하고 있지 않다
<br/>
<br/>

### 옵저버 패턴 적용 후 

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/observer/observer3.png?raw=true">

**인터페이스 정의**
```java
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(); // 주제의 상태가 변경되었을 때 모든 옵저버에게 변경 내용을 알리려고 호출하는 메소드
}

public interface Observer {
    void update(float temp, float humidity, float pressure);    
}

public interface DisplayElement {
    void display();
}
```
<br/>

**Subject 인터페이스 구현**
```java
public class WeatherData implements Subject{
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressures;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers) {
            o.update(temperature, humidity, pressures);
        }
    }

    // 가상 스테이션으로부터 갱신된 측정값을 받으면 옵저버에게 알린다
    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressures) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressures = pressures;
        measurementsChanged();
    }

    // getter 생략
}
```
<br/>

**디스플레이 요소(Observer) 구현**
```java
public class CurrentConditionsDisplay implements Observer, DisplayElement{
    private WeatherData weatherData;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this); // 주제에 옵저버(자신)을 등록함
    }

    @Override
    public void display() {
        System.out.println("현재 상태 : 온도 " + temperature + "F, 습도 " + humidity + "%");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        // 상태 변경시 subject -> Observer로 push 하는 방식
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
```
- 위의 예시에서 구현한 방식은 **push 방식**이다
- 만약 상태값을 선택해서 받고 싶은 경우 subject **getter** 호출하는 **pull 방식**으로 observer에서 직접 가져와 사용할 수 있다
- 이 경우 옵저버의 메소드를 일일히 고칠 필요 없어짐 
<br/>

**가상 스테이션 실행 테스트**
```java
public class WeatherStation {
    public static void main(String[] args) {
        // 주제 (subject)
        WeatherData weatherData = new WeatherData();

        // 옵저버
        CurrentConditionsDisplay currentConditionsDisplay
                = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
```
```text
현재 상태 : 온도 80.0F, 습도 65.0%
Avg/Max/Min temperature = 80.0/80.0/200.0
기상예보: 날씨가 좋아지고 있습니다!
체감 온도:82.95535
현재 상태 : 온도 82.0F, 습도 70.0%
Avg/Max/Min temperature = 81.0/82.0/200.0
기상예보: 쌀쌀하며 비가 올 것 같습니다
체감 온도:86.90124
현재 상태 : 온도 78.0F, 습도 90.0%
Avg/Max/Min temperature = 80.0/78.0/200.0
기상예보: 지금과 비슷할 것 같습니다
체감 온도:83.64967
```