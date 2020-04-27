import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        MulticastSocket socket = new MulticastSocket();
        InetAddress group = InetAddress.getByName("230.0.0.0");

        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\voen1\\" +
                "Documents\\Prog\\JavaCourse\\" +
                "lesson_11_Broadcast_client\\" +
                "src\\main\\java\\Main.java"));

        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            byte[] buffer = line.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 4446);
            socket.send(packet);
            line = reader.readLine();
        }

        byte[] buffer = "end".getBytes();
        DatagramPacket packet2 = new DatagramPacket(buffer, buffer.length, group, 4446);
        socket.send(packet2);

        reader.close();
    }
}