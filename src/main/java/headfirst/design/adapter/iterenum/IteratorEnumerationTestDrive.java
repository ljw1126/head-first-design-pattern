package headfirst.design.adapter.iterenum;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class IteratorEnumerationTestDrive {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        Enumeration<?> enumeration = new IteratorEnumeration(list.iterator());
        while(enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }
}
