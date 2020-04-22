public class Data { // Класс для передачи данных
    public String packet; // Сами данные

    // 1 - отправляется
    // 0 - получается
    public static boolean transfer = true;

    public Data() {

    }

    synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        this.packet = packet;
        transfer = false;
        notifyAll();
    }

    synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        transfer = true;
        notifyAll();

        return this.packet;
    }
}
