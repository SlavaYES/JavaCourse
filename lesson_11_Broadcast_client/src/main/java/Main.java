import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class Main {
    static List<InetAddress> listAllBroadcastAddresses() throws SocketException {
        List<InetAddress> broadcastList = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces
                = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();

            if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                continue;
            }

            networkInterface.getInterfaceAddresses().stream()
                    .map(a -> a.getBroadcast())
                    .filter(Objects::nonNull)
                    .forEach(broadcastList::add);
        }
        return broadcastList;
    }
    public static void main(String[] args) throws IOException {
//        List<InetAddress> addresses = listAllBroadcastAddresses();
//        addresses.forEach(System.out::println);

        DatagramSocket socket = new DatagramSocket(8080);
        socket.setBroadcast(true);


        byte[] data = "GET /info UDP".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length,
                InetAddress.getByName("192.168.0.255"), 8081);
        socket.send(packet);

        String str;
        do {
            byte[] buffer = new byte[1000];
            DatagramPacket packetFrom = new DatagramPacket(buffer, buffer.length);
            socket.receive(packetFrom);
            String received = new String(packetFrom.getData(), 0, packetFrom.getLength());
            if ("end".equals(received)) {
                break;
            }
            System.out.println(received);
        } while (true);
    }
}
