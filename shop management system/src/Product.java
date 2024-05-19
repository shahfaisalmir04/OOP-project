import java.io.Serializable;

public class Product implements Serializable {
    static final long serialVersionUID=-5370455057765203523L;
    private String name,imagepath;
    private int quantity,supplierindex;
    private double price;
    private boolean status, received;

    public Product(String name, int quantity, double price,String imagepath,int supplierindex) {
        status = false;
        received=false;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imagepath=imagepath;
        this.supplierindex=supplierindex;
    }
    public Product(String name, int quantity, double price) {
        status = false;
        received=false;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imagepath=null;
        this.supplierindex=-1;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public int getSupplierindex() {
        return supplierindex;
    }

    public void setSupplierindex(int supplierindex) {
        this.supplierindex = supplierindex;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    @Override
    public String toString() {
        return "\nProduct" +"\n"+
                "Name = " + name  +"\n"+
                "Quantity = " + quantity +"\n"+
                "Price=  " + price +"\n";
    }

    public void display(){
        System.out.println("Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}');
    }

    public int buy(String n,int q){
        if(this.name==n){
            if(quantity<=0){
                return -1;
            }else{
                quantity=quantity-q;
                return 1;
            }
        }else {
            return 0;
        }
    }
}
