package model;


abstract class MusicItem {
    private String title;
    private String itemID;
    private String genre;
    private Date releaseDate;
    private String artist;
    private Double price;


    public MusicItem(String title, String itemID, String genre, Date releaseDate, String artist, Double price) {
        this.title = title;
        this.itemID = itemID;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.price = price;
    }

}