package headfirst.design.singleton.chocolate;

public class ChocolateBoiler {
    private boolean empty;
    private boolean boiled; // 끊이다

    private static ChocolateBoiler chocolateBoiler;

    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public static ChocolateBoiler getInstance() {
        if(chocolateBoiler == null) { // Lazy 방식
            System.out.println("Creating unique instance of Chocolate Boiler");
            chocolateBoiler = new ChocolateBoiler();
        }

        System.out.println("Returning instance of Chocolate Boiler");
        return chocolateBoiler;
    }

    public void fill() {
        if(isEmpty()) { // 보일러가 비어 있을 때
            empty = false;
            boiled = false;
            // 보일러에 우유와 초콜릿을 혼합한 재룔를 넣음
        }
    }

    public void drain() { // 빼내다
        if(!isEmpty() && isBoiled()) {
            // 끓인 재료를 다음 단계로 넘김
            empty = true;
        }
    }

    public void boil() { // 끓이다
        if(!isEmpty() && !isBoiled()) {
            // 재료를 끓임
            boiled = true;
        }
    }

    public  boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }

}
