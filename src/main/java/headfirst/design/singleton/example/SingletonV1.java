package headfirst.design.singleton.example;

public class SingletonV1 implements Singleton {
    private static SingletonV1 uniqueInstance;

    // 기타 인스턴스 변수

    public static SingletonV1 getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new SingletonV1(); // 멀티 스레드에서 문제 발생 가능
        }

        return uniqueInstance;
    }
}
