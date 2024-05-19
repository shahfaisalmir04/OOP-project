import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Sell_F extends JFrame {
    JLabel name,quanity,price;
    JTextField j1,j2,j3;
    JButton Sell, receipt,back;
    Sell_F(){
        setSize(270, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());
        name = new JLabel("Name");
        quanity = new JLabel("Quantity");
        price = new JLabel("Price");
        j1 = new JTextField(20);
        j2 = new JTextField(20);
        j3 = new JTextField(20);
        Sell = new JButton("Sell");
        receipt = new JButton("Generate Recipt");
        back = new JButton("Back");
        MyActionListener a = new MyActionListener();
        Sell.addActionListener(a);
        receipt.addActionListener(a);
        back.addActionListener(a);
        add(name);add(j1);
        add(quanity);add(j2);
        add(price);add(j3);
        add(Sell);add(receipt);add(back);
    }
    public void writeToFileBuyable(Product e){
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
            // JOptionPane.showMessageDialog(new JFrame(),"Sold");
        }
        catch(IOException p){
            System.out.println(p);
        }
    }
    public void writeToFile(Product e){
        try{
            File f = new File("Buyed Product.ser");
            ObjectOutputStream oos;
            if(f.exists()){
                oos = new MyObjectOutputStream(new FileOutputStream(f,true));
            }
            else{
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(e);
            oos.close();
            JOptionPane.showMessageDialog(new JFrame(),"Sold");
        }
        catch(IOException p){
            System.out.println(p);
        }
    }
    public void writeToFilep(Product e){
        try{
            File f = new File("Product.ser");
            ObjectOutputStream oos;
            if(f.exists()){
                oos = new MyObjectOutputStream(new FileOutputStream(f,true));
            }
            else{
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(e);
            oos.close();
            // JOptionPane.showMessageDialog(new JFrame(),"Sold");
        }
        catch(IOException p){
            System.out.println(p);
        }
    }
    public void writeToFileR(Product e){
        try{
            File f = new File("Receipt.ser");
            ObjectOutputStream oos;
            if(f.exists()){
                oos = new MyObjectOutputStream(new FileOutputStream(f,true));
            }
            else{
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(e);
            oos.close();
            //JOptionPane.showMessageDialog(new JFrame(),"Sold");
        }
        catch(IOException p){
            System.out.println(p);
        }
    }
    public void writeToFileS(Salesmen e){
        try{
            File f = new File("Salesmen.ser");
            ObjectOutputStream oos;
            if(f.exists()){
                oos = new MyObjectOutputStream(new FileOutputStream(f,true));
            }
            else{
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(e);
            oos.close();
            //JOptionPane.showMessageDialog(new JFrame(),"Sold");
        }
        catch(IOException p){
            System.out.println(p);
        }
    }
    public ArrayList<Product> readAllFromFile(){

        ArrayList<Product> stuArr = new ArrayList<Product>();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BuyableProduct.ser"));){

            while(true){
                Product s = (Product) ois.readObject();
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
    public ArrayList<Salesmen> readAllFromFileS(){

        ArrayList<Salesmen> stuArr = new ArrayList<Salesmen>();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Salesmen.ser"));){
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
    public ArrayList<Product> readAllFromFileR(){

        ArrayList<Product> stuArr = new ArrayList<Product>();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Receipt.ser"));){
            while(true){
                Product s = (Product) ois.readObject();
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
            if (ae.getActionCommand().equals("Back")) {
                dispose();
                new Salesmen_Home_F();
            }else
            if(j1.getText().isEmpty() && j2.getText().isEmpty() && j3.getText().isEmpty()){
                JOptionPane.showMessageDialog(new JFrame(),"Fill all Fields");
            }else
            if (ae.getActionCommand().equals("Sell")) {
                ArrayList<Product> list = readAllFromFile();
                ArrayList<Salesmen> salesmen_list = readAllFromFileS();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i).getName()+" -- " +list.get(i).getQuantity());
                }
                int verifyname=0;
                int verifyquantity=0;
                for (int i =0;i<list.size();i++){
                    if(list.get(i).getName().equals(j1.getText())){
                        verifyname=1;
                        if (list.get(i).getQuantity()>=(Integer.parseInt(j2.getText()))){
                            if(list.get(i).getPrice()>Double.parseDouble(j3.getText())){
                                JOptionPane.showMessageDialog(new JFrame(),"Sell in high price.");
                                return;
                            }
                            verifyquantity=1;
                            list.get(i).setQuantity(list.get(i).getQuantity()-Integer.parseInt(j2.getText()));
                            Product p = new Product(j1.getText(),Integer.parseInt(j2.getText()),Double.parseDouble(j3.getText()));
                            writeToFileR(p);
                            writeToFile(p);//recipt writing
                            break;
                        }
//                        else{
//                            JOptionPane.showMessageDialog(new JFrame(),"Not Available\nQuantities available in mart are "+list.get(i).getName()+" Quantity-"+list.get(i).getQuantity());
//                            break;
//                        }
                    }
//                    else{
//                        JOptionPane.showMessageDialog(new JFrame(),"Not Available\nProducts available in mart are "+list.get(i).getName()+" Quantity-"+list.get(i).getQuantity());
//                        break;
//                    }
                }
                if(verifyname==0){
                    JOptionPane.showMessageDialog(new JFrame(),"Name you enter not exist");
                    return;
                }
                if(verifyquantity==0){
                    JOptionPane.showMessageDialog(new JFrame(),"Not that much quantity available");
                    return;
                }
                double earning = Double.parseDouble(j3.getText());
                int items = Integer.parseInt(j2.getText());
                salesmen_list.get(0).display();
                salesmen_list.get(0).setSalary(((salesmen_list.get(0).getSalary())+items*(earning/100)*30));
                salesmen_list.get(0).setNum_items_Sold(salesmen_list.get(0).getNum_items_Sold()+items);
                System.out.println(salesmen_list.get(0).getNum_items_Sold());
                salesmen_list.get(0).display();
                File S = new File("Salesmen.ser");
                S.delete();
                for (int i = 0; i < salesmen_list.size(); i++) {
                    writeToFileS(salesmen_list.get(i));
                }
                File f = new File("BuyableProduct.ser");
                f.delete();
                for (int i = 0; i < list.size(); i++) {
                    writeToFileBuyable(list.get(i));
                }
                ArrayList<Product> list2 = readAllFromFile();
                for (int i = 0; i < list2.size(); i++) {
                    System.out.println(list2.get(i).getName()+" -- " +list2.get(i).getQuantity());
                }
            } else if (ae.getActionCommand().equals("Generate Recipt")) {
                ArrayList<Product> list = readAllFromFileR();
                File z = new File("Receipt.ser");
                String s = "";
                for (int i = 0; i < list.size(); i++) {
                    s = s + list.get(i).toString();
                }
                JOptionPane.showMessageDialog(new JFrame(),s);
                z.delete();
            }
        }
    }
}
