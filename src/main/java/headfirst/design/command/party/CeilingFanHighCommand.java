package headfirst.design.command.party;


// 커맨드
public class CeilingFanHighCommand implements Command {
    private CeilingFan ceilingFan; // 리시버
    int prevSpeed;

    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        this.prevSpeed = this.ceilingFan.getSpeed();
        this.ceilingFan.high();
    }

    @Override
    public void undo() {
        switch (this.prevSpeed) {
            case CeilingFan.HIGH: 	this.ceilingFan.high(); break;
            case CeilingFan.MEDIUM: this.ceilingFan.medium(); break;
            case CeilingFan.LOW: 	this.ceilingFan.low(); break;
            default: 				this.ceilingFan.off(); break;
        }
    }
}
