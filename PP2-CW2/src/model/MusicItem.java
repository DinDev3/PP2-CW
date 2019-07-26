package model;


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
}