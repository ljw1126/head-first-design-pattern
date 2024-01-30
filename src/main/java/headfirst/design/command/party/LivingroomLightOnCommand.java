package headfirst.design.command.party;

// 커맨드
public class LivingroomLightOnCommand implements Command {

    private Light light; // 리시버

    public LivingroomLightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.on();
    }

    @Override
    public void undo() {
        this.light.off();
    }
}
