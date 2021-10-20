import lombok.*;
import javax.persistence.*;


/**
 * Class Manager's for database MySQL
 * @author Vyacheslav Dopchuk "voen1999@gmail.com"
 * @version 0.2
 */
@Entity
@Table()
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Manager extends Developer {

    @Column
    private String lang;

    /**
     * Constructor for simple fill
     * @param name Name user
     * @param phone Phone user
     * @param email Email user
     * @param lang Speak Language user
     */
    public Manager(String name, String phone, String email, String lang) {
        setName(name);
        setPhone(phone);
        setEmail(email);
        this.lang = lang;
    }
}
