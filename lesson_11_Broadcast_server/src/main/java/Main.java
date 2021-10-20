import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket socket = new DatagramSocket(8081);

        byte[] data = new byte[1000];
        DatagramPacket packetFrom = new DatagramPacket(data, data.length);
        socket.receive(packetFrom);
        String str = new String(data);
        System.out.println(str);

        Process process = Runtime.getRuntime().exec("cmd /c \"wmic cpu get caption, " +
                "deviceid, name, numberofcores, maxclockspeed, status\"");
        InputStream inS = process.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inS));

        String line = null;
        while ((line = in.readLine()) != null) {
            byte[] buffer = line.getBytes();

            DatagramPacket packetTo = new DatagramPacket(buffer, buffer.length,
                    packetFrom.getAddress(), packetFrom.getPort());
            socket.send(packetTo);
        }

        Process process2 = Runtime.getRuntime().exec("cmd /c \"wmic memorychip get Manufacturer," +
                " Capacity, PartNumber, Speed, DeviceLocator\"");
        InputStream inS2 = process2.getInputStream();
        BufferedReader in2 = new BufferedReader(new InputStreamReader(inS2));

        String line1 = null;
        while ((line1 = in2.readLine()) != null) {
            byte[] buffer = line1.getBytes();

            DatagramPacket packetTo = new DatagramPacket(buffer, buffer.length,
                    packetFrom.getAddress(), packetFrom.getPort());
            socket.send(packetTo);
        }

        byte[] buffer = "end".getBytes();

        DatagramPacket packetTo = new DatagramPacket(buffer, buffer.length,
                packetFrom.getAddress(), packetFrom.getPort());
        socket.send(packetTo);

        socket.close();
    }
}