package headfirst.design.command.party;

public class TVOnCommand implements Command {
    private TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.on();
        this.tv.setInputChannel();
    }

    @Override
    public void undo() {
        this.tv.off();
    }
}
