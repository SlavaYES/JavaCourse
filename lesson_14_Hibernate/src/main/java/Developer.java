import javax.persistence.*;

@Entity
@Table()
public class Developer extends User {

    @Column
    private String email;

    public Developer(String name, String phone, String email) {
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    public Developer() {
    }

    public Developer(String email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "Developer{id = " + getId() +
                "name = " + getName() +
                ", phone = " + getPhone() +
                ", email = " + email + "}";
    }
}
