package headfirst.design.singleton.example;

public class MultiThreadMain {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Singleton instance = SingletonV3.getInstance();
            System.out.println(instance);
        });
        Thread thread2 = new Thread(() -> {
            Singleton instance = SingletonV3.getInstance();
            System.out.println(instance);
        });

        thread.start();
        thread2.start();
    }
}
