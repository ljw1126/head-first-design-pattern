package headfirst.design.iterator.first;

import java.util.Iterator;

public interface Menu {
    Iterator<MenuItem> createIterator();
}
