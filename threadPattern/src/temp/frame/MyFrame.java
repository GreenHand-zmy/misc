package temp.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    public MyFrame() {
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(new JLabel("Thread-per-Message sample"));
        JButton button = new JButton("execute");
        getContentPane().add(button);
        button.addActionListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
