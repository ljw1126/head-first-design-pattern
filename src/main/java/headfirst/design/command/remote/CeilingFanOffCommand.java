package headfirst.design.command.remote;

// 커맨드
public class CeilingFanOffCommand implements Command {
    private CeilingFan ceilingFan; // 리시버

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        this.ceilingFan.off();
    }
}
