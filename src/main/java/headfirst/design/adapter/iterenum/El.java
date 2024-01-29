package headfirst.design.adapter.iterenum;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class El {
    public static void main(String[] args) {
        Vector<String> v = new Vector<>(Arrays.asList(args));
        Enumeration<String> e = v.elements();
        while(e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }

        Iterator<String> i = v.iterator();
        while(i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
