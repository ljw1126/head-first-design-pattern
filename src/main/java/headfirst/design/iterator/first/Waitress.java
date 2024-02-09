package headfirst.design.iterator.first;

import java.util.Iterator;

public class Waitress {
    Menu pancakeHouseMenu;
    Menu dinerMenu;

    public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> pancakeIterator = this.pancakeHouseMenu.createIterator();
        Iterator<MenuItem> dinerIterator = this.dinerMenu.createIterator();

        System.out.println("메뉴\n---\n아침 메뉴");
        printMenu(pancakeIterator);
        System.out.println("\n점심 메뉴");
        printMenu(dinerIterator);
    }

    private void printMenu(Iterator<MenuItem> iterator) {
        while(iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            System.out.print(menuItem.getName() + ", ");
            System.out.print(menuItem.getPrice() + "---");
            System.out.print(menuItem.getDescription());
            System.out.println();
        }
    }
}
