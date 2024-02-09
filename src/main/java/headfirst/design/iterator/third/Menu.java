package headfirst.design.iterator.third;

import java.util.Iterator;

public interface Menu {
    Iterator<MenuItem> createIterator();
}
