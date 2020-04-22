import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable {
    public Data data; // Класс который умеет передавать данные

    public Sender(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        String[] packet = {"Vyacheslav", // Сами данные
                            "Dmitriy",
                            "Vladimir",
                            "Elizaveta",
                            "End"};

        // Иммитация долгого отправления данных
        for (String packets : packet) {
            data.send(packets);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
