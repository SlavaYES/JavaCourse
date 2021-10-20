import com.mongodb.BasicDBObject;
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

        StringBuilder response = new StringBuilder();
        String JSON = "";

        String[] REST = splitRequest[1].split("\\W");
        for (String rest : REST) {
            System.out.println(rest);
        }

        switch (REST[1]) {
//             REST [ 0  1 ]
//                    /info
//                for (Document doc : collection.find()) {
//                    response.append(doc.toJson());
//                    response.append(",");
//                }
//
//                JSON += response.toString().substring(0, response.length() - 1);
//                setResponse(JSON);
//                break;
//             REST [ 0   1    2     3         4        5 ]
//                    /insert?name=Vyacheslav&phone=89134870834&status=willbe&level=easy
            case "insert": // ??
                Document insert = new Document("name", REST[3]).append("phone", REST[5]).append("status", REST[7]).append("level", REST[9]);
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
        }
        for (Document doc : collection.find()) {
            response.append(doc.toJson());
            response.append(",");
        }

        JSON += response.toString().substring(0, response.length() - 1);
        setResponse(JSON);

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setResponse(String json) {
        String page = "HTTP /1.1 200 OK\n" +
                "Content-Type: application/json\n" +
                "Access-Control-Allow-Origin: *\n" +
                "\n" + "[" + json + "]";
        out.println(page);
    }
}
