package model;

import java.util.HashMap;

class CD extends MusicItem{
    private double totalDuration;           //????????
    private double durationOfSong;              //is this needed??? anyway will be accessible from hashmap!!

    static HashMap <String, Double> cdInfo= new HashMap<>();       //used to store songTitle & duration


    public CD(String itemID, String title, String genre, String artist, double price, double durationOfSong) {
        super(itemID, title, genre, artist, price);
        this.durationOfSong = durationOfSong;
        totalDuration += durationOfSong;            //calculating total duration of songs           //can do this by looping through hashmap!!!!????
    }

    public double getDurationOfSong() {
        return durationOfSong;
    }


}
