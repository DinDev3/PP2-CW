package model;

import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterMusicStoreManager implements StoreManager {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<String> itemsInStore = new ArrayList<>();
    static ArrayList<String> shoppingCart = new ArrayList<>();

    static private String title;
    static private String genre;
    static private double date;
    static private String artist;
    static private double price;
    static private double duration;

    @Override
    public void addItem() {
        System.out.println("Choose item to be added:");
        System.out.println("1)CD\n2)Vinyl\n>");        //validate!!!!!!
        int itemType = sc.nextInt();
        sc.nextLine();              //to consume the rest of the line

        if (itemType == 1) {       //CD item chosen
            addCommonInfo();        //used to get common information

            System.out.println("Enter Duration of song:");
            duration = sc.nextDouble();
            sc.nextLine();              //to consume the rest of the line

            CD newCD = new CD(title, genre, artist, price, duration);
            System.out.println(newCD);
            System.out.println(MusicItem.getItemID());              //to check, can be added to object as well!

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

    private static void addCommonInfo() {
        System.out.println("\nEnter Title:");
        title = sc.nextLine();
        System.out.println("Enter Genre:");
        genre = sc.nextLine();
        System.out.println("Enter Release Date:");                      //Date class should be fixed as req!!!!
        date = sc.nextDouble();
        sc.nextLine();              //to consume the rest of the line

        System.out.println("Enter Artist:");
        artist = sc.nextLine();
        System.out.println("Enter Price:");
        price = sc.nextDouble();
        sc.nextLine();              //to consume the rest of the line
    }
}
