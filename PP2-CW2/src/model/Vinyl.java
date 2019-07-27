package model;


import java.util.HashMap;

class Vinyl extends MusicItem {
    private double speed;             //use hashMap to store with itemID????
    private double diameter;          //use hashMap to store with itemID????

    static HashMap<String, Double> vinylSpeed = new HashMap<>();       //used to store itemID & speed
    static HashMap<String, Double> vinylDiameter = new HashMap<>();       //used to store itemID & diameter


    public Vinyl(String itemID, String title, String genre, String artist, double price, double speed, double diameter) {
        super(itemID, title, genre, artist, price);
        this.speed = speed;                 //making sure that this extra info is added when adding a new Vinyl object
        this.diameter = diameter;           //making sure that this extra info is added when adding a new Vinyl object
    }

//    public double getSpeed() {
//        return speed;
//    }
//
//    public double getDiameter() {
//        return diameter;
//    }

}
