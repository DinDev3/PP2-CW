import model.WestminsterMusicStoreManager;

import java.util.Scanner;

public class ConApp {
    public static void main(String[] args) {
        int chosenOption;

        System.out.println("\\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/");
        System.out.println("~~\tOnline Music Store Management System\t~~");
        System.out.println("/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\\");

        do {//display main menu
            System.out.println("\n1)Add item");
            System.out.println("2)Delete item");
            System.out.println("3)Print list of items");
            System.out.println("4)Sort items");
            System.out.println("5)Buy items");
            System.out.println("6)Open GUI");
            System.out.println("7)Exit program");
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter Option:\n>>");
            chosenOption = sc.nextInt();                    //validate for integer inputs!!!!!!!!!!

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
                    break;

                case 7:
                    //display exit message
                    System.exit(0);

                default:
                    System.out.println("Invalid input. Please try again");
            }
        } while (chosenOption != 7);
    }
}
