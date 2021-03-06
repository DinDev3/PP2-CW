import controller.DatabaseController;
import controller.WestminsterMusicStoreManager;

import java.util.Scanner;


public class ConApp {
    public static void main(String[] args) {
        int chosenOption;

        DatabaseController.importDB();              //importing items saved in database to itemsInStore ArrayList

        do {
            System.out.println("\n\t\\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/");
            System.out.println("\t~~\tOnline Music Store Management System\t~~");
            System.out.println("\t/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\\");

            //display main menu
            System.out.println("\n1)Add item");
            System.out.println("2)Delete item");
            System.out.println("3)Print list of items");
            System.out.println("4)Sort items");
            System.out.println("5)Buy items");
            System.out.println("6)Open GUI");
            System.out.println("7)Exit program");
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter Option:\n>>");

            while (!sc.hasNextInt()) {              //validation for integer input
                System.out.println("Only integer numbers are allowed! Please provide a valid input");              //error handling message for characters other than integers
                sc.next();                                                     //removing incorrect input entered
            }

            chosenOption = sc.nextInt();

            WestminsterMusicStoreManager managementAction = new WestminsterMusicStoreManager();         //new object

            switch (chosenOption) {
                case 1:         //add item
                    managementAction.addItem();
                    break;

                case 2:         //delete item
                    managementAction.deleteItem();
                    break;

                case 3:         //print list
                    managementAction.printList();
                    break;

                case 4:         //sort items
                    managementAction.sortItem();
                    break;

                case 5:         //buy items
                    managementAction.buyItem();
                    break;

                case 6:         //open GUI
                    managementAction.viewGUI();
                    break;

                case 7:         //display exit message
                    System.out.println("\nThank you for using the Online Music Store Manager.");
                    System.out.println("\tLooking forward to assist you in the future.");
                    System.out.println("\tExiting Program...");
                    System.exit(0);

                default:
                    System.out.println("Invalid input. Please try again");
            }
        } while (chosenOption != 7);
    }
}
