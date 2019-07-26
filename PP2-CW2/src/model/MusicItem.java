package model;


abstract class MusicItem {
    private static int itemID;
    private String title;
    private String genre;
    //private Date releaseDate;               //Lec 7 - slide 46!!!
    private String artist;
    private double price;

    public MusicItem(String title, String genre, String artist, double price) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.price = price;

        ++itemID;
    }

    @Override
    public String toString() {
        return "MusicItem{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }


    public static int getItemID() {
        return itemID;
    }
}