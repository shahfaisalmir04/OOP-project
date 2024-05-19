import java.io.Serializable;

public class Person implements Serializable {
    private String name,contantnum;

    public Person(String name, String contantnum) {
        this.name = name;
        this.contantnum = contantnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContantnum() {
        return contantnum;
    }

    public void setContantnum(String contantnum) {
        this.contantnum = contantnum;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", contantnum='" + contantnum + '\'' +
                '}';
    }
    public void display(){
        System.out.println("Person{" +
                "name='" + name + '\'' +
                ", contantnum='" + contantnum + '\'' +
                '}');
    }
}

