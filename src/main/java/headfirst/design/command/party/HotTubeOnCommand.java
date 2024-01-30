package headfirst.design.command.party;

// 커맨드
public class HotTubeOnCommand implements Command {
    private HotTube hotTube; // 리시버

    public HotTubeOnCommand(HotTube hotTube) {
        this.hotTube = hotTube;
    }

    @Override
    public void execute() {
        this.hotTube.on();
        this.hotTube.heat();
        this.hotTube.bubblesOn();
    }

    @Override
    public void undo() {
        this.hotTube.off();
    }
}
