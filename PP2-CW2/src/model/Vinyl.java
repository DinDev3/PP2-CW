package model;


import java.util.HashMap;
import java.util.Objects;

public class Vinyl extends MusicItem {
    private double speed;             //use hashMap to store with itemID????
    private double diameter;          //use hashMap to store with itemID????

    public static HashMap<String, Double> vinylSpeed = new HashMap<>();       //used to store itemID & speed
    public static HashMap<String, Double> vinylDiameter = new HashMap<>();       //used to store itemID & diameter


    public Vinyl(String itemID, String title, String genre, Date releaseDate, String artist, double price, double speed, double diameter) {
        super(itemID, title, genre, releaseDate, artist, price);
        this.speed = speed;                 //making sure that this extra info is added when adding a new Vinyl object
        this.diameter = diameter;           //making sure that this extra info is added when adding a new Vinyl object
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
