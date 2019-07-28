package model;


import java.util.HashMap;

class CD extends MusicItem {
    private double totalDuration;
    private double durationOfSong;              //is this needed??? anyway will be accessible from hashmap!!

    static HashMap<String, Double> cdDuration = new HashMap<>();       //used to store itemID & duration


    public CD(String itemID, String title, String genre, Date releaseDate, String artist, double price, double durationOfSong) {
        super(itemID, title, genre, releaseDate, artist, price);
        this.durationOfSong = durationOfSong;                 //making sure that this extra info is added when adding a new CD object
        totalDuration += durationOfSong;            //calculating total duration of songs
    }


//    public double getDurationOfSong() {
//        return durationOfSong;
//    }
//
//    public double getTotalDuration() {
//        return totalDuration;
//    }


}
