package headfirst.design.command.party;

// 커맨드
public class StereoOnCommand implements Command {

    private Stereo stereo; // 리시버

    public StereoOnCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        this.stereo.on();
    }

    @Override
    public void undo() {
        this.stereo.off();
    }
}
