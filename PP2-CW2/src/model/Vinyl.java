package model;


import java.util.HashMap;
import java.util.Objects;

public class Vinyl extends MusicItem {
    protected double speed;
    protected double diameter;

    public Vinyl(String itemID, String title, String genre, Date releaseDate, String artist, double price, String type, double speed, double diameter) {
        super(itemID, title, genre, releaseDate, artist, price, type);
        this.speed = speed;                 //making sure that this extra info is added when adding a new Vinyl object
        this.diameter = diameter;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDiameter() {
        return diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vinyl vinyl = (Vinyl) o;
        return Double.compare(vinyl.speed, speed) == 0 &&
                Double.compare(vinyl.diameter, diameter) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speed, diameter);
    }
}
