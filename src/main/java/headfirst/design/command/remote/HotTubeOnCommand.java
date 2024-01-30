package headfirst.design.command.remote;

// 커맨드
public class HotTubeOnCommand implements Command {
    private HotTube hotTube; // 리시버

    public HotTubeOnCommand(HotTube hotTube) {
        this.hotTube = hotTube;
    }

    @Override
    public void execute() {
        this.hotTube.on();
    }
}
