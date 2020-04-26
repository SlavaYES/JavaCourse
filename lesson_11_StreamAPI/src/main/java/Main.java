import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static int N = 1_0;
    public static void main(String[] args) {
        Collection<Developer> developers = new ArrayList<>();
        Collection<Manager> managers = new ArrayList<>();

        for (int i = 0;  i < N; i++) {
            developers.add(new Developer());
         }
        for (int i = 0;  i < N; i++) {
            managers.add(new Manager());
        }

        developers.forEach(System.out::println);
        managers.forEach(System.out::println);

        System.out.println("[Max Dev]: " + developers.stream().max(Developer::compareDev).get());
        System.out.println("[Min Dev]: " + developers.stream().min(Developer::compareDev).get());
        System.out.println("[Max Man]: " +managers.stream().max(Manager::compareMan).get());
        System.out.println("[Min Man]: " +managers.stream().min(Manager::compareMan).get());

        System.out.println("[Middle Dev]: " + (developers.stream().max(Developer::compareDev).get().kpi
                + developers.stream().min(Developer::compareDev).get().kpi) / 2);
        System.out.println("[Middle Man]: " + (managers.stream().max(Manager::compareMan).get().kpi
                + managers.stream().min(Manager::compareMan).get().kpi) / 2);
    }
}
