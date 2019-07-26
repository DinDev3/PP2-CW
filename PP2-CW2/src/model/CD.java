package model;

import java.util.HashMap;

class CD extends MusicItem{
    private double totalDuration;           //????????
    private double durationOfSong;

    static HashMap <String, Integer> cdInfo= new HashMap<>();       //used to store songTitle & duration


    public CD(String title, String genre, String artist, double price, double durationOfSong) {
        super(title, genre, artist, price);
        this.durationOfSong = durationOfSong;
        totalDuration += durationOfSong;            //calculating total duration of songs
    }

    public double getDurationOfSong() {
        return durationOfSong;
    }


}
