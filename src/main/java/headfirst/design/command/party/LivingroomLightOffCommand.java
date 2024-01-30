package headfirst.design.command.party;

// 커맨드
public class LivingroomLightOffCommand implements Command {

    private Light light; // 리시버

    public LivingroomLightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }

    @Override
    public void undo() {
        this.light.on();
    }
}
