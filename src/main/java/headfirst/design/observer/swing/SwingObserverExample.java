package headfirst.design.observer.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingObserverExample {
    JFrame jFrame;

    public static void main(String[] args) {
        SwingObserverExample example = new SwingObserverExample();
        example.go();
    }

    private void go() {
        this.jFrame = new JFrame();

        JButton jButton = new JButton("할까?말까?");
        jButton.addActionListener(new AngelListener()); // 리스너(옵저버) 등록, 람다식으로 표현 가능 (생략)
        jButton.addActionListener(new DevilListener());

        this.jFrame.getContentPane().add(BorderLayout.CENTER, jButton);

        // set frame properties
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.getContentPane().add(BorderLayout.CENTER, jButton);
        this.jFrame.setSize(300, 300);
        this.jFrame.setVisible(true);
    }

    class AngelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("하지마! 아마 후회할 걸?");
        }
    }

    class DevilListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("그냥 저질러 버렷!");
        }
    }
}
