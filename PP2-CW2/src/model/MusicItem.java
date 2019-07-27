package model;


import java.util.Objects;

abstract class MusicItem {
    private String itemID;
    private String title;
    private String genre;
    //private Date releaseDate;               //Lec 7 - slide 46!!!
    private String artist;
    private double price;

    private static int count;


    public MusicItem(String itemID, String title, String genre, String artist, double price) {
        this.itemID = itemID;
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.price = price;
        count++;
    }

    @Override
    public String toString() {
        return "MusicItem{" +
                "itemID='" + itemID + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }


    public static int getCount() {
        return count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicItem musicItem = (MusicItem) o;
        return Double.compare(musicItem.price, price) == 0 &&
                Objects.equals(itemID, musicItem.itemID) &&
                Objects.equals(title, musicItem.title) &&
                Objects.equals(genre, musicItem.genre) &&
                Objects.equals(artist, musicItem.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, title, genre, artist, price);
    }
}