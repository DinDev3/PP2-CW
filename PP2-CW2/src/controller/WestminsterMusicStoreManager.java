package controller;

import model.CD;
import model.Date;
import model.MusicItem;
import model.StoreManager;
import model.Vinyl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WestminsterMusicStoreManager implements StoreManager {
    private static Scanner scanInput = new Scanner(System.in);

    //    private static final int MAX_ITEMS =1000;

    public static HashMap<String, String> allItemIDs = new HashMap<>();             //used to check whether the itemID exists
    protected static ArrayList<MusicItem> itemsInStore = new ArrayList<>();


    public static ArrayList<MusicItem> getItemsInStore() {         //accessed in GUI
        return itemsInStore;
    }


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
    public void addItem() {             //adding new item

        if (MusicItem.getCount() <= MAX_ITEMS) {     //checking whether number of items stored is less than 1000

            System.out.println("\nChoose the type of the item to be added:");
            System.out.println("1)CD\n2)Vinyl");
            System.out.print(">");
            intInputValidation();
            int typeSelection = scanInput.nextInt();
            scanInput.nextLine();              //to consume the rest of the line

            if (typeSelection == 1) {       //CD item chosen
                addCommonInfo();        //used to get common information
                type = "CD";

                System.out.println("Enter Duration of song:");
                System.out.print(">");
                doubleInputValidation();
                duration = scanInput.nextDouble();
                scanInput.nextLine();              //to consume the rest of the line

                MusicItem newCD = new CD(itemID, title, genre, date, artist, price, type, duration);
                itemsInStore.add(newCD);                            //adding CD object into itemsInStore arrayList
                allItemIDs.put(itemID, type);

                //adding to noSQL database
                DatabaseController.addToDB(itemID, title, genre, date.getYear(), date.getMonth(), date.getDay(), artist, price, type, duration);

            } else if (typeSelection == 2) {         //Vinyl item chosen
                addCommonInfo();        //used to get common information
                type = "Vinyl";

                System.out.println("Enter Speed of Vinyl:(RPM)");
                System.out.print(">");
                doubleInputValidation();
                speed = scanInput.nextDouble();
                scanInput.nextLine();              //to consume the rest of the line

                System.out.println("Enter Diameter of Vinyl:(cm)");
                System.out.print(">");
                doubleInputValidation();
                diameter = scanInput.nextDouble();
                scanInput.nextLine();              //to consume the rest of the line


                MusicItem newVinyl = new Vinyl(itemID, title, genre, date, artist, price, type, speed, diameter);
                itemsInStore.add(newVinyl);                            //adding Vinyl object into itemsInStore arrayList
                allItemIDs.put(itemID, type);

                //adding to noSQL database
                DatabaseController.addToDB(itemID, title, genre, date.getYear(), date.getMonth(), date.getDay(), artist, price, type, speed, diameter);

            } else {
                System.out.println("Please choose an option out of 1 & 2");
            }
            System.out.println("\nThere are " + (MAX_ITEMS - MusicItem.getCount()) + " spaces left to store items.");

        } else {
            System.out.println("There are no available spaces. 1000 items have been added!");
        }
    }


    @Override
    public void deleteItem() {                  //delete item by entering item ID
        System.out.println("Enter the itemID of the item that u desire to delete:");
        System.out.print(">");              //get itemID from user to choose item to be deleted
        String searchID = scanInput.nextLine();

        if (allItemIDs.containsKey(searchID)) {
            MusicItem itemToBeDeleted = findItem(searchID);

            type = itemToBeDeleted.getType();
            itemsInStore.remove(itemToBeDeleted);
            allItemIDs.remove(searchID);
            MusicItem.count -= 1;          //decreasing the number of items in store

            //Deleting from noSQL Database
            DatabaseController.deleteFromDB(searchID);

            System.out.println("\nA " + type + " has been deleted");
            System.out.println("There are " + (MAX_ITEMS - MusicItem.getCount()) + " spaces left to store items.");

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
        System.out.println("Items in store sorted in ascending order of title");
    }


    @Override
    public void buyItem() {                 //buy item by selecting item ID and generate a report
        System.out.println("Enter the itemID of the item that u desire to purchase:");
        System.out.print(">");              //get itemID from user to choose item to be deleted
        String searchID = scanInput.nextLine();

        if (allItemIDs.containsKey(searchID)) {
            MusicItem itemToBeBought = findItem(searchID);
            multipleCopies(itemToBeBought, searchID);         //buying multiple items or not?

            System.out.println("\nTotal cost= " + totalCost);

        } else {
            System.out.println("There's no item related to the item ID: " + searchID);
        }
    }


    @Override
    public void viewGUI() {
        GUI.main(null);
    }

//---------reused methods---------

    private static void addCommonInfo() {       //common information related to CD and Vinyl in addItem
        System.out.println("\nEnter Item ID:");
        do {
            System.out.print(">");
            itemID = scanInput.nextLine();
            if (allItemIDs.containsKey(itemID)) {
                System.out.println("This item ID has already been taken. Please enter a different item ID");
            }
        } while (allItemIDs.containsKey(itemID));

        System.out.println("Enter Title:");
        System.out.print(">");
        title = scanInput.nextLine();

        System.out.println("Enter Genre:");
        System.out.print(">");
        genre = scanInput.nextLine();

        System.out.println("Enter Release Date");
        System.out.println("\tEnter day:");                       //getting input for day
        System.out.print("\t>");
        intInputValidation();
        int day = scanInput.nextInt();
        scanInput.nextLine();              //to consume the rest of the line

        System.out.println("\tEnter month:");
        System.out.print("\t>");
        intInputValidation();
        int month = scanInput.nextInt();
        scanInput.nextLine();              //to consume the rest of the line

        System.out.println("\tEnter year:");
        System.out.print("\t>");
        intInputValidation();
        int year = scanInput.nextInt();
        scanInput.nextLine();              //to consume the rest of the line

        date = new Date(year, month, day);

        System.out.println("Enter Artist:");
        System.out.print(">");
        artist = scanInput.nextLine();

        System.out.println("Enter Price (in $):");
        System.out.print(">$");
        doubleInputValidation();
        price = scanInput.nextDouble();
        scanInput.nextLine();              //to consume the rest of the line
    }


    private static MusicItem findItem(String searchItemID) {
        for (MusicItem searchItem : itemsInStore) {
            if (searchItem.getItemID().equals(searchItemID)) {
                return searchItem;
            }
        }
        return null;
    }


    private static void multipleCopies(MusicItem chosenItem, String searchID) {
        String multipleReq;
        totalCost = 0;
        int copies = 1;

        System.out.println("Do you want to buy more than one copy of this item?");
        System.out.print(">");
        multipleReq = scanInput.nextLine().toLowerCase();

        if (multipleReq.equals("yes") || multipleReq.equals("y")) {              //buying more than one copy
            System.out.println("How many copies of this item do you require?");
            System.out.print("\t>");
            intInputValidation();
            copies = scanInput.nextInt();
            scanInput.nextLine();              //to consume the rest of the line

            for (int i = 0; i < copies; i++) {
                totalCost += chosenItem.getPrice();
            }
            generateReport(chosenItem.getTitle(), searchID, chosenItem.getPrice(), copies, totalCost);             //generate report when items are bought
            separatePurchase();               //separating purchases

        } else if (multipleReq.equals("no") || multipleReq.equals("n")) {
            totalCost = chosenItem.getPrice();
            generateReport(chosenItem.getTitle(), searchID, chosenItem.getPrice(), copies, totalCost);             //generate report when items are bought
            //use Math.round for total in file??
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

                soldFile.write(String.format("+---------------------------+------------+------------+---------+-------------+------------------------------+%n"));
                soldFile.write(String.format("|     Title                 | Item ID    |  Price($)  | Copies  |Total Cost($)|       Selling Date/Time      |%n"));
                soldFile.write(String.format("+---------------------------+------------+------------+---------+-------------+------------------------------+%n"));
//                soldFile.write(System.getProperty("line.separator"));       //line break
                soldFile.close();
            }

            String leftAlignFormat2 = "| %-25s | %-10s | %-10s | %-7s | %-11s | %-27s |%n";


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

    private static void intInputValidation() {                     //validating integer input

        while (!scanInput.hasNextInt()) {
            System.out.println("Only integer numbers are allowed! Please provide a valid input");              //error handling message for characters other than integers
            scanInput.next();                                                     //removing incorrect input entered
        }
    }

    private static void doubleInputValidation() {                     //validating double input

        while (!scanInput.hasNextDouble()) {
            System.out.println("Only numbers are allowed! Please provide a valid input");              //error handling message for characters other than integers
            scanInput.next();                                                     //removing incorrect input entered
        }
    }

    public static void separatePurchase() {               //separating purchases in generated report
        try {               //separating purchases
            FileWriter soldFile = new FileWriter("soldItems.txt", true);
            soldFile.write("+------------------------------------------------------------------------------------------------------------+");
            soldFile.write(System.getProperty("line.separator"));       //line break
            soldFile.close();
        } catch (IOException e) {
            System.out.println("\nAn error occurred.");
            e.printStackTrace();
        }
    }
}

/*
References:
https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo

https://www.callicoder.com/java-arraylist/

https://stackoverflow.com/questions/48720936/java-enhanced-for-loop-for-arraylist-with-custom-object

To open GUI from console
https://stackoverflow.com/questions/2550310/can-a-main-method-of-class-be-invoked-from-another-class-in-java

File handling
https://www.w3schools.com/java/java_files.asp

Next line in file handling
https://stackoverflow.com/questions/17716192/insert-line-break-when-writing-to-file

File handling - table format
https://stackoverflow.com/questions/26229140/writing-data-to-text-file-in-table-format

Table display format for print list
https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console

Selling date/time
https://www.javatpoint.com/java-get-current-date

Search for object in ArrayList
https://stackoverflow.com/questions/17526608/how-to-find-an-object-in-an-arraylist-by-property
*/