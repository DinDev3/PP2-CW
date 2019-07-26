package model;

import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterMusicStoreManager implements StoreManager {
    static ArrayList<String> itemsInStore = new ArrayList<>();
    static ArrayList<String> shoppingCart = new ArrayList<>();

    String title;
    String genre;
    double date;
    String artist;
    double price;
    double duration;

    @Override
    public void addItem() {
        System.out.println("Choose item to be added:");
        Scanner sc = new Scanner(System.in);
        System.out.println("1)CD\n2)Vinyl\n>");        //validate!!!!!!
        int itemType = sc.nextInt();

        if (itemType == 1) {       //CD item chosen
            int id = 0;
            System.out.println("\nEnter Title:");
            title = sc.nextLine();
            sc.next();
            System.out.println("Enter Genre:");
            genre = sc.nextLine();
            sc.next();
            System.out.println("Enter Release Date:");
            date = sc.nextDouble();
            sc.next();
            System.out.println("Enter Artist:");
            artist = sc.nextLine();
            sc.next();
            System.out.println("Enter Price:");
            price = sc.nextDouble();
            sc.next();
            System.out.println("Enter Duration of song:");
            duration = sc.nextDouble();
            sc.next();

            CD newCD = new CD(title,id, genre, artist, price, duration);            //pass variables as parameters?????
            System.out.println(newCD);

        } else if (itemType == 2) {         //Vinyl item chosen

        }


    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void printList() {

    }

    @Override
    public void sortItem() {

    }

    @Override
    public void buyItem() {

    }
}
