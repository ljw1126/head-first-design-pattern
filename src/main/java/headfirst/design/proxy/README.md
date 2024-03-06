## 프록시 패턴(Proxy)
특정 객체로의 접근을 제어하는 대리인을 제공하는 패턴

### 원격 프록시
> 원격 객체로의 접근을 제어할 수 있는 프록시

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/proxy/%EC%9B%90%EA%B2%A9%ED%94%84%EB%A1%9D%EC%8B%9C.png?raw=true">
RMI 기술 활용하여 원격 프록시를 구현한다
<br/>
① 서버 측에서는 규격에 따라 Stub/Skelton 프록시 정의하고 RMI 레지스트리에 등록한다(Stub 관리된다)  
<br/> 
② 클라이언트 측에서 요청시 lookup(..) 호출하여 RMI 레지스트리에 있는 Stub 프록시를 가져와 사용한다
<br/>
③ 클라이언트는 Stub 프록시를 실제 객체처럼 요청한다
<br/>
④ Stub 프록시는 네트워크를 통해 Skelton 프록시에 요청하게 되고, 실제 서버 객체에 요청하여 결과 반환하거나 캐싱 결과를 응답한다 
(이때 네트워크 통신 과정은 RPC 프레임워크에서 추상화하여 제공하기 때문에 개발자는 비즈니스 로직에 집중하면 된다)
<br/>
⑤ 네트워크로 받은 응답을 클라이언트 객체에게 전달한다
<br/><br/>

**RMI 용어**
- Stub(스텁) : 클라이언트 보조 객체 
- Skeleton(스켈레톤) : 서비스 보조 객체

**원격 프록시 예제 학습 기록**
- ClassNotFoundException 발생 
- classpath 지정했지만 같은 패지키에 있는 rmi Remote 인터페이스 구현체 못 찾음
- 5시간 투자하였지만, 해결방안 찾지 못해 넘어감
```text
1. jdk 17 -> 11 
- 버전 충돌 발생하여 11로 변경

2. class 생성 (/build/classes/java/main/ 경로에 클래스 생성)
gradle > Tasks > build > clean
gradle > Tasks > build > classes 

3. 터미널에서 실행할 class 경로 이동
$ cd ~gitRepository/head-first-design-pattern/build/classes/java/main/headfirst/design/proxy/gumball

4. stub, skelton 생성
rmic -classpath /home/gitRepository/head-first-design-pattern/build/classes/java/main headfirst.design.proxy.gumball.MyRemoteImpl

5. 레지스트리 백그라운드 실행 
rmiregistry &

6. 서버 프록시 등록 (실패)
$ java -cp .:/home/gitRepository/head-first-design-pattern/build/classes/java/main headfirst.design.proxy.gumball.GumballMachineTestDrive localhost 200

문제1. ClassNotFoundException 발생하였고, Remote 인터페이스 구현체인 GumballMachine 클래스를 찾지 못하였다
문제2. 파라미터에 localhost 외에는 등록 불가했다

7. 클라이언트 확인 
$ java GumballMonitorTestDrive 
```

<br/>

### 가상 프록시
> 생성하기 힘든 자원으로의 접근을 제어하는 프록시

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/proxy/%EA%B0%80%EC%83%81%ED%94%84%EB%A1%9D%EC%8B%9C.png?raw=true">
<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/proxy/%EA%B0%80%EC%83%81%ED%94%84%EB%A1%9D%EC%8B%9CUML.png?raw=true">

- 생성하는데 많은 비용이 드는 객체를 대신한다
- 진짜 객체가 필요한 상황이 오기 전까지 객체의 생성을 미루는 기능을 제공한다 (지연 전략)
- 객체 생성이 끝나면 RealSubject에게 직접 요청을 전달한다

<br/>

### 보호 프록시 
> - 접근 권한이 필요한 객체로의 접근을 제어하는 프록시
> - 자바의 동적 프록시(Dynamic Proxy) 기술 활용하여 프록시 만듦
> - 이때 '동적'의 의미는 런타임에 프록시가 생성됨을 의미 

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/proxy/dynamicProxyUML.png?raw=true">


```java
// 프록시 생성 예시
(Proxy) Proxy.newProxyInstance(
        Person.class.getClassLoader(),
        new Class[]{ Person.class },
        new OwnerInvocationHandler(person)
);
```
<br/>


**예제 학습 - 데이팅 서비스의 문제점** <br/>
① 자기 괴짜 지수를 직접 조작할 수 없어야 한다 <br/> 
② 다른 사람들의 개인정보도 수정할 수 없어야 한다

**해결 방안** 
- [X] InvocationHandler 인터페이스 구현한 클래스에서 메소드 접근을 제어함 <br/>
    - OwnerInvocationHandler : 자기 괴짜 지수를 직접 조작할 수 없도록 접근 제어 
    - NonOwnerInvocationHandler : 다른 사람 개인 정보 수정하는 메소드 호출 접근 제어(단, 괴짜 지수 설정은 허용)
- [X] 자바의 동적 프록시 기술 활용하여 프록시 생성 후 테스트 수행

**출력 결과** 
<br/>
<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/proxy/dynamic-proxy.png?raw=true">

