import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
public class User {
    public Integer id;
    public String number;
    public String name;

    public User(Integer id, String number, String name) {
        this.id =  id;
        this.number = number;
        this.name = name;
    }
}
