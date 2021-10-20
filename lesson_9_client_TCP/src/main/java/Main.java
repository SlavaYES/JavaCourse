import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 8081);

        Scanner in = new Scanner(client.getInputStream());
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        Scanner inSms = new Scanner(System.in);
        String sms;
        do {
            System.out.print("Client -> ");
            sms = inSms.nextLine();
            out.println(sms); // to server
            System.out.println("Server -> " + in.nextLine()); // of server
        } while (!sms.equals("exit"));

        client.close();
    }
}
