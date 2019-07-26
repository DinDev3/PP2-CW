package model;

import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterMusicStoreManager implements StoreManager {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Object> itemsInStore = new ArrayList<>();
    static ArrayList<Object> shoppingCart = new ArrayList<>();

    static private String itemID;
    static private String title;
    static private String genre;
    static private double date;
    static private String artist;
    static private double price;
    static private double duration;

    @Override
    public void addItem() {
        System.out.println("Choose item to be added:");
        System.out.println("1)CD\n2)Vinyl");        //validate!!!!!!
        System.out.print(">");
        int itemType = sc.nextInt();
        sc.nextLine();              //to consume the rest of the line

        if (itemType == 1) {       //CD item chosen
            addCommonInfo();        //used to get common information

            System.out.println("Enter Duration of song:");
            System.out.print(">");
            duration = sc.nextDouble();
            CD.cdInfo.put(title, duration);             //adding duration to hashMap
            sc.nextLine();              //to consume the rest of the line

            CD newCD = new CD(itemID, title, genre, artist, price, duration);
            itemsInStore.add(newCD);                            //adding CD object into itemsInStore arrayList

            System.out.println(newCD);

            System.out.println(MusicItem.getCount());           //to check whether count is right

            //add object to itemsInStore arrayList!!!!!!!!!!!!!

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
        System.out.println("\nEnter Item ID:");
        System.out.print(">");
        itemID = sc.nextLine();

        System.out.println("Enter Title:");
        System.out.print(">");
        title = sc.nextLine();

        System.out.println("Enter Genre:");
        System.out.print(">");
        genre = sc.nextLine();

        System.out.println("Enter Release Date:");                      //Date class should be fixed as req!!!!
        System.out.print(">");
        date = sc.nextDouble();
        sc.nextLine();              //to consume the rest of the line

        System.out.println("Enter Artist:");
        System.out.print(">");
        artist = sc.nextLine();

        System.out.println("Enter Price:");
        System.out.print(">");
        price = sc.nextDouble();
        sc.nextLine();              //to consume the rest of the line
    }
}
