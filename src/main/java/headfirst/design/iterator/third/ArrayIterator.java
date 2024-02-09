package headfirst.design.iterator.third;

import java.util.Iterator;

public class ArrayIterator implements Iterator<MenuItem> {
    MenuItem[] items;
    int position = 0;

    public ArrayIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if(position >= items.length || items[position] == null) {
            return false;
        }

        return true;
    }

    @Override
    public MenuItem next() {
        return items[position++];
    }
}
