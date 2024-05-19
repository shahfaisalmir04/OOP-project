import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    JLabel myshop;
    JButton manager,salesman;
    JPanel panel_1,panel2;
    Home(){
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(3,1));
        myshop = new JLabel("Welcome To Shop");
        manager=new JButton("Manager");
        salesman=new JButton("Salesmen");
        MyActionListener a = new MyActionListener();
        manager.addActionListener(a);
        salesman.addActionListener(a);
        panel_1 = new JPanel();
        panel_1.setLayout(new FlowLayout());
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel_1.add(myshop);
        panel2.add(manager);
        panel2.add(salesman);
        panel_1.setBackground(Color.CYAN);

        add(panel_1);
        add(panel2);



    }
    public class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Salesmen")) {
                dispose();
                new Salesmen_F();
            }
            else if (ae.getActionCommand().equals("Manager")) {
                dispose();
                new Manager_F();
            }
        }
    }


}
