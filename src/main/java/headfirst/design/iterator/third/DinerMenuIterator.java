package headfirst.design.iterator.third;

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
        if(this.position <= 0) {
            throw new IllegalStateException();
        }

        // next() 호출 후 remove() 호출하니깐 position += 1 된 상태
        if(items[position - 1] != null) {
            // shift
            for(int i = position - 1; i < (items.length - 1); i++) {
                items[i] = items[i - 1];
            }
            items[items.length - 1] = null;
        }
    }
}
