package headfirst.design.templatemethod.after;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoffeeWithHook extends CaffeineBeverageWithBook{

    @Override
    void brew() {
        System.out.println("필터로 커피를 우려내는 중");
    }

    @Override
    void addCondiments() {
        System.out.println("우유와 설탕을 추가하는 중");
    }

    // 후크를 오버라이드해서 원하는 기능을 넣습니다
    @Override
    boolean customerWantsCondiments() {
        String answer = getUserInput();

        return "y".equals(answer.toLowerCase());
    }

    private String getUserInput() {
        String answer = null;

        System.out.println("커피에 우유와 설탕을 넣을까요 (y/n)");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = br.readLine();
        } catch (IOException ex) {
            System.out.println("IO 오류");
        }

        if(answer == null) {
            return "n";
        }

        return answer;
    }
}
