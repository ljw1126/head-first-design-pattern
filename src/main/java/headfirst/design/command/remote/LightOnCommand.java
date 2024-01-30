package headfirst.design.command.remote;

// 커맨드
public class LightOnCommand implements Command {
    private Light light; // 리시버

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.on();
    }
}
