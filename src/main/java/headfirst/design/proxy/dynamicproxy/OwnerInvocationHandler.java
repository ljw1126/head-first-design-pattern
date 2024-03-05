package headfirst.design.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OwnerInvocationHandler implements InvocationHandler {
    Person target;

    public OwnerInvocationHandler(Person person) {
        this.target = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        Object result = null;
        try {
            if(method.getName().equals("setGeekRating")) { // 자기의 괴짜 지수를 직접 올리면 안된다
                throw new IllegalAccessException();
            }

            result = method.invoke(target, args);
        } catch(InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }
}
