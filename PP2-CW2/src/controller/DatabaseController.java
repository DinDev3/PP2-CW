package controller;


import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import model.CD;
import model.Date;
import model.MusicItem;
import model.Vinyl;
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
        MongoCollection<Document> oldCollection = database.getCollection("ItemsInStore");

        for(Document selectedDoc : oldCollection.find()){
            String itemID = (String)selectedDoc.get("item ID");
            String title = (String) selectedDoc.get("Title");
            String genre = (String) selectedDoc.get("Genre");
            Date date = (Date) selectedDoc.get("Date");
            String artist = (String) selectedDoc.get("Artist");
            double price = (double) selectedDoc.get("Price");
            String type = (String) selectedDoc.get("Type");

            if(type.equals("CD")){
                double duration = (double) selectedDoc.get("Duration");
                MusicItem storedCD = new CD(itemID, title, genre, date, artist, price, type, duration);
                WestminsterMusicStoreManager.itemsInStore.add(storedCD);
//                System.out.println(storedCD);            //to check whether item was added

            }else if(type.equals("Vinyl")){
                double speed = (double) selectedDoc.get("Speed(RPM)");
                double diameter = (double) selectedDoc.get("Diameter(cm)");
                MusicItem storedVinyl = new Vinyl(itemID, title, genre, date, artist, price, type, speed, diameter);
                WestminsterMusicStoreManager.itemsInStore.add(storedVinyl);

//                System.out.println(storedVinyl);            //to check whether item was added
            }
        }
    }
}
