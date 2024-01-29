package headfirst.design.singleton.example;

public class SingletonV4 implements Singleton{
    private SingletonV4() {}

    private static class SingletonV4Holder {
        private static final SingletonV4 INSTANCE = new SingletonV4();
    }

    public static SingletonV4 getInstance() {
        return SingletonV4Holder.INSTANCE;
    }
}
