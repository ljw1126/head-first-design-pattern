package headfirst.design.proxy.gumball;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 서버에서 RMI 레지스트리에 스텁 등록
 * - 원격 서비스 객체 역할/기능을 수행 하기 위해 UnicastRemoteObject 확장
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    private static final long serialVersionUID = 1L;

    public MyRemoteImpl() throws RemoteException {}

    @Override
    public String sayHello() throws RemoteException {
        return "Server says, 'Hey'";
    }

    public static void main(String[] args) {
        try {
            MyRemote service = new MyRemoteImpl(); // 원격 객체 인스턴스 생성
            Naming.rebind("RemoteHello", service); // 레지스트리에 스텁 등록
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
