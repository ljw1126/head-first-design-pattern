package headfirst.design.command.simpleremote;

public class RemoteControlTest {
    public static void main(String[] args) {
        // 인보커
        SimpleRemoteControl remote = new SimpleRemoteControl();

        // 리시버
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();

        // 커맨드 객체
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);


        remote.setCommand(lightOnCommand); // 인보커는 커맨드 객체를 포함하고 있다
        remote.buttonWasPressed(); // 실행 요청시 커맨드 객체는 리시버에게 실제 요청을 위임
        remote.setCommand(garageDoorOpenCommand);
        remote.buttonWasPressed();
    }
}
