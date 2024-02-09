package headfirst.design.composite.second;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu extends MenuComponent {
    Iterator<MenuComponent> iterator = null;
    List<MenuComponent> menuComponents = new ArrayList<>();
    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void print() {
        System.out.print("\n" + getName());
        System.out.println("-----------" + getDescription());

        Iterator<MenuComponent> menuComponentIterator = menuComponents.iterator();
        while(menuComponentIterator.hasNext()) {
            MenuComponent menuComponent = menuComponentIterator.next();
            menuComponent.print();
        }
    }

    //지연 전략, 캐싱 지원
    public Iterator<MenuComponent> createIterator() {
        if(iterator == null) {
            iterator = new CompositeIterator(menuComponents.iterator());
        }

        return iterator;
    }
}
