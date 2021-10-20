import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static Integer N = 20;
    public static void main(String[] args) {
        Collection<Developer> developers = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock();

        long start = System.nanoTime();
        for (int i = 0 ; i < 2; i++) {
            new Thread((Runnable) new MyThread(developers, lock)).start();
        }
        long stop = System.nanoTime();
        System.out.println("Time = " + (stop - start) + "nsec");


        Collection<Developer> dev = new ArrayList<>();
        start = System.nanoTime();
        for (int i = 0 ; i < 2; i++) {
            new Thread(new MyThread(dev)).start();
        }
        stop = System.nanoTime();
        System.out.println("Time = " + (stop - start) + "nsec");

//        for (Developer developer : developers) {
//            System.out.println(developer.toString());
//        }
    }
}
