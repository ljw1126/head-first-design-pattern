package headfirst.design.command.party;

// 커맨드
public class StereoOffCommand implements Command {

    private Stereo stereo; // 리시버

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        this.stereo.off();
    }

    @Override
    public void undo() {
        this.stereo.on();
    }
}
