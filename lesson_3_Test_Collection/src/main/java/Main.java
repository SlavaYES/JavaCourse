import java.util.*;

public class Main {
    static final int N = 1 << 20;
    public static void main(String[] args) {
        arrayList("ArrayList");
        linkedList("LinkedList");
        treeSet("TreeSet");
        hashSet("hashSet");
        linkedHashSet("LinkedHashSet");
    }

    private static void linkedHashSet(String name) {
        Collection<User> users = new LinkedHashSet<>();

        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            users.add(new User());
        }
        long end = System.nanoTime();
        double convertTime = (double) (end - start) / 1_000_000_000;

        start = System.nanoTime();
        users.clear();
        end = System.nanoTime();
        double convertTimeRemove = (double) (end - start) / 1_000_000_000;

        System.out.println(name);
        System.out.printf("Time Add -> %f\n", convertTime);
        System.out.printf("Time Remove -> %f\n\n", convertTimeRemove);
    }

    private static void hashSet(String name) {
        Collection<User> users = new HashSet<>();

        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            users.add(new User());
        }
        long end = System.nanoTime();
        double convertTime = (double) (end - start) / 1_000_000_000;

        start = System.nanoTime();
        users.clear();
        end = System.nanoTime();
        double convertTimeRemove = (double) (end - start) / 1_000_000_000;

        System.out.println(name);
        System.out.printf("Time Add -> %f\n", convertTime);
        System.out.printf("Time Remove -> %f\n\n", convertTimeRemove);
    }

    private static void treeSet(String name) {
        Collection<User> users = new TreeSet<>(new UserCompare());

        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            users.add(new User());
        }
        long end = System.nanoTime();
        double convertTime = (double) (end - start) / 1_000_000_000;

        start = System.nanoTime();
        users.clear();
        end = System.nanoTime();
        double convertTimeRemove = (double) (end - start) / 1_000_000_000;

        System.out.println(name);
        System.out.printf("Time Add -> %f\n", convertTime);
        System.out.printf("Time Remove -> %f\n\n", convertTimeRemove);
    }

    private static void linkedList(String name) {
        Collection<User> users = new LinkedList<>();

        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            users.add(new User());
        }
        long end = System.nanoTime();
        double convertTime = (double) (end - start) / 1_000_000_000;

        start = System.nanoTime();
        users.clear();
        end = System.nanoTime();
        double convertTimeRemove = (double) (end - start) / 1_000_000_000;

        System.out.println(name);
        System.out.printf("Time Add -> %f\n", convertTime);
        System.out.printf("Time Remove -> %f\n\n", convertTimeRemove);
    }

    public static void arrayList(String name) {
        Collection<User> users = new ArrayList<>();

        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            users.add(new User());
        }
        long end = System.nanoTime();
        double convertTime = (double) (end - start) / 1_000_000_000;


        start = System.nanoTime();
        users.clear();
        end = System.nanoTime();
        double convertTimeRemove = (double) (end - start) / 1_000_000_000;

        System.out.println(name);
        System.out.printf("Time Add -> %f\n", convertTime);
        System.out.printf("Time Remove -> %f\n\n", convertTimeRemove);
    }
}
