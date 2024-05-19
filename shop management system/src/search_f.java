import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class search_f extends JFrame {
    JLabel name;
    JTextField j1;
    JButton search,back;
    search_f(){
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());
        name = new JLabel("Name");
        j1 = new JTextField(20);
        search =new JButton("Search");
        back = new JButton("Back");
        MyActionListener a = new MyActionListener();
        search.addActionListener(a);
        back.addActionListener(a);
        add(name);add(j1);add(search);add(back);
    }
    public ArrayList<Product> readAllFromFilep() {

        ArrayList<Product> stuArr = new ArrayList<Product>();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BuyableProduct.ser"));) {
            while (true) {
                Product s = (Product) ois.readObject();
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
            if (ae.getActionCommand().equals("Back")) {
                dispose();
                new Salesmen_Home_F();
            } else if (ae.getActionCommand().equals("Search")) {
                ArrayList<Product> list = readAllFromFilep();
                int verify =0;
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getName().equals(j1.getText())){
                        verify=1;
                        JOptionPane.showMessageDialog(new JFrame(),list.get(i).toString());
                    }
                }if(verify==0){
                    JOptionPane.showMessageDialog(new JFrame(),"Product not found");
                }
            }
        }
    }
}
