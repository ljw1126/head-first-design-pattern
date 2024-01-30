package headfirst.design.command.diner;

// 커맨드 객체
public class BurgerAndFiresOrder implements Order {
    Cook cook; // 리시버

    public BurgerAndFiresOrder(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void orderUp() {
        this.cook.makeBurger();
        this.cook.makeFires();
    }
}
