import java.util.ArrayList;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Collection<Developer> developers = new ArrayList<>();
        Collection<Manager> managers = new ArrayList<>();

        new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                developers.add(new Developer());
            }
        }).start();
        Thread.sleep(2000);

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                managers.add(new Manager());
            }
        }).start();
        Thread.sleep(500);

        developers.stream().skip(999_999).forEach(System.out::println);
        managers.stream().skip(5).forEach(System.out::println);
    }
}
