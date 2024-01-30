package headfirst.design.command.remote;

// 커맨드
public class LightOffCommand implements Command {
    private Light light; // 리시버

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }
}
