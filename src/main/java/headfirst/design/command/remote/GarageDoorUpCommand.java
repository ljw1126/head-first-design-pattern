package headfirst.design.command.remote;

// 커맨드
public class GarageDoorUpCommand implements Command {
    private GarageDoor garageDoor; // 리시버

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        this.garageDoor.up();
    }
}
