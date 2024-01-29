## 요약 
- 복잡한 서브 시스템의 일련의 인터페이스를 간편하게 사용할 수 있도록 통합 인터페이스를 제공한다 <br/> 
-> 인터페이스를 단순하게 만들어 제공하는 용도로 쓰인다

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/facade/facade.png?raw=true">

<br/><br/>

**참고. 최소 지식 원칙 (Principle of Least Knowledge)**
> 객체 지향 구현시 각 모듈간의 결합도를 최소화하여 설계한다는 원칙

**장점**
- 객체 사이의 의존성/결합도를 줄일 수 있으며, S/W 관리가 더 편해짐

**단점**
- 메소드 호출을 처리하는 Wrapper 클래스를 더 만들어야 할 수 있음 <br/>
->시스템이 복잡해지고, 개발시간도 늘어나고, 성능도 떨어질 수 있다

<br/>

① 최소 지식 원칙을 따르지 않는 경우
```java
public float getTemp() {
  return station.getThermometer().getTemperature();
}
```
메소드 체인이 깊어질수록 아래와 같은 단점을 가진다
- 서로 관련없는 객체의 내부 구조에 접근하게 되어 의존성/결합도가 높아짐
- 코드 가독성 저하된다
- 연쇄 호출된 메소드가 수정되면 다른 메소드에 영향을 줄 수 있다

<br/>

② 최소 지식 원칙을 따르는 경우
```java
public float getTemp(Station station) {
  return station.getTemperature();
}
```

[최소 지식 원칙 가이드 라인]
- 객체 자기 자신의 메소드
- 메소드 인자로 전달된 객체의 메소드
- 멤버 변수 또는 지역 스코프 내에서 생성한 객체의 메소드
- 객체에 속하는 구성요소 (ex. Wrapper 클래스)