package headfirst.design.iterator.second;

import java.util.Iterator;
import java.util.List;

public class Waitress {
    List<Menu> menus;

    public Waitress(List<Menu> menus) {
        this.menus = menus;
    }

    public void printMenu() {
        Iterator<Menu> menuIterator = menus.iterator();
        while(menuIterator.hasNext()) {
            Menu menu = menuIterator.next();
            printMenu(menu.createIterator());
            System.out.println();
        }
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
