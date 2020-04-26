import java.util.Random;

public class Developer extends User {

    public String lang;

    public Developer() {
        id = ID++;
        int min = 97, max = 122, num = 10;
        Random rand = new Random();
        name =  rand.ints(min, max + 1)
                .limit(num)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        lang = rand.ints(min, max + 1)
                .limit(5)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        kpi = (int) (Math.random() * 100 + 1);
    }
    public String toString() {
        return "[" + Integer.toString(id) + ", "
                + name + ", " + lang + ", "
                + kpi + "]";
    }
}