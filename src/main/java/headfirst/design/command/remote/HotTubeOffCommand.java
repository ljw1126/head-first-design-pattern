package headfirst.design.command.remote;

// 커맨드
public class HotTubeOffCommand implements Command {
    private HotTube hotTube; // 리시버

    public HotTubeOffCommand(HotTube hotTube) {
        this.hotTube = hotTube;
    }

    @Override
    public void execute() {
        this.hotTube.off();
    }
}
