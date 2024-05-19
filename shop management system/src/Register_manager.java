import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Register_manager extends JFrame {
    JLabel name,contact,id,passward;

    JTextField j1,j2,j3;
    JPasswordField j4;
    JButton register,back;
    JPanel p1;

    Register_manager(){
        setSize(250, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());


        name = new JLabel("Name");
        contact = new JLabel("Contact");
        id = new JLabel("ID");


        passward = new JLabel("Password");
        j1=new JTextField(20);

        j2=new JTextField(20);
        j3=new JTextField(20);
        j4=new JPasswordField(20);
        j4.setEchoChar('*');
        register = new JButton("Register");
        back = new JButton("Back");
        p1 = new JPanel(new GridLayout(2,1));
        MyActionListener a = new MyActionListener();
        register.addActionListener(a);
        back.addActionListener(a);
        p1.setBackground(Color.cyan);
        add(name);add(j1);
        add(contact);add(j2);
        add(id);add(j3);
        add(passward);add(j4);
        p1.add(register);
        p1.add(back);


        add(p1);
    }
    Manager[] S = new Manager[2];
    public void writeToFile(Manager e){
        try{
            File f = new File("Manager.ser");
            ObjectOutputStream oos;
            if(f.exists()){
                oos = new MyObjectOutputStream(new FileOutputStream(f,true));
            }
            else{
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(e);
            oos.close();
            JOptionPane.showMessageDialog(new JFrame(),"Registered");
        }
        catch(IOException p){
            System.out.println(p);
        }
    }
    public ArrayList<Manager> readAllFromFile() {

        ArrayList<Manager> stuArr = new ArrayList<Manager>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Manager.ser"));
            while (true) {
                Manager s = (Manager) ois.readObject();
                stuArr.add(s);
            }
        } catch (ClassNotFoundException c) {
            System.out.println(c);
        } catch (EOFException eof) {
            System.out.println(eof);
        } catch (IOException eee) {
            System.out.println(eee);
        }
        return stuArr;
    }

    public class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Register")) {
                ArrayList<Manager> list  = readAllFromFile();
                if(j1.getText().isEmpty() && j2.getText().isEmpty() && j3.getText().isEmpty() && j4.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"Fill all Fields");
                }else{
                    if (list.size()==1){
                        JOptionPane.showMessageDialog(new JFrame(),"No more Manager required");
                    }else{
                        File f = new File("Manager.ser");
                        for (int i = 0; i < S.length; i++) {
                            for (int j = 0; j <list.size() ; j++) {
                                if (f.exists()) {
                                    if(list.get(j).getName().equals(j1.getText()) && list.get(j).getContantnum().equals(j2.getText()) && list.get(j).getId().equals(j3.getText()) && list.get(j).getPassward().equals(j4.getText())){
                                        JOptionPane.showMessageDialog(new JFrame(),"Same person can't register twice");
                                        return;
                                    } else if (list.get(j).getName().equals(j1.getText()) && list.get(j).getContantnum().equals(j2.getText())) {
                                        JOptionPane.showMessageDialog(new JFrame(),"Same person can't register twice");
                                        return;
                                    }
                                }
                            }
                            if(S[i]==null){
                                S[i] = new Manager(j1.getText(), j2.getText(), j3.getText(), j4.getText());
                                writeToFile(S[i]);
                                return;
                            }
                        }
                    }
                }
            } else if (ae.getActionCommand().equals("Back")) {
                dispose();
                new Manager_F();
            }
        }
    }

}
