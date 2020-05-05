import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue()
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String phone;

    public User(String name, String phone) {
        this.phone = phone;
        this.name = name;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "phone = " + phone +
                "}";
    }
}
