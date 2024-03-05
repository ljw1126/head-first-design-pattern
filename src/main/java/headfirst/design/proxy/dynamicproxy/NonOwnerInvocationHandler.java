package headfirst.design.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NonOwnerInvocationHandler implements InvocationHandler {
    Person target;

    public NonOwnerInvocationHandler(Person person) {
        this.target = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            if(method.getName().startsWith("get") || method.getName().equals("setGeekRating")) {
                return method.invoke(target, args);
            }

            if(method.getName().startsWith("set")) { // 다른 사람의 개인정보를 수정할 수 없다
                throw new IllegalAccessException();
            }
        } catch(InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
