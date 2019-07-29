package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WestminsterMusicStoreManager implements StoreManager {
    private static Scanner sc = new Scanner(System.in);

    static ArrayList<Object> itemsInStore = new ArrayList<>();
    static ArrayList<Object> shoppingCart = new ArrayList<>();

    static HashMap<String, String> itemTitles = new HashMap<>();        //to retrieve item titles

    static private String itemID;
    static private String title;
    static private String genre;
    static private Date date;           //object is created here as it's required to be accessed from several methods
    static private String artist;
    static private double price;
    static private double duration;
    static private double speed;
    static private double diameter;
    static private double totalCost;

    @Override
    public void addItem() {             //adding new item

        if (MusicItem.getCount() <= maxItems) {     //checking whether number of items stored is less than 1000

            System.out.println("\nChoose item to be added:");
            System.out.println("1)CD\n2)Vinyl");        //validate for integer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

                MusicItem newCD = new CD(itemID, title, genre, date, artist, price, duration);
                itemsInStore.add(newCD);                            //adding CD object into itemsInStore arrayList
                itemTitles.put(newCD.getItemID(), newCD.getTitle());

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


                Vinyl newVinyl = new Vinyl(itemID, title, genre, date, artist, price, speed, diameter);
                itemsInStore.add(newVinyl);                            //adding Vinyl object into itemsInStore arrayList
                itemTitles.put(newVinyl.getItemID(), newVinyl.getTitle());

                System.out.println(Vinyl.vinylDiameter);               //to check!!!!!!!!!
                System.out.println(Vinyl.vinylSpeed);               //to check!!!!!!!!!
            } else {
                System.out.println("Please choose an option out of 1 & 2");
            }
//            System.out.println(itemsInStore);               //to check
            System.out.println("There are " + (maxItems - MusicItem.getCount()) + " spaces left to store items.");


        } else {
            System.out.println("There are no available spaces. 1000 items have been added!");
        }
    }


    @Override
    public void deleteItem() {                  //delete item by entering item ID

        System.out.println("Enter itemID of item that u desire to delete:");
        System.out.print(">");              //get itemID from user to choose item to be deleted
        String searchID = sc.nextLine();

        if (CD.cdDuration.containsKey(searchID)) {     //if itemID that isn't in the store is entered, 1st item is given!!!!!!!!!FIX!!!!!!!!
            MusicItem searchMusicItem = new CD(searchID, title, genre, date, artist, price, duration);
            MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
            Object itemToBeDeleted = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));
//            System.out.println(itemsInStore.get(linearSearch(itemsInStore, searchMusicItem)));    //checking whether correct item was selected

            CD.cdDuration.remove(searchID);
            itemsInStore.remove(itemToBeDeleted);
            MusicItem.count -= 1;          //decreasing the number of items in store

            System.out.println("There are " + (maxItems - MusicItem.getCount()) + " spaces left to store items.");
            System.out.println("A CD has been deleted");

        } else if (Vinyl.vinylDiameter.containsKey(searchID)) {
            MusicItem searchMusicItem = new Vinyl(searchID, title, genre, date, artist, price, speed, diameter);
            MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
            Object itemToBeDeleted = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

            Vinyl.vinylDiameter.remove(searchID);
            Vinyl.vinylSpeed.remove(searchID);
            itemsInStore.remove(itemToBeDeleted);
            MusicItem.count -= 1;          //decreasing the number of items in store

            System.out.println("There are " + (maxItems - MusicItem.getCount()) + " spaces left to store items.");
            System.out.println("A Vinyl has been deleted");

        } else {
            System.out.println("There's no item related to the item ID: " + searchID);
        }


    }


    @Override
    public void printList()                 //prints list of items in store
    {   //print only item ID, item type, title

        System.out.println(itemsInStore);           //to check !!!!!!!!!

        for (Map.Entry<String, String> entry : itemTitles.entrySet()) {         //checking for all HashMap entries
            if (CD.cdDuration.containsKey(entry.getKey())) {                 //checking whether the ID selected is of a CD
                System.out.println(entry.getKey() + "\t" + entry.getValue() + "\tCD");

            } else if (Vinyl.vinylDiameter.containsKey(entry.getKey())) {
                System.out.println(entry.getKey() + "\t" + entry.getValue() + "\tVinyl");
            }
        }

    }


    @Override
    public void sortItem() {                //sorts items in ascending order of title

    }


    @Override
    public void buyItem() {                 //buy item by selecting item ID and generate a report
        System.out.println("Enter itemID of item that u desire to buy:");
        System.out.print(">");              //get itemID from user to choose item to be deleted
        String searchID = sc.nextLine();

        if (CD.cdDuration.containsKey(searchID)) {     //if itemID that isn't in the store is entered, 1st item is given!!!!!!!!!!!FIX!!!!!!
            MusicItem searchMusicItem = new CD(searchID, title, genre, date, artist, price, duration);
            MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
            Object itemToBeBought = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

            multipleCopies(itemToBeBought);         //buying multiple items or not?
            System.out.println("Total cost= " + totalCost);

        } else if (Vinyl.vinylDiameter.containsKey(searchID)) {
            MusicItem searchMusicItem = new Vinyl(searchID, title, genre, date, artist, price, speed, diameter);
            MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
            Object itemToBeBought = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

            multipleCopies(itemToBeBought);         //buying multiple items or not?
            System.out.println("Total cost= " + totalCost);

        } else {
            System.out.println("There's no item related to the item ID: " + searchID);
        }

        System.out.println(shoppingCart);               //to check

        shoppingCart.clear();           //emptying the shopping cart
    }


//---------reused methods---------

    private static void addCommonInfo() {       //common information related to CD and Vinyl in addItem
        System.out.println("\nEnter Item ID:");             //!!!!!!!!!!!!have an if with AND to check whether itemID exists in hashMaps of either CD/Vinyl!!!!!!!!!!!
        System.out.print(">");
        itemID = sc.nextLine();

        System.out.println("Enter Title:");
        System.out.print(">");
        title = sc.nextLine();

        System.out.println("Enter Genre:");
        System.out.print(">");
        genre = sc.nextLine();

        System.out.println("Enter Release Date");
        System.out.println("\tEnter day:");                       //getting input for day
        System.out.print("\t>");
        int day = sc.nextInt();
        sc.nextLine();              //to consume the rest of the line

        System.out.println("\tEnter month:");
        System.out.print("\t>");
        int month = sc.nextInt();
        sc.nextLine();              //to consume the rest of the line

        System.out.println("\tEnter year:");
        System.out.print("\t>");
        int year = sc.nextInt();
        sc.nextLine();              //to consume the rest of the line

        date = new Date(year, month, day);


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

    private static void multipleCopies(Object chosenItem) {
        String multipleReq;
        totalCost = 0;

        System.out.println("Do you want to buy more than one copy of this item?");
        System.out.print(">");
        multipleReq = sc.nextLine().toLowerCase();

        if (multipleReq.equals("yes") || multipleReq.equals("y")) {              //buying more than one copy
            System.out.println("How many copies of this item do you require?");
            System.out.print("\t>");
            int copies = sc.nextInt();
            sc.nextLine();              //to consume the rest of the line

            for (int i = 0; i < copies; i++) {
                shoppingCart.add(chosenItem);
                totalCost += price;
            }

        } else if (multipleReq.equals("no") || multipleReq.equals("n")) {
            shoppingCart.add(chosenItem);
            totalCost = price;
        } else {
            System.out.println("Invalid input. Please try again.");             //loop this and handle!!!!!!
        }

    }

}
