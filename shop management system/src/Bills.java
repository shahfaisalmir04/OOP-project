import java.io.Serializable;

public class Bills implements Serializable {
    private double elecbill,rent,tax;

    public Bills(double elecbill, double rent, double tax) {
        this.elecbill = elecbill;
        this.rent = rent;
        this.tax = tax;
    }

    public double getElecbill() {
        return elecbill;
    }

    public void seotElecbill(double elecbill) {
        this.elecbill = elecbill;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
    public double totalbill(){
        return elecbill+tax+rent;
    }
}
