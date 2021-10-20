import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Main {

    public static ServerSocket server = null;
    public static Socket client = null;

    public static void main(String[] args) throws IOException {

        server = new ServerSocket(8081);

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("trainer");
        MongoCollection<Document> collection = db.getCollection("coach");

        while (true) {
            client = server.accept();
            new Thread(new ServerThread(client, collection)).start();
        }
    }
}