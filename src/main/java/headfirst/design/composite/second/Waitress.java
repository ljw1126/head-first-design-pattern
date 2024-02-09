package headfirst.design.composite.second;

import java.util.Iterator;

public class Waitress {
    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();
    }

    /**
     * 추가, 신규 CompositeIterator 클래스 메소드로 동작
     * 상당히 복잡한 구조를 가진다
     */
    public void printVegetarianMenu() {
        Iterator<MenuComponent> iterator = allMenus.createIterator();

        System.out.println("\n Vegetarian Menu\n----");
        while(iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            try {
                if (menuComponent.isVegetarian()) {
                    menuComponent.print();
                }
            } catch (UnsupportedOperationException ex){} // Menu 경우 isVegetarian() 없음
        }
    }
}
