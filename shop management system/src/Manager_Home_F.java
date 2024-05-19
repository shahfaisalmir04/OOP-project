import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Manager_Home_F extends JFrame {
    JLabel name;
    JButton  addProduct,addbills,demandingproduct,update,allproduct,profit,back;
    JPanel panel ,p1;
    Manager_Home_F(){
        panel=new JPanel();
        panel.setBounds(40,80,200,200);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new GridLayout(8,1));
        p1 =new JPanel(new GridBagLayout());
        p1.setBackground(Color.cyan);
        name = new JLabel("Manager Portal");
        addProduct = new JButton("Add Product");
        addbills = new JButton("Add Bills");
        demandingproduct = new JButton("Products on Demand");
        update = new JButton("Update");
        allproduct = new JButton("All Products in Shop");
        back = new JButton("Logout");
        profit = new JButton("Check Profit");
        MyActionListener a = new MyActionListener();
        addProduct.addActionListener(a);
        addbills.addActionListener(a);
        demandingproduct.addActionListener(a);
        update.addActionListener(a);
        allproduct.addActionListener(a);
        profit.addActionListener(a);
        back.addActionListener(a);
        p1.add(name);
        add(p1);
        add(addProduct);add(addbills);add(allproduct);add(demandingproduct);add(profit);add(back);
    }
    public ArrayList<Product> readAllFromFile(){

        ArrayList<Product> stuArr = new ArrayList<Product>();

        try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Product.ser"));){
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
    public ArrayList<Product> readAllFromFilebuyed(){

        ArrayList<Product> stuArr = new ArrayList<Product>();

        try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Buyed Product.ser"));){
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
    public ArrayList<Bills> readAllFromFilebill(){

        ArrayList<Bills> stuArr = new ArrayList<Bills>();

        try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Bills.ser"));){
            while(true){
                Bills s = (Bills) ois.readObject();
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
    public void writeToFile(Product e){
        try{
            File f = new File("Product.ser");
            ObjectOutputStream oos;
            if(f.exists()){
                oos = new MyObjectOutputStream(new FileOutputStream(f,true));// not appending
            }
            else{
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }

            oos.writeObject(e);
            oos.close();

        }
        catch(IOException p){
            System.out.println(p);
        }
    }
    public ArrayList<Manager> readAllFromFileM() {
        ArrayList<Manager> stuArr = new ArrayList();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Manager.ser"));){
            while(true) {
                Manager s = (Manager)ois.readObject();
                stuArr.add(s);
            }
        } catch (ClassNotFoundException var4) {
            System.out.println(var4);
        } catch (EOFException var5) {
            System.out.println(var5);
        } catch (IOException var6) {
            System.out.println(var6);
        }

        return stuArr;
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
    public void writeToFileBuy(Product e){
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
            if(ae.getActionCommand().equals("Logout")){
                dispose();
                new Manager_F();
            }
            else if (ae.getActionCommand().equals("Add Product")) {
                dispose();
                new buyableProduct_F();
            }else if (ae.getActionCommand().equals("Add Bills")) {
                dispose();
                new Bills_F();
            } else if (ae.getActionCommand().equals("Products on Demand")) {
                ArrayList<Product> list2 =Manager_Home_F.this.readAllFromFilep();
                boolean verify = true;
                for (int i = 0; i < list2.size(); i++) {
                    if (list2.get(i).getQuantity()<3) {
                        verify =false;
                        JOptionPane.showMessageDialog(new JFrame(),list2.get(i).toString());
                    }
                }if(verify==true){
                    JOptionPane.showMessageDialog(new JFrame(),"No Products on demand");
                }// profit / remaining items <5
            } else if (ae.getActionCommand().equals("All Products in Shop")) {
                ArrayList<Product> list =readAllFromFilep();
                String s = "";
                for (int i = 0; i < list.size(); i++) {
                    s=s+list.get(i).toString()+"\n";
                }JOptionPane.showMessageDialog(new JFrame(),s);
            } else if (ae.getActionCommand().equals("Check Profit")) {
                ArrayList<Bills> bill = readAllFromFilebill();
                ArrayList<Product> pricelist= readAllFromFilep();
                ArrayList<Product> buyedpricelist = readAllFromFilebuyed();
                double totalbill = 0;
                for (int i = 0; i < bill.size(); i++) {
                    totalbill=totalbill+bill.get(i).totalbill();
                }
                double totalbuy =0;
                for (int i = 0; i < pricelist.size(); i++) {
                    totalbuy=totalbuy+pricelist.get(i).getPrice();
                }
                double totalbuyed=0;
                for (int i = 0; i < buyedpricelist.size(); i++) {
                    totalbuyed=totalbuyed+(buyedpricelist.get(i).getPrice()*buyedpricelist.get(i).getQuantity());
                }
                double profit = totalbuyed-totalbill+totalbuy;
                JOptionPane.showMessageDialog(new JFrame(),"Your current budget is "+profit+" $/-");

            }

        }
    }

}
