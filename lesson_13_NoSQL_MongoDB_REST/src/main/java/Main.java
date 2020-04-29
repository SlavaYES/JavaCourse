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
        MongoDatabase db = mongoClient.getDatabase("user");
        MongoCollection<Document> collection = db.getCollection("profile");

        while (true) {
            client = server.accept();
            new Thread(new ServerThread(client, collection)).start();
        }
    }
}



// update
//        BasicDBObject searchOgj = new BasicDBObject();
//        searchOgj.append("age", 22);
//
//        BasicDBObject changeObj = new BasicDBObject();
//        changeObj.append("$set", new BasicDBObject().append("age", 23));
//
//
//        collection.updateOne(searchOgj, changeObj);

// delete

//        BasicDBObject searchObj = new BasicDBObject();
//        searchObj.append("age", 20);
////        collection.deleteMany(); // all
//        collection.deleteOne(searchObj);
//
//        for (Document doc : collection.find()) {
//            System.out.println(doc.toJson());
//        }
