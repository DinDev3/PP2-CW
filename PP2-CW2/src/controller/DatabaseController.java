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

    public static void addToDB(String itemID, String title, String genre, int year, int month,int day, String artist, double price, String type, double duration) {
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
                .append("Release Date",new Document("year",year)            //document inside document
                        .append("month",month)
                        .append("day", day))
                .append("Artist", artist)
                .append("Price", price)
                .append("Type", type)
                .append("Duration", duration);

        //insert the document
        collection.insertOne(newItem);
    }

    public static void addToDB(String itemID, String title, String genre,int year, int month,int day, String artist, double price, String type, double speed, double diameter) {
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
                .append("Release Date",new Document("year",year)            //document inside document
                        .append("month",month)
                        .append("day", day))
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
            Document dateObject = (Document) selectedDoc.get("Release Date");          //Date will give null, need to add day, month, year separately to db when adding

            String artist = (String) selectedDoc.get("Artist");
            double price = (double) selectedDoc.get("Price");
            String type = (String) selectedDoc.get("Type");

            //breaking down date document to create date using Date constructor
            int year = dateObject.getInteger("year");
            int month = dateObject.getInteger("month");
            int date = dateObject.getInteger("day");

            //creating Date object using Date constructor in Date class (Otherwise won't show up in GUI)
            Date releasedDate = new Date(year, month, date);

            if(type.equals("CD")){
                double duration = (double) selectedDoc.get("Duration");
                MusicItem storedCD = new CD(itemID, title, genre, releasedDate, artist, price, type, duration);
                WestminsterMusicStoreManager.itemsInStore.add(storedCD);
                WestminsterMusicStoreManager.allItemIDs.put(itemID,type);
//                System.out.println(storedCD);            //to check whether item was added

            }else if(type.equals("Vinyl")){
                double speed = (double) selectedDoc.get("Speed(RPM)");
                double diameter = (double) selectedDoc.get("Diameter(cm)");
                MusicItem storedVinyl = new Vinyl(itemID, title, genre, releasedDate, artist, price, type, speed, diameter);
                WestminsterMusicStoreManager.itemsInStore.add(storedVinyl);
                WestminsterMusicStoreManager.allItemIDs.put(itemID,type);
//                System.out.println(storedVinyl);            //to check whether item was added
            }
        }
        System.out.println("\n----All items retrieved from database.----");
        System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
    }
}

/*
References:
https://www.tutorialspoint.com/mongodb/mongodb_java
        https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
        https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/installation/
        https://mongodb.github.io/mongo-java-driver/
        https://github.com/mongodb/mongo-java-driver/tree/master

Importing MongoDB documents to Java ArrayList
https://stackoverflow.com/questions/19435621/extract-field-value-from-mongodb-basicdbobject?rq=1
*/