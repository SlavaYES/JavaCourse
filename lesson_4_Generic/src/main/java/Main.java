import java.util.*;

public class Main {
    static final int N = 1 << 20;
    public static void main(String[] args) {
        arrayList("ArrayList");
    }

    public static void arrayList(String name) {
        Collection<Manager> managers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            managers.add(new Manager());
        }

//        for (Manager manager : managers) {
//            System.out.println(manager.id
//                                + " - " + manager.name
//                                + " - " + manager.mail
//                                + " - " + manager.kpi);
//        }

        Statistic<Manager> statistic = new Statistic<>(managers);
        System.out.printf(" Max -> %d\n Min -> %d\n Middle -> %f\n",
                            statistic.getMax(),
                            statistic.getMin(),
                            statistic.getMiddle());
    }
}
