package headfirst.design.command.simpleremoteByLambda;

public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();

        // 리시버
        GarageDoor garageDoor = new GarageDoor();
        Light light = new Light();

        // 인보커
        simpleRemoteControl.setCommand(garageDoor::up);
        simpleRemoteControl.buttonWasPressed();
        simpleRemoteControl.setCommand(light::on);
        simpleRemoteControl.buttonWasPressed();
        simpleRemoteControl.setCommand(light::off);
        simpleRemoteControl.buttonWasPressed();
    }
}
