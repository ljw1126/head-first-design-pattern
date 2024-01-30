package headfirst.design.command.party;

// 커맨드
public class HotTubeOffCommand implements Command {
    private HotTube hotTube; // 리시버

    public HotTubeOffCommand(HotTube hotTube) {
        this.hotTube = hotTube;
    }

    @Override
    public void execute() {
        this.hotTube.bubblesOff();
        this.hotTube.off();
    }

    @Override
    public void undo() {
        this.hotTube.on();
        this.hotTube.bubblesOn();
        this.hotTube.heat();
    }
}
