package model;

import java.util.HashMap;

class CD extends MusicItem{
    private double totalDuration;           //????????
    private double durationOfSong;

    static HashMap <String, Integer> cdInfo= new HashMap<>();       //used to store songTitle & duration


    public CD(String title, Integer itemID, String genre, String artist, Double price, double durationOfSong) {
        super(title, itemID, genre, artist, price);
        this.durationOfSong = durationOfSong;
    }


    public double getDurationOfSong() {
        return durationOfSong;
    }


}
