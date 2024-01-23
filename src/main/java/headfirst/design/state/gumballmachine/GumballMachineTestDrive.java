package headfirst.design.state.gumballmachine;

public class GumballMachineTestDrive {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);

        System.out.println(gumballMachine);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine); // 4

        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter(); // 동전 꺼내기
        gumballMachine.turnCrank();

        System.out.println(gumballMachine); // 4

        gumballMachine.insertQuarter(); // 동전 넣기
        gumballMachine.turnCrank(); // 손잡이 돌리기
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.ejectQuarter(); // 동전꺼내기

        System.out.println(gumballMachine); // 2

        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter(); // SOLD_OUT 상태
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);
    }
}
