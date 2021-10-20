import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Enumeration<NetworkInterface> interfaces
                = NetworkInterface.getNetworkInterfaces();
        NetworkInterface networkInterface = interfaces.nextElement();
        for (int i = 0; i < 12; i++) { // Find Wireless Interface
            networkInterface = interfaces.nextElement();
        }
        System.out.println(networkInterface.toString());

        MulticastSocket socket = new MulticastSocket(4446);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        SocketAddress socketAddress = new InetSocketAddress(group, 4446);
        socket.joinGroup(socketAddress, networkInterface);

        while (true) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(
                    packet.getData(), 0, packet.getLength());
            if ("end".equals(received)) {
                break;
            }
            System.out.println(received);
        }

        socket.close();
    }
}