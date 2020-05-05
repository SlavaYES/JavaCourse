import javax.persistence.*;

@Entity
@Table()
public class Manager extends Developer {

    @Column
    private String lang;

    public Manager(String name, String phone, String email, String lang) {
        setName(name);
        setPhone(phone);
        setEmail(email);
        this.lang = lang;
    }

    public Manager() {
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Manager{id = " + getId() +
                "name = " + getName() +
                ", phone = " + getPhone() +
                ", email = " + getEmail() +
                ", lang = " + lang + "}";
    }
}
