import java.util.concurrent.ThreadLocalRandom;

public class Receiver implements Runnable {
    public Data data;

    public Receiver(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (String receive = data.receive();
             !"End".equals(receive);
             receive = data.receive()) {
            System.out.println(receive);

            // Иммитация долгого получения данных
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
