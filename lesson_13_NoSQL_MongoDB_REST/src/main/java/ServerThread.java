import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private Socket client = null;
    private static Scanner in = null;
    private static PrintWriter out = null;
    private static MongoCollection<Document> collection = null;

    public ServerThread(Socket client) throws IOException {
        this.client = client;
        in = new Scanner(client.getInputStream());
        out = new PrintWriter(client.getOutputStream(), true);
    }
    public ServerThread(Socket client, MongoCollection<Document> collection) throws IOException {
        this.client = client;
        in = new Scanner(client.getInputStream());
        out = new PrintWriter(client.getOutputStream(), true);
        ServerThread.collection = collection;
    }

    public static MongoCollection<Document> getCollection() {
        return collection;
    }

    public static void setCollection(MongoCollection<Document> collection) {
        ServerThread.collection = collection;
    }

    @Override
    public void run() {

        String reguest = in.nextLine();
        String[] splitRequest = reguest.split(" ");
        System.out.println(reguest);
        if (reguest.equals("GET / HTTP/1.1") || reguest.equals("GET /favicon.ico HTTP/1.1")) {
            String page = "<!DOCTYPE><html><body style=\"color: #4285f4; fon-family: Arial; font-size: 20px\"><center>WELCOME<br>SlavaAPI MongoDB<br></center>" +
                    "Use commandline:<br>" +
                    "/info<br>" +
                    "/insert<br>" +
                    "/delete<br>" +
                    "/update<br>" +
                    "</body></html>";
            String header = "HTTP /1.1 200 OK + \r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: ";
            out.println(header  + page.length() + "\r\n" +
                    "\r\n" +
                    page);
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }


        if (splitRequest[0].equals("GET")) {
            String[] REST = splitRequest[1].split("/");
            for (String rest : REST) {
                System.out.println(rest);
            }
            switch (REST[1]) {
                case "info":
                    StringBuilder JSON = new StringBuilder();
                    for (Document doc : collection.find()) {
                        JSON.append(doc.toJson());
                        System.out.println(doc.toJson());
                    }
                    String head = "HTTP /1.1 200 OK + \r\n" +
                            "Content-Type: application/json\r\n" +
                            "Content-Length: ";
                    out.println(head + JSON.length() + "\r\n\r\n" + JSON);

                    break;
                case "insert":

                    break;
                case "update":
                    break;
                case "delete":
                    break;
                default:
                    break;
            }
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
