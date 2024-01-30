package headfirst.design.command.remote;

// 커맨드
public class GarageDoorDownCommand implements Command {
    private GarageDoor garageDoor; // 리시버

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        this.garageDoor.down();
    }
}
