package headfirst.design.command.remoteByLambda;

public class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;

    public RemoteControl() {
        this.onCommands = new Command[7];
        this.offCommands = new Command[7];

        for(int i = 0; i < 7; i++) {
            this.onCommands[i] = () -> {};
            this.offCommands[i] = () -> {};
        }
    }
}
