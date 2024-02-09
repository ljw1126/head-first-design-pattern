package headfirst.design.iterator.first;

import java.util.Iterator;

public class DinerMenuIterator implements Iterator<MenuItem> {
    MenuItem[] items;
    int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if(this.position >= items.length || this.items[this.position] == null) {
            return false;
        }

        return true;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = this.items[position];
        position += 1;
        return menuItem;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("메뉴 항목은 지우면 안 됩니다");
    }
}
