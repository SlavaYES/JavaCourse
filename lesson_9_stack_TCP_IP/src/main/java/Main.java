import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8081);
        Socket client = server.accept();

        // Client
        Scanner in = new Scanner(client.getInputStream());
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);


        Scanner inSms = new Scanner(System.in);
        String sms;
        do {
            sms = in.nextLine(); // of client
            System.out.println("Client -> " + sms);
            System.out.print("Server -> ");
            sms = inSms.nextLine();
            out.println(sms); // to client
        } while (!sms.equals("exit"));

        client.close();
        // End Client

        server.close();
    }
}