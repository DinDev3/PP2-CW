package model;


abstract class Vinyl extends MusicItem{
    public Vinyl(String title, String itemID, String genre, Date releaseDate, String artist, Double price) {
        super(title, itemID, genre, releaseDate, artist, price);
    }

    public abstract double Speed();
    public abstract double diameter();

//    getter & setter methods

}
