import lombok.*;
import javax.persistence.*;

@Entity
@Table()
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Developer extends User {

    @Column
    private String email;

    /**
     * Constructor for simple fill
     * @param name Name user
     * @param phone Phone user
     * @param email Email user
     */
    public Developer(String name, String phone, String email) {
        setName(name);
        setPhone(phone);
        setEmail(email);
    }
}
