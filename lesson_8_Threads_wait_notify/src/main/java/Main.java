public class Main {
    public static void main(String[] args) {
        Data data = new Data(); // Класс передачи данных

        Thread sender = new Thread(new Sender(data)); // Поток для отправки Данных
        Thread recieve = new Thread(new Receiver(data)); // Поток для получения Данных

        sender.start();
        recieve.start();
    }
}
