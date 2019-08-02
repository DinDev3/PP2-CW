package controller;

import model.*;
import model.Date;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WestminsterMusicStoreManager implements StoreManager {
    private static Scanner sc = new Scanner(System.in);

    private static final int MAX_COPIES = 20;                           //maximum quantity of a selected item in-store
    //    private static final int MAX_ITEMS =1000;
    protected static ArrayList<MusicItem> itemsInStore = new ArrayList<>();
    private static ArrayList<MusicItem> shoppingCart = new ArrayList<>();

    public static ArrayList<MusicItem> getItemsInStore() {         //accessed in GUI
        return itemsInStore;
    }

    public static HashMap<String, String> allItemIDs = new HashMap<>();             //used to check whether itemID exists


    private static String itemID;
    private static String type;
    private static String genre;
    private static Date date;
    private static String artist;
    private static double price;
    private static double duration;
    private static double speed;
    private static double diameter;
    private static double totalCost;
    private static String title;

    @Override
    public void addItem() {             //adding new item                 //check passing Music Item item!!!!!!!!!!

        if (MusicItem.getCount() <= MAX_ITEMS) {     //checking whether number of items stored is less than 1000

            System.out.println("\nChoose item to be added:");
            System.out.println("1)CD\n2)Vinyl");        //validate for integer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.print(">");
            int typeSelection = sc.nextInt();
            sc.nextLine();              //to consume the rest of the line

            if (typeSelection == 1) {       //CD item chosen
                addCommonInfo();        //used to get common information
                type = "CD";

                System.out.println("Enter Duration of song:");
                System.out.print(">");
                duration = sc.nextDouble();
                sc.nextLine();              //to consume the rest of the line

                MusicItem newCD = new CD(itemID, title, genre, date, artist, price, type, duration);
                itemsInStore.add(newCD);                            //adding CD object into itemsInStore arrayList
                allItemIDs.put(itemID, type);

                //adding to noSQL database
                DatabaseController.addToDB(itemID, title, genre, date.getYear(),date.getMonth(),date.getDay(), artist, price, type, duration);

            } else if (typeSelection == 2) {         //Vinyl item chosen
                addCommonInfo();        //used to get common information
                type = "Vinyl";

                System.out.println("Enter Speed of Vinyl:(RPM)");
                System.out.print(">");
                speed = sc.nextDouble();
                sc.nextLine();              //to consume the rest of the line

                System.out.println("Enter Diameter of Vinyl:(cm)");
                System.out.print(">");
                diameter = sc.nextDouble();
                sc.nextLine();              //to consume the rest of the line


                MusicItem newVinyl = new Vinyl(itemID, title, genre, date, artist, price, type, speed, diameter);
                itemsInStore.add(newVinyl);                            //adding Vinyl object into itemsInStore arrayList
                allItemIDs.put(itemID, type);

                //adding to noSQL database
                DatabaseController.addToDB(itemID, title, genre, date.getYear(),date.getMonth(),date.getDay(), artist, price, type, speed, diameter);
            } else {
                System.out.println("Please choose an option out of 1 & 2");
            }
            System.out.println("There are " + (MAX_ITEMS - MusicItem.getCount()) + " spaces left to store items.");

        } else {
            System.out.println("There are no available spaces. 1000 items have been added!");
        }
    }


    @Override
    public void deleteItem() {                  //delete item by entering item ID
        System.out.println("Enter itemID of item that u desire to delete:");
        System.out.print(">");              //get itemID from user to choose item to be deleted
        String searchID = sc.nextLine();

        if (allItemIDs.containsKey(searchID)) {
            if (allItemIDs.containsValue("CD")) {
                MusicItem searchMusicItem = new CD(searchID, title, genre, date, artist, price, type, duration);
                MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
                MusicItem itemToBeBought = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

                System.out.println("A " + itemToBeBought.getType() + " has been deleted");
                itemsInStore.remove(itemToBeBought);
                allItemIDs.remove(searchID);
                MusicItem.count -= 1;          //decreasing the number of items in store
                System.out.println("There are " + (MAX_ITEMS - MusicItem.getCount()) + " spaces left to store items.");

            } else if (allItemIDs.containsValue("Vinyl")) {
                MusicItem searchMusicItem = new Vinyl(searchID, title, genre, date, artist, price, type, speed, diameter);
                MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
                MusicItem itemToBeBought = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

                System.out.println("A " + itemToBeBought.getType() + " has been deleted");
                itemsInStore.remove(itemToBeBought);
                MusicItem.count -= 1;          //decreasing the number of items in store
                System.out.println("There are " + (MAX_ITEMS - MusicItem.getCount()) + " spaces left to store items.");
                allItemIDs.remove(searchID);

            }
            //Deleting from noSQL Database
            DatabaseController.deleteFromDB(searchID);

        } else {
            System.out.println("There's no item related to the item ID: " + searchID);
        }
    }


    @Override
    public void printList()                 //prints list of items in store
    {   //print only item ID, item type, title

        String leftAlignFormat = "| %-10s | %-5s | %-25s |%n";

        System.out.format("+------------+-------+---------------------------+%n");
        System.out.format("| Item ID    | Type  |     Title                 |%n");
        System.out.format("+------------+-------+---------------------------+%n");

        for (MusicItem item : itemsInStore) {
            if (item instanceof CD) {
                System.out.format(leftAlignFormat, item.getItemID(), "CD", item.getTitle());
            } else if (item instanceof Vinyl) {
                System.out.format(leftAlignFormat, item.getItemID(), "Vinyl", item.getTitle());
            }
        }
        System.out.format("+------------+-------+---------------------------+%n");
    }


    @Override
    public void sortItem() {                //sorts items in ascending order of title
        Collections.sort(itemsInStore);
    }


    @Override
    public void buyItem() {                 //buy item by selecting item ID and generate a report
        System.out.println("Enter itemID of item that u desire to buy:");
        System.out.print(">");              //get itemID from user to choose item to be deleted
        String searchID = sc.nextLine();

        if (allItemIDs.containsKey(searchID)) {
            if (allItemIDs.containsValue("CD")) {
                MusicItem searchMusicItem = new CD(searchID, title, genre, date, artist, price, type, duration);
                MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
                MusicItem itemToBeBought = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

                multipleCopies(itemToBeBought, searchID);         //buying multiple items or not?
                System.out.println("\nTotal cost= " + totalCost);
            } else if (allItemIDs.containsValue("Vinyl")) {
                MusicItem searchMusicItem = new Vinyl(searchID, title, genre, date, artist, price, type, speed, diameter);
                MusicItem.count -= 1;         //making sure that count isn't increased for the temporary object created
                MusicItem itemToBeBought = itemsInStore.get(linearSearch(itemsInStore, searchMusicItem));

                multipleCopies(itemToBeBought, searchID);         //buying multiple items or not?
                System.out.println("\nTotal cost= " + totalCost);
            }

            //quantity to be added!!!!!!!!!!!!!!!

        } else {
            System.out.println("There's no item related to the item ID: " + searchID);
        }

        shoppingCart.clear();           //emptying the shopping cart
    }


    @Override
    public void viewGUI() {
        GUI.main(null);
    }

//---------reused methods---------

    private static void addCommonInfo() {       //common information related to CD and Vinyl in addItem
        System.out.println("\nEnter Item ID:");             //!!!!!!!!!!!!have an if with AND to check whether itemID exists in Arraylist of itemsInStore
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

    private static void multipleCopies(MusicItem chosenItem, String searchID) {
        String multipleReq;
        totalCost = 0;
        int copies = 1;

        System.out.println("Do you want to buy more than one copy of this item?");
        System.out.print(">");
        multipleReq = sc.nextLine().toLowerCase();

        if (multipleReq.equals("yes") || multipleReq.equals("y")) {              //buying more than one copy
            System.out.println("How many copies of this item do you require?");
            System.out.print("\t>");
            copies = sc.nextInt();
            sc.nextLine();              //to consume the rest of the line

            for (int i = 0; i < copies; i++) {
                shoppingCart.add(chosenItem);
                totalCost += chosenItem.getPrice();
            }
            generateReport(chosenItem.getTitle(), searchID, chosenItem.getPrice(), copies, totalCost);             //generate report when items are bought
            separatePurchase();               //separating purchases

        } else if (multipleReq.equals("no") || multipleReq.equals("n")) {
            shoppingCart.add(chosenItem);
            totalCost = chosenItem.getPrice();
            generateReport(chosenItem.getTitle(), searchID, chosenItem.getPrice(), copies, totalCost);             //generate report when items are bought

            separatePurchase();               //separating purchases

        } else {
            System.out.println("Invalid input. Please try again.");             //loop this and handle!!!!!!
        }

    }

    private static void generateReport(String titleOfBought, String IDOfBought, Double priceOfBought, int numOfCopies, double total) {
        try {       //creating the file
            File myFile = new File("soldItems.txt");
            if (myFile.createNewFile()) {
//                System.out.println("\nFile created: " + myFile.getName());
                FileWriter soldFile = new FileWriter("soldItems.txt", true);

                soldFile.write(String.format("+---------------------------+------------+------------+---------+------------+------------------------------+%n"));
                soldFile.write(String.format("|     Title                 | Item ID    |  Price($)  | Copies  |  Total($)  |       Selling Date/Time      |%n"));
                soldFile.write(String.format("+---------------------------+------------+------------+---------+------------+------------------------------+%n"));
//                soldFile.write(System.getProperty("line.separator"));       //line break
                soldFile.close();
            }

            String leftAlignFormat2 = "| %-25s | %-10s | %-10s | %-7s | %-10s | %-27s |%n";


            //writing into the file
            FileWriter soldFile = new FileWriter("soldItems.txt", true);

            java.util.Date sellingDate = Calendar.getInstance().getTime();          //getting current(selling) time and date
            soldFile.write(String.format(leftAlignFormat2, titleOfBought, IDOfBought, priceOfBought, numOfCopies, total, sellingDate));
//            soldFile.write(System.getProperty("line.separator"));       //line break
            soldFile.close();


        } catch (IOException e) {
            System.out.println("\nAn error occurred.");
            e.printStackTrace();
        }
    }


    public static void separatePurchase() {               //separating purchases
        try {               //separating purchases
            FileWriter soldFile = new FileWriter("soldItems.txt", true);
            soldFile.write("+-----------------------------------------------------------------------------------------------------------+");
            soldFile.write(System.getProperty("line.separator"));       //line break
            soldFile.close();
        } catch (IOException e) {
            System.out.println("\nAn error occurred.");
            e.printStackTrace();
        }
    }
}
