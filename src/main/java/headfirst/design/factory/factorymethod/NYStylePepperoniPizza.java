package headfirst.design.factory.factorymethod;

public class NYStylePepperoniPizza extends Pizza{
    public NYStylePepperoniPizza() {
        name = "뉴욕 스타일 소스와 페페로니 피자";
        dough = "씬 크러스트 도우";
        sauce = "마리나라 소스";

        toppings.add("모짜렐라 치즈 추가");
    }
}
