package model;

import java.util.HashMap;

abstract class CD extends MusicItem{
    static HashMap <String, Integer> cdInfo= new HashMap<>();       //used to store songTitle & duration

    public CD(String title, String itemID, String genre, Date releaseDate, String artist, Double price) {
        super(title, itemID, genre, releaseDate, artist, price);
    }

    public abstract double totalDuration();

}
