package headfirst.design.templatemethod.after;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeaWithHook extends CaffeineBeverageWithBook{
    @Override
    void brew() {
        System.out.println("찻잎을 우려내는 중");
    }

    @Override
    void addCondiments() {
        System.out.println("레몬을 추가하는 중");
    }

    @Override
    boolean customerWantsCondiments() {
        String answer = getUserInput();

        return "y".equals(answer.toLowerCase());
    }

    private String getUserInput() {
        String answer = null;

        System.out.println("차에 레몬을 추가할까요 (y/n)");

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
