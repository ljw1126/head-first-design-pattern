package headfirst.design.proxy.basic;


import headfirst.design.proxy.gumball.GumballMachine;

import java.rmi.RemoteException;

/**
 * Edit Configuration > Program Argument 란에 공백으로 입력
 * 예) Ausein 112
 */
public class GumballMachineTestDrive {
    public static void main(String[] args) throws RemoteException {
        int count = 0;

        if(args.length < 2) {
            System.out.println("GumballMachine <name> <inventory>");
            System.exit(1);
        }

        count = Integer.parseInt(args[1]);
        GumballMachine machine = new GumballMachine(args[0], count);

        GumballMonitor monitor = new GumballMonitor(machine);
        monitor.report();
    }
}
