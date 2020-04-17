import java.util.Random;
import java.lang.Object;

public class User {
    private static Integer ID = 1;
    public Integer id;
    public String name;
    public String mail;

    public User() {
        id = ID++;
        int min = 97, max = 122, num = 10;
        Random rand = new Random();
        name =  rand.ints(min, max + 1)
                    .limit(num)
                    .collect(StringBuilder::new,
                                StringBuilder::appendCodePoint,
                                StringBuilder::append)
                    .toString();
        mail = rand.ints(min, max + 1)
                    .limit(num)
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString() + "@";
        mail += rand.ints(min, max + 1)
                    .limit(num)
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString() + ".";
        mail += rand.ints(min, max + 1)
                .limit(2)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

}
