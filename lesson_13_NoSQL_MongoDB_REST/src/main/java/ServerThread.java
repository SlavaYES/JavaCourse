import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import lombok.*;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс для обработки потомков сервера
 * @author Vyacheslav Dopchuk "voen1999@gmail.com"
 * @version 0.2
 */
@Setter @Getter
@NoArgsConstructor
@ToString
public class ServerThread implements Runnable {
    /** Client's socket */
    private Socket client = null;
    /** Input */
    private static Scanner in = null;
    /** Output */
    private static PrintWriter out = null;
    /** MongoDB collection */
    private static MongoCollection<Document> collection = null;

    /**
     * Constructor
     * @param client Socket for client
     */
    public ServerThread(Socket client) throws IOException {
        this.client = client;
        in = new Scanner(client.getInputStream());
        out = new PrintWriter(client.getOutputStream(), true);
    }
    /**
     * Constructor
     * @param client Socket client
     * @param collection MongoDB data
     */
    public ServerThread(Socket client, MongoCollection<Document> collection) throws IOException {
        this.client = client;
        in = new Scanner(client.getInputStream());
        out = new PrintWriter(client.getOutputStream(), true);
        ServerThread.collection = collection;
    }

    /**
     * Function for processing client
     */
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
            String[] REST = splitRequest[1].split("\\W");
            System.out.println(splitRequest[1]);
            for (String rest : REST) {
                System.out.println(rest);
            }
            String head;
            switch (REST[1]) {
//             REST [ 0  1 ]
//                    /info
                case "info":
                    StringBuilder JSON = new StringBuilder();
                    for (Document doc : collection.find()) {
                        JSON.append(doc.toJson() + "\r\n");
                        System.out.println(doc.toJson());
                    }
                    head = "HTTP /1.1 200 OK + \r\n" +
                            "Content-Type: application/json\r\n" +
                            "Content-Length: ";
                    out.println(head + JSON.length() + "\r\n\r\n" + JSON);
                    break;
//             REST [ 0   1    2     3         4        5 ]
//                    /insert?name=Vyacheslav&phone=89134870834
                case "insert":
                    Document insert = new Document("name", REST[3]).append("phone", REST[5]);
                    collection.insertOne(insert);
                    break;
//             REST [ 0   1    2     3         4        5 ]
//                    /update?name=Vaycheslav&phone=23563463466
                case "update":
                    BasicDBObject search = new BasicDBObject().append("name", REST[3]);
                    BasicDBObject changed = new BasicDBObject();
                    changed.append("$set", new BasicDBObject("phone", REST[5]));
                    collection.updateMany(search, changed);
                    break;
//             REST [ 0   1    2     3        ]
//                    /delete?name=Vaycheslav
                case "delete":
                    BasicDBObject delete = new BasicDBObject();
                    delete.append("name", REST[3]);
                    collection.deleteMany(delete);
                    break;
                default:
                    String WRONG = "<!DOCTYPE><html><body style=\"color: #4caf50; fon-family: " +
                            "Arial; font-size: 20px\"><center>Sorry</center></body></html>";
                    head = "HTTP /1.1 404 Not Found + \r\n" +
                            "Content-Type: text/html\r\n" +
                            "Content-Length: ";
                    out.println(head + WRONG.length() + "\r\n\r\n" + WRONG);
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
            }
            StringBuilder JSON = new StringBuilder();
            for (Document doc : collection.find()) {
                JSON.append(doc.toJson()).append("\r\n");
                System.out.println(doc.toJson());
            }
            head = "HTTP /1.1 200 OK + \r\n" +
                    "Content-Type: application/json\r\n" +
                    "Content-Length: ";
            out.println(head + JSON.length() + "\r\n\r\n" + JSON);
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
