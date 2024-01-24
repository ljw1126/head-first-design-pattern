package headfirst.design.templatemethod.after;

public abstract class CaffeineBeverage {

    // 템플릿 메소드
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();
    abstract void addCondiments();

    void pourInCup() {
        System.out.println("컵에 따르는 중");
    }

    void boilWater() {
        System.out.println("물 끓이는 중");
    }
}
