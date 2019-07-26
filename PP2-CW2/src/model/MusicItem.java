package model;


abstract class MusicItem {
    private String title;
    private Integer itemID;
    private String genre;
    //private Date releaseDate;               //thid should be fixed!!!!!!!
    private String artist;
    private Double price;


    public MusicItem(String title, Integer itemID, String genre, String artist, Double price) {
        this.title = title;
        this.itemID = itemID;
        this.genre = genre;
        //this.releaseDate = releaseDate;
        this.artist = artist;
        this.price = price;
    }

    @Override           //to check
    public String toString() {
        return "MusicItem{" +
                "title='" + title + '\'' +
                ", itemID=" + itemID +
                ", genre='" + genre + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price +
                '}';
    }
}