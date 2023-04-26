package edu.eci.arep.apiresources;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import edu.eci.arep.entities.Tweet;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/tweets")
public class Tweets {

    private List<Tweet> cache = new ArrayList<>();
    private static MongoClient mongo;
    Gson gson = new Gson();
    private static String DB_URL = "ec2-54-226-188-161.compute-1.amazonaws.com";

    @GET
    public String getTweets() {
        mongo = new MongoClient(DB_URL, 27017);
        MongoDatabase db = mongo.getDatabase("admin");
        MongoCollection<Document> collection = db.getCollection("tweets");

        List<Document> documents = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().limit(10).iterator()) {
            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }
        }

        System.out.println(documents + "\n");
        mongo.close();


//        if (cache.size() >= 10) {
//            return gson.toJson(cache.subList(cache.size() - 10, cache.size()));
//        }
        return gson.toJson(documents);
    }

    @POST
    public void createTweet(Tweet tweet) {
//        cache.add(tweet);
        String body = gson.toJson(tweet);
        mongo = new MongoClient( DB_URL, 27017);
        MongoDatabase db = mongo.getDatabase("admin");

        MongoCollection<Document> collection = db.getCollection("tweets");

        System.out.println(body);
        Document document = Document.parse(body);
        System.out.println("POSTEANDO");

        collection.insertOne(document);
    }
}
