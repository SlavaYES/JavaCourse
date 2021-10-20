import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8081);

        while (true) {
            try {
                Socket client = server.accept();
                new Thread(new ServerThread(client)).start();
            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());

            }
        }

//        server.close();
    }
}
