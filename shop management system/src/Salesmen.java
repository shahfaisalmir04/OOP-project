import java.io.Serializable;

public class Salesmen extends Person implements Serializable {
    private String id;
    private double salary;
    private String  passward;
    private int Num_items_Sold;

    public Salesmen(String name, String contantnum, String id,  String  passward) {
        super(name, contantnum);
        this.id = id;
        this.salary = 0;
        this.passward = passward;
        Num_items_Sold=0;
    }

    public int getNum_items_Sold() {
        return Num_items_Sold;
    }

    public void setNum_items_Sold(int num_items_Sold) {
        Num_items_Sold = num_items_Sold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String  passward) {
        this.passward = passward;
    }

    @Override
    public String toString() {
        return super.toString()+"Salesmen{" +
                "id='" + id + '\'' +
                ", salary=" + salary +
                ", passward=" + passward +
                '}';
    }
    public void display(){
        System.out.println(super.toString()+"Salesmen{" +
                "id='" + id + '\'' +
                ", salary=" + salary +
                ", passward=" + passward +
                '}');
    }
}
