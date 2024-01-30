package headfirst.design.command.remote;

// 커맨드
public class CeilingFanOnCommand implements Command {
    private CeilingFan ceilingFan; // 리시버

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        this.ceilingFan.high();
    }
}
