package headfirst.design.singleton.example;

public class SingletonV3 implements Singleton {
    private volatile static SingletonV3 uniqueInstance;

    // 기타 인스턴스 변수

    public static SingletonV3 getInstance() {
        if(uniqueInstance == null) {
            synchronized (SingletonV3.class) { // 동기화 블록
                if(uniqueInstance == null) {
                    uniqueInstance = new SingletonV3();
                }
            }
        }
        return uniqueInstance;
    }
}
