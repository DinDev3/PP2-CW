package controller;


import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import model.Date;
import org.bson.Document;

import java.util.Arrays;


public class DatabaseController {

    public static void addToDB(String itemID, String title, String genre, String date, String artist, double price, String type, double duration) {
        //Adding a CD to the Collection

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://cw_user:123098@cluster0-gxfyy.gcp.mongodb.net/test?retryWrites=true&w=majority");
        com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("OnlineMusicStore");

        //Access collection
        MongoCollection<Document> collection = database.getCollection("ItemsInStore");

        //create a document
        Document newItem = new Document("item ID", itemID)
                .append("Title", title)
                .append("Genre", genre)
                .append("Release Date", date)
                .append("Artist", artist)
                .append("Price", price)
                .append("Type", type)
                .append("Duration", duration);

        //insert the document
        collection.insertOne(newItem);
    }

    public static void addToDB(String itemID, String title, String genre, String date, String artist, double price, String type, double speed, double diameter) {
        //Adding a Vinyl to the Collection

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://cw_user:123098@cluster0-gxfyy.gcp.mongodb.net/test?retryWrites=true&w=majority");
        com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("OnlineMusicStore");

        //Access collection
        MongoCollection<Document> collection = database.getCollection("ItemsInStore");

        //create a document
        Document newItem = new Document("item ID", itemID)
                .append("Title", title)
                .append("Genre", genre)
                .append("Release Date", date)
                .append("Artist", artist)
                .append("Price", price)
                .append("Type", type)
                .append("Speed(RPM)", speed)
                .append("Diameter(cm)", diameter);

        //insert the document
        collection.insertOne(newItem);
    }

    public static void deleteFromDB(String searchID) {
        //Deleting an item from the Collection

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://cw_user:123098@cluster0-gxfyy.gcp.mongodb.net/test?retryWrites=true&w=majority");
        com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("OnlineMusicStore");

        //Access collection
        MongoCollection<Document> collection = database.getCollection("ItemsInStore");

        collection.deleteOne(Filters.eq("item ID", searchID));
    }
}
