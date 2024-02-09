package headfirst.design.composite.second;


import java.util.Iterator;
import java.util.Stack;

/**
 * 스프링 프레임워크에서 3.0부터 지원
 * 재귀함수 형식으로 호출 처리된다
 * MenuItem.createIterator() 호출하여 NullIterator 반환된다
 * 깊이 우선 탐색 방식으로 동작함
 */
public class CompositeIterator implements Iterator<MenuComponent> {
    Stack<Iterator<MenuComponent>> stack = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        this.stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if(stack.isEmpty()) {
            return false;
        }

        Iterator<MenuComponent> iterator = stack.peek();
        if(iterator.hasNext()) {
            return true;
        } else {
            stack.pop();
            return hasNext();
        }
    }

    @Override
    public MenuComponent next() {
        if(hasNext()) {
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent menuComponent = iterator.next();
            stack.push(menuComponent.createIterator()); // MenuItem - NullIterator, Menu - MenuItem
            return menuComponent;
        }

        return null;
    }
}
