import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Bills_F extends JFrame{
    JLabel ELectricityBill,Rent,Tax;
    JButton add ,back;
    JTextField j1,j2,j3;
    Bills_F(){
        setSize(270, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());
        ELectricityBill = new JLabel("ELectricityBill");
        Rent = new JLabel("Rent");
        Tax = new JLabel("Tax");
        add = new JButton("Add");
        back = new JButton("Back");
        j1 = new JTextField(20);
        j2 = new JTextField(20);
        j3 = new JTextField(20);
        MyActionListener a = new MyActionListener();
        add.addActionListener(a);
        back.addActionListener(a);
        add(ELectricityBill);add(j1);
        add(Rent);add(j2);
        add(Tax);add(j3);
        add(add);add(back);
    }
    public void writeToFile(Bills e){
        try{
            File f = new File("Bills.ser");
            ObjectOutputStream oos;
            if(f.exists()){
                oos = new MyObjectOutputStream(new FileOutputStream(f,true));
            }
            else{
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(e);
            oos.close();
            JOptionPane.showMessageDialog(new JFrame(),"Added");
        }
        catch(IOException p){
            System.out.println(p);
        }
    }
    public class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Back")) {
                dispose();
                new Manager_Home_F();
            }
            else if(j1.getText().isEmpty() && j2.getText().isEmpty() && j3.getText().isEmpty()){
                JOptionPane.showMessageDialog(new JFrame(),"Fill all Fields");
            }
            else if (ae.getActionCommand().equals("Add")) {
                Bills B = new Bills(Double.parseDouble(j1.getText()), Double.parseDouble(j2.getText()), Double.parseDouble(j3.getText()));
                writeToFile(B);
            }
        }
    }
}
