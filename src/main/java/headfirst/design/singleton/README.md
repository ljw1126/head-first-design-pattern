## 요약 
> 클래스 인스턴스를 하나만 만들고 해당 인스턴스로의 전역(글로벌) 접근 방법을 제공하는 패턴
- 자바에서 싱글톤 패턴을 구현할 때는 private 생성자와 정적 메소드, 정적 변수를 사용함
- 클래스 로더가 여러 개 있으면, 방식에 따라 싱글톤이 제대로 작동하지 않고, 여러 개 인스턴스가 생성될 수 있음
<br/>
-> 자바의 enum을 쓰면 간단하게 싱글톤을 구현할 수 있다 (Java 1.5 이상 지원)

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/singleton/singleton.png?raw=true">

---

## 싱글톤 구현 방법
### 1. getInstance() 메소드를 동기화하는 방법
```java
public class Singleton {
    private static Singleton uniqueInstance;

    // 기타 인스턴스 변수
    
    public static Singleton getInstance() {
        if(uniqueInstance == null) { // Lazy 초기화 방식
            uniqueInstance = new Singleton(); // 멀티 스레드에서 문제 발생 가능
        }

        return uniqueInstance;
    }
}
```
**장점**
<br/>
구현이 직관적이고, 간단하다

**단점**
<br/>
thread safe 하지 않아, 멀티 스레드 환경에서 여러 인스턴스가 생성될 수 있다 
<br/>
-> "오직 하나의 인스턴스만을 생성하고 제공해야 한다"는 규칙을 위배할 수 있다

<br/>

### 2. 인스턴스를 시작하자마자 만드는 방법
```java
public class Singleton {
    // 즉시(eagar) 초기화 방식
    // 클래스 로딩시 JVM에서 Singleton 인스턴스를 생성
    private static Singleton uniqueInstance = new Singleton();

    // 기타 인스턴스 변수

    public static Singleton getInstance() {
        return uniqueInstance;
    }
}
```
**장점**
- 코드가 간결하고, 클래스 로딩 시점에 인스턴스가 생성되기 때문에 Lazy Initialization과 관련된 복잡성이 없다
- 정적 필드는 클래스 레벨에서 공유되므로, 스레드 안전성을 보장한다

**단점**
- Lazy Initialization가 불가능하다
- 미리 생성된 인스턴스가 메모리 리소스를 차지하여 낭비 요인이 될 수 있다

<br/>

### 3. DCL (Double-Checked Locking)
```java
public class Singleton {
    private volatile static Singleton uniqueInstance;

    // 기타 인스턴스 변수

    public static Singleton getInstance() {
        if(uniqueInstance == null) {
            synchronized (Singleton.class) { // 동기화 블록
                if(uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```
**장점**
- Lazy Initialization 지원한다 -> 인스턴스 필요 시점에 생성되므로, 초기 부하를 감소시킬 수 있다
- synchronized 키워드를 사용하여 전체 메소드를 동기화하는 것보다 세밀한 동기화 제공하여 성능을 향상 시킬 수 있다
- thread safe하다

**단점**
- 구현이 복잡하다 -> 이해하려면 history 알아야 한다
- 플랫폼 종속적이다 -> Java 1.5 이상 지원
- 가독성 저하

<br/>

### 4. enum 클래스 사용
```java
public enum Singleton {
    UNIQUE_INSTANCE
}
```

**장점**
- type safety -> compile 시점에 타입 체크 지원
- thread safety -> 싱글톤 인스턴스를 보장하기 때문에 멀티스레드 환경에서도 안전성 보장됨
- 간단한 구현 -> 암묵적으로 private 생성자로 선언되므로 외부에서 인스턴스화를 막을 필요 없다
- Reflection, 직렬화/역직렬화 방식으로 부터 안전하다

**단점**
- 확장성이 제한됨 -> 상속을 사용할 수 없다. 이는 enum의 기능이나 동작을 확장하는데 제한을 가할 수 있다
- 플랫폼 종속적이다 -> Java 1.5 이상 지원