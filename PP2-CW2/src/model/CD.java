package model;


import java.util.HashMap;
import java.util.Objects;

public class CD extends MusicItem {
    protected double durationOfSong;

    public CD(String itemID, String title, String genre, Date releaseDate, String artist, double price, String type, double durationOfSong) {
        super(itemID, title, genre, releaseDate, artist, price, type);
        this.durationOfSong = durationOfSong;                 //making sure that this extra info is added when adding a new CD object
    }

    public double getDurationOfSong() {
        return durationOfSong;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CD cd = (CD) o;
        return Double.compare(cd.durationOfSong, durationOfSong) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), durationOfSong);
    }

//create Time class for duration!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
}
