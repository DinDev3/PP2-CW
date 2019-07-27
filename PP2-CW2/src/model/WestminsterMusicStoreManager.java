package model;

import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterMusicStoreManager implements StoreManager {
    private static Scanner sc = new Scanner(System.in);

    static ArrayList<Object> itemsInStore = new ArrayList<>();
    static ArrayList<Object> shoppingCart = new ArrayList<>();

    static private String itemID;
    static private String title;
    static private String genre;
    static private double date;
    static private String artist;
    static private double price;
    static private double duration;
    static private double speed;
    static private double diameter;

    @Override
    public void addItem() {

        if (MusicItem.getCount() <= maxItems) {     //checking whether number of items stored is less than 1000

            System.out.println("\nChoose item to be added:");
            System.out.println("1)CD\n2)Vinyl");        //validate!!!!!!
            System.out.print(">");
            int itemType = sc.nextInt();
            sc.nextLine();              //to consume the rest of the line

            if (itemType == 1) {       //CD item chosen
                addCommonInfo();        //used to get common information

                System.out.println("Enter Duration of song:");
                System.out.print(">");
                duration = sc.nextDouble();
                CD.cdDuration.put(itemID, duration);             //adding duration to hashMap
                sc.nextLine();              //to consume the rest of the line

                CD newCD = new CD(itemID, title, genre, artist, price, duration);
                itemsInStore.add(newCD);                            //adding CD object into itemsInStore arrayList

                System.out.println(CD.cdDuration);               //to check!!!!!!!!!


            } else if (itemType == 2) {         //Vinyl item chosen
                addCommonInfo();        //used to get common information

                System.out.println("Enter Speed of Vinyl:");
                System.out.print(">");
                speed = sc.nextDouble();
                Vinyl.vinylSpeed.put(itemID, speed);             //adding speed into hashMap
                sc.nextLine();              //to consume the rest of the line

                System.out.println("Enter Diameter of Vinyl:");
                System.out.print(">");
                diameter = sc.nextDouble();
                Vinyl.vinylDiameter.put(itemID, diameter);             //adding diameter into hashMap
                sc.nextLine();              //to consume the rest of the line


                Vinyl newVinyl = new Vinyl(itemID, title, genre, artist, price, speed, diameter);
                itemsInStore.add(newVinyl);                            //adding Vinyl object into itemsInStore arrayList

                System.out.println(Vinyl.vinylDiameter);               //to check!!!!!!!!!
                System.out.println(Vinyl.vinylSpeed);               //to check!!!!!!!!!
            }
            System.out.println(itemsInStore);               //to check!!!!!!!!!


            System.out.println("There are " + (maxItems - MusicItem.getCount()) + " spaces left to store items.");

        } else {
            System.out.println("There are no available spaces. 1000 items have been added!");
        }
    }


    @Override
    public void deleteItem() {

        System.out.println("Enter itemID of item that u desire to delete:");
        System.out.print(">");              //get itemID from user to choose item to be deleted
        String searchID = sc.nextLine();

        MusicItem searchMusicItem = new CD(searchID, title, genre, artist, price, duration);
        MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
        //use if else to search for CD/ Vinyl separately!!!!!!!!!!!!!

        System.out.println(MusicItem.getCount());

        Object itemToBeDeleted = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

        if (CD.cdDuration.containsKey(searchID)) {     //if itemID that isn't in the store is entered, 1st item is given
//            System.out.println(itemsInStore.get(linearSearch(itemsInStore, searchMusicItem)));    //checking whether correct item was selected
            CD.cdDuration.remove(searchID);
            itemsInStore.remove(itemToBeDeleted);

            MusicItem.count -= 1;          //decreasing the number of items in store
            System.out.println("There are " + (maxItems - MusicItem.getCount()) + " spaces left to store items.");

            System.out.println("A CD has been deleted");
        } else {
            System.out.println("There's no item related to the item ID: " + searchID);
        }


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


    private static void addCommonInfo() {       //common information related to CD and Vinyl in addItem
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

        System.out.println("Enter Price (in $):");
        System.out.print(">$");
        price = sc.nextDouble();
        sc.nextLine();              //to consume the rest of the line
    }

    public static int linearSearch(ArrayList itemsInStore, MusicItem searchValue) {
        int itemIndex = 0;
        for (int i = 0; i < itemsInStore.size(); i++) {            //searching indexes of a given integer.(Linear Search)
            if (itemsInStore.get(i).equals(searchValue)) {
                itemIndex = i;
            }
        }
        return itemIndex;
    }

}
