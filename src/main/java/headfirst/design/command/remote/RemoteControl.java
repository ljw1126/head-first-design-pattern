package headfirst.design.command.remote;


// 인보커
public class RemoteControl {
    Command[] onCommand; // 커맨드 객체 (DIP원칙 준수)
    Command[] offCommand; // 커맨드 객체 (DIP원칙 준수)

    public RemoteControl() {
        this.onCommand = new Command[7];
        this.offCommand = new Command[7];

        Command noCommand = new NoCommand();
        for(int i = 0; i < 7; i++) {
            this.onCommand[i] = noCommand;
            this.offCommand[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        this.onCommand[slot] = onCommand;
        this.offCommand[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        this.onCommand[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        this.offCommand[slot].execute();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n------- 리모컨 --------- \n");
        for(int i = 0; i < onCommand.length; i++) {
            sb.append("[slot " + i +"]" + onCommand[i].getClass().getName() + "  " + offCommand[i].getClass().getName() + "\n");
        }

        return sb.toString();
    }
}
