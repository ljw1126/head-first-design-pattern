package headfirst.design.command.dinerLambda;

@FunctionalInterface
public interface Order {
    void orderUp(); // 커맨드 객체 실행 명령어
}
