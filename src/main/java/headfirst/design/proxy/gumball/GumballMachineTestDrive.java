package headfirst.design.proxy.gumball;

import java.rmi.Naming;

public class GumballMachineTestDrive {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("GumballMachine <name> <inventory>");
            System.exit(1);
        }

        try {
            int count = Integer.parseInt(args[1]);
            GumballMachine machine = new GumballMachine(args[0], count); // ClassNotFoundException 해결 못함
            Naming.bind("//" + args[0] + "/gumballmachine", machine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
