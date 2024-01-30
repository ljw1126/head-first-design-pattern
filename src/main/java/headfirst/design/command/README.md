## 요약 
> 요청을 캡슐화하여 호출자(invoker)와 수신자(receiver)를 분리하는 패턴

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/command/command3.png?raw=true">

**장점**
<br/>
- 기존 코드를 변경하지 않고 새로운 커맨드를 만들 수 있다 (OCP 원칙 준수)
- 수신자의 코드가 변경되어도 호출자의 코드는 변경되지 않는다 (캡슐화, SRP 원칙 준수)

**단점**
- 코드가 복잡하고 클래스가 많아진다

---

## 예시. 식당 주문하기
<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/command/command.png?raw=true">

**주문 요청 과정**
<br/>
① 손님(Client)은 주문서(Command)를 작성하여 종업원(Invoker)에게 전달한다
<br/>
② 종업원(Invoker)은 손님은 주문서 목록(Command)을 전달 받은 후, 주문 요청한다
<br/>
③ 주방장(Receiver)은 주문 요청을 처리한다

<br/>

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/command/command2.png?raw=true">

- Invoker는 호출자 역할을 한다
  - Invoker는 여러 Command 정보를 가지고 있고, 클라이언트 요청(**takeOrder()**)에 따라 적절한 Command 요청한다 (**Command.orderUp()실행**)
- Command 객체는 공통 인터페이스를 구현한다
  - Command 구현체 안에는 Recevier가 포함되어 있다. 주문 요청(**orderUp()**) 올 경우 Recevier에서 책임을 위임한다.
    