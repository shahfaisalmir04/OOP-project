import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class buyableProduct_F extends JFrame{
    JLabel name,quantity,price,pic;
    JButton add ,back;
    JTextField j1,j2,j3;
    buyableProduct_F(){
        setSize(270, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());
        name = new JLabel("Name");
        quantity = new JLabel("Quantity");
        price = new JLabel("Price");
        pic = new JLabel("Picture");
        add = new JButton("Add");
        back = new JButton("Back");
        j1 = new JTextField(20);
        j2 = new JTextField(20);
        j3 = new JTextField(20);
        MyActionListener a = new MyActionListener();
        add.addActionListener(a);
        back.addActionListener(a);
        add(name);add(j1);
        add(quantity);add(j2);
        add(price);add(j3);
//        add(pic);
        add(add);add(back);
    }
    //Product[] P = new Product[50];
    public void writeToFile(Product e){
        try{
            File f = new File("BuyableProduct.ser");
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
//            Image image;
            if (ae.getActionCommand().equals("Back")) {
                dispose();
                new Manager_Home_F();
            }

            else if(j1.getText().isEmpty() && j2.getText().isEmpty() && j3.getText().isEmpty()){
                JOptionPane.showMessageDialog(new JFrame(),"Fill all Fields");
            }

            else if (ae.getActionCommand().equals("Add")) {
                Product P = new Product(j1.getText(), Integer.parseInt(j2.getText()), Double.parseDouble(j3.getText()));
                System.out.println(P.toString());
                writeToFile(P);
            }

        }
    }
}
