package headfirst.design.iterator.third;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuTestDrive {
    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<>();
        menus.add(new PancakeHouseMenu()); // ArrayList
        menus.add(new DinerMenu()); // Array
        menus.add(new CafeMenu()); // HashMap

        Waitress waitress = new Waitress(menus);
        waitress.printMenu();
        waitress.printVegetarianMenu();

        System.out.println("\nCustomer asks, is 핫도그 vegetarian?");
        System.out.print("Waitress says: ");
        if (waitress.isMenuItemVegetarian("핫도그")) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        System.out.println("\nCustomer asks, are 와플 vegetarian?");
        System.out.print("Waitress says: ");
        if (waitress.isMenuItemVegetarian("와플")) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        System.out.println("==========================");
        printMenus();
    }

    /**
     * Waitress 없는 경우 메뉴 출력시 아래와 같은 코드가 필요함
     */
    private static void printMenus() {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();

        List<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItems();
        MenuItem[] lunchItems = dinerMenu.getMenuItems();

        // print as Iterable
        printMenu(breakfastItems);
        printMenu(Arrays.asList(lunchItems));

        // print with forEach
        System.out.println("=== forEach ===");
        breakfastItems.forEach(System.out::println);
        Arrays.asList(lunchItems).forEach(System.out::println);
        System.out.println("=== forEach ===");

        // Using enhanced for loop
        System.out.println("Using enhanced for");
        for (MenuItem menuItem : breakfastItems) {
            System.out.print(menuItem.getName());
            System.out.println("\t\t" + menuItem.getPrice());
            System.out.println("\t" + menuItem.getDescription());
        }
        for (MenuItem menuItem : lunchItems) {
            System.out.print(menuItem.getName());
            System.out.println("\t\t" + menuItem.getPrice());
            System.out.println("\t" + menuItem.getDescription());
        }

        System.out.println();

        // Exposing(노출) implementation
        System.out.println("USING FOR LOOPS");
        for (int i = 0; i < breakfastItems.size(); i++) {
            MenuItem menuItem = breakfastItems.get(i);
            System.out.print(menuItem.getName());
            System.out.println("\t\t" + menuItem.getPrice());
            System.out.println("\t" + menuItem.getDescription());
        }

        for (int i = 0; i < lunchItems.length; i++) {
            MenuItem menuItem = lunchItems[i];
            System.out.print(menuItem.getName());
            System.out.println("\t\t" + menuItem.getPrice());
            System.out.println("\t" + menuItem.getDescription());
        }
    }

    private static void printMenu(Iterable<MenuItem> items) {
        for(MenuItem menuItem : items) {
            System.out.print(menuItem.getName());
            System.out.println("\t\t" + menuItem.getPrice());
            System.out.println("\t" + menuItem.getDescription());
        }
    }
}
