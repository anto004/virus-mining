package acquisition;

import com.mongodb.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class App {
    private static void importCSV(){

    }

    public static void main(String[] args){
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("zika");
        MongoCollection<Document> collection = mongoDatabase.getCollection("Argentina");

        MongoCursor<Document> cursor = collection.find().iterator();

        while(cursor.hasNext()){
            System.out.println(cursor.next().toJson());
        }


    }
}
