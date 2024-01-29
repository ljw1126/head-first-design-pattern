package headfirst.design.singleton.subclass;

public class SingletonTestDrive {
    public static void main(String[] args) {
        Singleton cooler = CoolerSingleton.getInstance();
        Singleton hotter = HotterSingleton.getInstance();

        System.out.println(cooler);
        System.out.println(hotter);
        System.out.println(cooler == hotter);
    }
}
