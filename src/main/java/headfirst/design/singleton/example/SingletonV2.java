package headfirst.design.singleton.example;

public class SingletonV2 implements Singleton {
    // 즉시(eagar) 생성 방식
    // 클래스 로딩시 JVM에서 Singleton 인스턴스를 생성
    private static SingletonV2 uniqueInstance = new SingletonV2();

    // 기타 인스턴스 변수

    public static SingletonV2 getInstance() {
        return uniqueInstance;
    }
}
