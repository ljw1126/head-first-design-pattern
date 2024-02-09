package headfirst.design.iterator.third;

import java.util.Iterator;
import java.util.List;

public class ArrayListIterator implements Iterator<MenuItem> {
    List<MenuItem> items;
    int position = 0;

    public ArrayListIterator(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return items.size() > position;
    }

    @Override
    public MenuItem next() {
        return items.get(position++);
    }
}
