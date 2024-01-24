package headfirst.design.templatemethod.list;

import java.util.AbstractList;

public class MyStringList extends AbstractList<String> {
    private String[] myList;

    public MyStringList(String[] myList) {
        this.myList = myList;
    }

    @Override
    public String get(int index) {
        return myList[index];
    }

    // 구현하지 않은데 호출할 경우 오류 반환, 그러므로 오버라이딩 해서 기능 구현
    @Override
    public String set(int index, String element) {
        String oldString = myList[index];
        myList[index] = element;
        return oldString;
    }

    @Override
    public int size() {
        return myList.length;
    }
}
