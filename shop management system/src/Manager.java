import java.io.Serializable;

public class Manager extends Person implements Serializable {
    private String id,passward;

    public Manager(String name, String contantnum, String id, String passward) {
        super(name, contantnum);
        this.id = id;
        this.passward = passward;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    @Override
    public String toString() {
        return super.toString()+"Manager{" +
                "id='" + id + '\'' +
                ", passward='" + passward + '\'' +
                '}';
    }
}
