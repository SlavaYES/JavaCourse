import lombok.*;


/**
 * Класс описывающий <b>Пользователя</b>
 * @author Vyacheslav Dopchuk "voen1999@gmail.com"
 * @version 0.2
 * @see Main
 */
//POJO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class User {
    /** Поле идентификатор */
    protected int id;
    /** Поле имени */
    protected String name;
    protected String phone;
}