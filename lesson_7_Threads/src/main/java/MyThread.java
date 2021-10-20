import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread implements Runnable {

    public MyThread(Collection<Developer> dev) {
        synchronized (dev) {
            for (int i = 0; i < 500_000; i++) {
                dev.add(new Developer());
            }
        }
    }
    public MyThread(Collection<Developer> developers, ReentrantLock lock) {
        lock.lock();
        for (int i = 0; i < 500_000; i++) {
            developers.add(new Developer());
        }
        lock.unlock();
    }

    @Override
    public void run() {

    }
}
