//Dinuka Piyadigama
//UOW ID - w17421047
//IIT ID - 2018373

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class GolfClub {
    static HashMap<Integer, String> playerRecords = new HashMap<Integer, String>();
    static ArrayList<String> playerNames = new ArrayList<>();
    static ArrayList<Integer> playerResults = new ArrayList<>();

    static Scanner scanInput = new Scanner(System.in);                  //getting input, for process that needs to be carried out

    public static void main(String[] args) {

        int input;                                         //initializing input
        System.out.println("\nWelcome to Springfield Golf Club!");

        do {
            input = 0;                                          //resetting input


            while (input <= 0) {
                System.out.println("\nSelect Option:\n\t1) Enter Scores\n\t2) Find Golfer\n\t3) Display Scoreboard\n\t4) Exit Program");

                System.out.print("\nEnter input in range 1-4\n\t>");

                inputValidation();                     //validating integer input

                input = scanInput.nextInt();

                Collections.sort(playerResults);                //sorting scores in ascending order
                playerNames.clear();                            //removing names from arrayList, to match the same positions as the new/updated results
                for (int i = 0; i < playerResults.size(); i++) {
                    int p = playerResults.get(i);                       //selecting key, to find value

                    playerNames.add(playerRecords.get(p));              //adding value of selected key into playerNames arrayList
                }

                switch (input) {
                    case 1:
                        enterScores();
                        break;


                    case 2:
                        findGolfer();
                        break;


                    case 3:
                        displayScores();
                        break;

                    case 4:
                        System.out.println("\nWe hope you enjoyed your Golf session at Springfield Golf Club.");      //Exit message, when exiting program
                        System.out.println("\tLooking forward to assist you in your next session.");
                        System.out.println("\tExiting Program...");
                        System.exit(0);                             //ending program
                        break;

                    default:                                            //message to display, if input number not in required range
                        System.out.println("Invalid input!!! Reenter...");
                }

            }

        } while (input !=4);

    }



//-----methods-----
    private static void enterScores() {//input = 1
        int numOfGolfers = 0;

        //getting the count of golfers in the group
        System.out.println("How many golfers are in this group?");

        inputValidation();                     //validating integer input
        numOfGolfers = scanInput.nextInt();


        while (numOfGolfers > 0) {
            Scanner scanName = new Scanner(System.in);
            System.out.println("\nEnter a name of a Golfer");
            String name = scanName.nextLine();


            if (playerRecords.containsValue(name)) {

                Scanner alterInput = new Scanner(System.in);                  //Asking the user whether to alter results or not
                System.out.println("This Golfer has a previous entry! Do you wish to over-write this?");
                String alter = alterInput.nextLine();

                alter = alter.toLowerCase();                        //to accept input in capital letters as well    |   reassigned because strings are immutable

                if (alter.equals("yes") || alter.equals("y")) {
                    int result = 0;                                 //resetting result that will be input, if the user wishes to change it
                    int position = 0;                                       //resetting position

                    for (int n = 0; n < playerNames.size(); n++) {                        //n is an index of the two Array Lists, above
                        if (playerNames.get(n).equals(name)) {
                            position = n;
                        }
                    }

                    int oldResult = playerResults.get(position);

                    playerRecords.remove(oldResult);               //removing old key entry
                    playerResults.remove(position);                //removing old result from results array list

                    while (result < 18 || result > 108) {                             //result range: 18-108
                        //getting result of golfer
                        System.out.print("Enter the result (in range 18-108): ");

                        inputValidation();                     //validating integer input
                        result = scanInput.nextInt();
                    }

                    playerRecords.put(result, name);                //updating entry
                    playerResults.add(result);

                    numOfGolfers -= 1;

                } else if (alter.equals("no") || alter.equals("n")) {
                    System.out.println("The record for Golfer, " + name + " wasn't changed.");

                } else {
                    System.out.println("Invalid input. Please try again.");
                }

            } else {
                int result;                                 //initialing result to get the while loop to work
                do {                                            //result range: 18-108

                    //getting result of golfer
                    System.out.print("Enter the result (in range 18-108): ");

                    inputValidation();                     //validating integer input
                    result = scanInput.nextInt();

                } while (result < 18 || result > 108);
                playerRecords.put(result, name);                //new entry
                playerResults.add(result);

                numOfGolfers -= 1;
            }
        }
    }


    private static void findGolfer() {//input = 2

        Scanner scanName = new Scanner(System.in);
        System.out.println("\nEnter a name of a Golfer");
        String name = scanName.nextLine();

        if (playerRecords.containsValue(name)) {
            int position = 0;                                       //resetting position

            for (int n = 0; n < playerNames.size(); n++) {                        //n is an index of the two Array Lists
                if (playerNames.get(n).equals(name)) {
                    position = n;
                }

            }

            System.out.println("Score: " + playerResults.get(position));
        } else {
            System.out.println("There is no recorded entry related to this name.");
        }

    }


    private static void displayScores() {//input = 3

        System.out.println("\n-----------------------------------------");                               //for clarity of output
        System.out.println("Name       Score");
        System.out.println("-----------------------------------------");                               //for clarity of output

        for (int i = 0; i < playerResults.size(); i++) {

            System.out.println(playerNames.get(i) + "       " + playerResults.get(i));

            System.out.println("-----------------------------------------");                               //for clarity of output
        }
    }


    private static void inputValidation() {                     //validating integer input

        while (!scanInput.hasNextInt()) {
            System.out.println("Only integer numbers are allowed! Please provide a valid input");              //error handling message for characters other than integers
            scanInput.next();                                                     //removing incorrect input entered
        }
    }
}