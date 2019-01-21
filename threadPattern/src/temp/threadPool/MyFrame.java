package temp.threadPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    private final JLabel label = new JLabel("event dispatching thread simple");
    private final JButton button = new JButton("countUp");

    public MyFrame() throws HeadlessException {
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(label);
        getContentPane().add(button);
        button.addActionListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        countUp();
    }

    private synchronized void countUp() {
        for (int i = 0; i < 10; i++) {
            label.setText("" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
