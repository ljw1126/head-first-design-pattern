package headfirst.design.command.party;

// 매크로 커맨드 생성 후 확인
public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
        
        // 리시버 생성
        Light light = new Light("Living Room");
        TV tv = new TV("Living Room");
        Stereo stereo = new Stereo("Living Room");
        HotTube hotTube = new HotTube();

        // 커맨드 생성
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        Command stereoOn = new StereoOnCommand(stereo);
        Command stereoOff = new StereoOffCommand(stereo);

        Command tvOn = new TVOnCommand(tv);
        Command tvOff = new TVOffCommand(tv);

        Command hotTubeOn = new HotTubeOnCommand(hotTube);
        Command hotTubeOff = new HotTubeOffCommand(hotTube);

        // 매크로 커맨드 생성
        Command[] partyOn = {lightOn, stereoOn, tvOn, hotTubeOn};
        Command[] partyOff = {lightOff, stereoOff, tvOff, hotTubeOff};

        Command partyOnCommand = new MacroCommand(partyOn);
        Command partyOffCommand = new MacroCommand(partyOff);

        // 인보커에 커맨드 설정
        remoteControl.setCommand(0, partyOnCommand, partyOffCommand);

        // 컨트롤러 실행
        System.out.println(remoteControl);
        System.out.println("매크로 ON----");
        remoteControl.onButtonWasPushed(0);
        System.out.println("매크로 OFF----");
        remoteControl.offButtonWasPushed(0);
        System.out.println("=====undo");
        remoteControl.undoButtonWasPushed();
    }
}
