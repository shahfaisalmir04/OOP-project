import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Salesmen_F extends JFrame {
    public static int salesmen_index =0;
    public static int mindex=0;
    JLabel login,ID,registerfirst;
    JLabel Passward;
    JTextField j1;
    JPasswordField j2;
    JButton register,back;
    JButton enter;
    JPanel p1,p2;


    Salesmen_F() {
        this.setSize(800, 500);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setLayout(new GridLayout(9,4));
        p1= new JPanel();
        p1.setLayout(new GridBagLayout());
        p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        this.ID = new JLabel("                     ID");

        this.Passward = new JLabel("                     Password");
        this.j1 = new JTextField(10);
        j1.setSize(20,20);
        this.j2 = new JPasswordField(10);
        registerfirst = new JLabel("Create Account  ");
        login = new JLabel("               Salesmen Log In Portal");
        this.j2.setEchoChar('*');
        this.register = new JButton("Register");
        this.enter = new JButton("Enter");
        back = new JButton("Back");
        MyActionListener a = new MyActionListener();
        this.register.addActionListener(a);
        this.enter.addActionListener(a);
        back.addActionListener(a);
        p1.add(login);
        p1.setBackground(Color.cyan);
        add(p1);

        this.add(this.ID);
        this.add(this.j1);
        this.add(this.Passward);
        this.add(this.j2);

        this.add(this.enter);add(back);
        p2.add(registerfirst);
        p2.add(register);
        add(p2);
        p2.setSize(50,50);
        p1.setBackground(Color.cyan);
        p2.setBackground(Color.cyan);
    }
    public ArrayList<Salesmen> readAllFromFile(){

        ArrayList<Salesmen> stuArr = new ArrayList<Salesmen>();

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Salesmen.ser"));
            while(true){
                Salesmen s = (Salesmen) ois.readObject();
                stuArr.add(s);
            }
        }
        catch(ClassNotFoundException c){
            System.out.println(c);
        }
        catch(EOFException eof){
            System.out.println(eof);
        }
        catch(IOException eee){
            System.out.println(eee);
        }

        return stuArr;

    }
    public class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("Back")){
                dispose();
                new Home();
            }
            else if(ae.getActionCommand().equals("Register")){
                dispose();
                new Register_salesmen();
            }
            else if (ae.getActionCommand().equals("Enter")){
                ArrayList<Salesmen> stuArr =  readAllFromFile();
                int verify = 0;
                for (int i = 0; i < stuArr.size(); i++) {
                    if (stuArr.get(i).getId().equals(j1.getText()) && stuArr.get(i).getPassward().equals(j2.getText())){
                        salesmen_index=i;
                        verify =1;
                        dispose();
                        new Salesmen_Home_F();
                        return;
                    }
                }
                if(verify ==0){
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong Credential ");
                }
            }
        }
    }
}
