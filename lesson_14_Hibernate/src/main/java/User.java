import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="user")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue()
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String phone;

    /**
     * Constructor for simple fill
     * @param name Name user
     * @param phone Phone user
     */
    public User(String name, String phone) {
        this.phone = phone;
        this.name = name;
    }
}
