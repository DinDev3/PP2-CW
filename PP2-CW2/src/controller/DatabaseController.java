package controller;


import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import com.mongodb.ConnectionString;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;



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

    public static void importDB() {
        //importing stored data in db to application

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://cw_user:123098@cluster0-gxfyy.gcp.mongodb.net/test?retryWrites=true&w=majority");
        com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("OnlineMusicStore");

        //Access collection
        MongoCollection<Document> collection = database.getCollection("ItemsInStore");


//        List<Document> itemsInStore = (List<Document>) collection.find().into(
//                new ArrayList());
//
//        WestminsterMusicStoreManager.itemsInStore.add(collection.find().into(MusicItem));             //adding object into itemsInStore arrayList
//
//        // Getting the iterable object
//        FindIterable<Document> iterDoc = collection.find();
//        int i = 1;
//
//        // Getting the iterator
//        Iterator it = iterDoc.iterator();
//
//        while (it.hasNext()) {
////            allItemIDs.put(itemID, type);
//            System.out.println(it.next());
//            i++;
//        }
//
//        System.out.println(WestminsterMusicStoreManager.itemsInStore);
    }
}
