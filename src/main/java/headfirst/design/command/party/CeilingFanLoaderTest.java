package headfirst.design.command.party;

// 선풍기 코드 테스트
public class CeilingFanLoaderTest {
    public static void main(String[] args) {
        // 인보커 생성
        RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();

        // 리시버 생성
        CeilingFan ceilingFan = new CeilingFan("Living Room");

        // 커맨드 생성
        Command medium = new CeilingFanMediumCommand(ceilingFan);
        Command high = new CeilingFanHighCommand(ceilingFan);
        Command off = new CeilingFanOffCommand(ceilingFan);

        remoteControl.setCommand(0, medium, off);
        remoteControl.setCommand(1, high, off);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();

        remoteControl.onButtonWasPushed(1);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();
    }
}
