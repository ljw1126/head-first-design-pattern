package headfirst.design.iterator.third;

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

    public void printVegetarianMenu() {
        System.out.println("\nVegetarian menu\n----------");
        Iterator<Menu> iterator = menus.iterator();
        while(iterator.hasNext()) {
            Menu menu = iterator.next();
            printVegetarianMenu(menu);
            System.out.println();
        }
    }

    private void printVegetarianMenu(Menu menu) {
        Iterator<MenuItem> menuItemIterator = menu.createIterator();
        while(menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();

            if(menuItem.isVegetarian()) {
                System.out.print(menuItem.getName() + ", ");
                System.out.print(menuItem.getPrice() + " -- ");
                System.out.println(menuItem.getDescription());
            }
        }
    }

    public boolean isMenuItemVegetarian(String name) {
        Iterator<Menu> menuIterator = menus.iterator();
        while(menuIterator.hasNext()) {
            Menu menu = menuIterator.next();
            if(isVegetarian(name, menu)) {
                return true;
            }
        }

        return false;
    }

    private boolean isVegetarian(String name, Menu menu) {
        Iterator<MenuItem> menuItemIterator = menu.createIterator();
        while(menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            if(menuItem.isVegetarian() && menuItem.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }
}
