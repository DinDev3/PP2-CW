package model;


import java.util.Objects;

public abstract class MusicItem implements Comparable<MusicItem>{
    protected String itemID;
    protected String title;
    protected String genre;
    protected Date releaseDate;
    protected String artist;
    protected double price;

    public static int count = 0;


    public MusicItem(String itemID, String title, String genre, Date releaseDate, String artist, double price) {
        this.itemID = itemID;
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.price = price;
        this.releaseDate = releaseDate;
        count++;
    }

    @Override
    public String toString() {
        return "{" +
                "itemID='" + itemID + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }

    public static int getCount() {
        return count;
    }

    public String getItemID() {
        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() { return genre; }

    public Date getReleaseDate() { return releaseDate; }

    public String getArtist() { return artist; }

    public double getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicItem musicItem = (MusicItem) o;
        return Double.compare(musicItem.price, price) == 0 &&
                Objects.equals(itemID, musicItem.itemID) &&
                Objects.equals(title, musicItem.title) &&
                Objects.equals(genre, musicItem.genre) &&
                Objects.equals(releaseDate, musicItem.releaseDate) &&
                Objects.equals(artist, musicItem.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, title, genre, releaseDate, artist, price);
    }


    @Override
    public int compareTo(MusicItem obj) {
        return this.title.compareTo(obj.getTitle());
    }
}