package headfirst.design.proxy.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MatchMakingTestDrive {
    Map<String, Person> DATING_DB = new HashMap<>();

    public static void main(String[] args) {
        MatchMakingTestDrive test = new MatchMakingTestDrive();
        test.drive();
    }

    public MatchMakingTestDrive() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        Person joe = new PersonImpl();
        joe.setName("김자바");
        joe.setInterests("cars, computers, music");
        joe.setGeekRating(7);
        DATING_DB.put(joe.getName(), joe);

        Person kelly = new PersonImpl();
        kelly.setName("켈리");
        kelly.setInterests("ebay, movies, music");
        kelly.setGeekRating(6);
        DATING_DB.put(kelly.getName(), kelly);
    }

    private void drive() {
        Person joe  = getPersonFromDatabase("김자바");
        Person ownerProxy = getOwnerProxy(joe);

        System.out.println("이름 :" + ownerProxy.getName());
        System.out.println("본인 프록시에 관심 사항을 등록합니다");
        ownerProxy.setInterests("볼링, 바둑");

        try {
            ownerProxy.setGeekRating(100);
        } catch (Exception e) {
            System.out.println("본인의 괴짜 지수를 매길 수 없습니다");
        }

        Person nonProxy = getNonOwnerProxy(joe);
        System.out.println("이름 :" + nonProxy.getName());
        try {
            nonProxy.setInterests("볼링, 바둑");
        } catch (Exception e) {
            System.out.println("타인의 관심사항을 변경할 수 없습니다");
        }

        System.out.println("타인의 괴짜 지수를 매깁니다");
        nonProxy.setGeekRating(3);
        System.out.println("괴짜 지수 : " + nonProxy.getGeekRating());
    }

    private Person getPersonFromDatabase(String name) {
        return DATING_DB.get(name);
    }

    Person getOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                Person.class.getClassLoader(),
                new Class[]{ Person.class },
                new OwnerInvocationHandler(person)
        );
    }

    Person getNonOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                Person.class.getClassLoader(),
                new Class[]{ Person.class },
                new NonOwnerInvocationHandler(person)
        );
    }
}
