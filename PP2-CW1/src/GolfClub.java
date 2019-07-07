//Dinuka Piyadigama
//UOW ID - w17421047
//IIT ID - 2018373

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public abstract class GolfClub {
    static HashMap<String, Integer> playerRecords = new HashMap<>();
    static ArrayList<String> playerNames = new ArrayList<>();
    static ArrayList<Integer> playerResults = new ArrayList<>();

    static HashMap<String, Integer> deletedRecords = new HashMap<>();               //to add deleted records in order to restore if required.

    static Scanner scanInput = new Scanner(System.in);                  //getting input, for process that needs to be carried out
    static Scanner scanName = new Scanner(System.in);                   //getting name input

    static Stack<String> stackUndoName = new Stack<>();                        //stack used for undo name of Golfer
    static Stack<Integer> stackUndoScore = new Stack<>();                      //stack used for undo score of Golfer
    static Stack<String> stackRedoName = new Stack<>();                        //stack used for redo name of Golfer
    static Stack<Integer> stackRedoScore = new Stack<>();                      //stack used for redo score of Golfer
    static Stack<String> stackUndoCommand = new Stack<>();                     //stack used to identify process to undo
    static Stack<String> stackRedoCommand = new Stack<>();                     //stack used to identify process to redo

    static int changedScore;                   //used to redo, undone editing of score

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
                playerNames.clear();                            //removing names from arrayList,

                //matching the names with the same positions as the new/updated results
                for (int i = 0; i < playerResults.size(); i++) {            //considering all positions of records
                    int p = playerResults.get(i);                   //score at the sorted position

                    //if same score is entered in this method, things get messed up when displaying an deleting (some names won't match scores)
                    for (Map.Entry<String, Integer> entry : playerRecords.entrySet()) {         //checking for all HashMap entries
                        if (p == entry.getValue())
                            playerNames.add(entry.getKey());              //adding value of selected key into playerNames arrayList
                    }
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

        } while (input != 4);

    }


    //------------------------------------------methods----------------------------------------------


    //---~~~main menu methods~~~---


    private static void enterScores() {//input = 1
        int inputSub;

        do {
            inputSub = 0;                                          //resetting input


            System.out.println("\n~Enter Scores~\n\t1) Enter Details\n\t2) Edit Records\n\t3) Delete Records\n\t4) Restore Records" +
                    "\n\t5) Undo\n\t6) Redo\n\t7) Back to Main Menu");

            System.out.print("\nEnter input in range 1-7\n\t>");

            inputValidation();                     //validating integer input

            inputSub = scanInput.nextInt();

            switch (inputSub) {
                case 1:
                    enterDetails();
                    break;

                case 2:
                    editData();
                    break;

                case 3:
                    deleteData();
                    break;

                case 4:
                    restoreData();
                    break;

                case 5:
                    undo();
                    break;

                case 6:
                    redo();
                    break;

                case 7:
                    break;

                default:                                            //message to display, if input number not in required range
                    System.out.println("Invalid input!!! Reenter...");
            }

        } while (inputSub < 1 || inputSub > 7);

    }


    private static void findGolfer() {//input = 2

        System.out.println("\nEnter a name of a Golfer");
        String name = scanName.nextLine();

        if (playerRecords.containsKey(name)) {
            System.out.println("Score: " + playerRecords.get(name));

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
        //System.out.println("stack: "+stackUndoName.peek());       //used to check whether stack contains required data
    }


    private static void inputValidation() {                     //validating integer input

        while (!scanInput.hasNextInt()) {
            System.out.println("Only integer numbers are allowed! Please provide a valid input");              //error handling message for characters other than integers
            scanInput.next();                                                     //removing incorrect input entered
        }
    }


    private static void changeScore(String enteredName) {

        int result = 0;                                 //resetting result that will be input, if the user wishes to change it
        int position = 0;                                       //resetting position

        //finding the same positions of the name entered in both playerNames & playerResults ArrayLists
        for (int n = 0; n < playerNames.size(); n++) {                        //n is an index of the two Array Lists, above
            if (playerNames.get(n).equals(enteredName)) {                          //getting matching position of score to name
                position = n;
            }
        }

        //playerRecords value will be automatically replaced
        playerResults.remove(position);                //removing old result from results array list

        while (result < 18 || result > 108) {                             //result range: 18-108
            //getting result of golfer
            System.out.print("Enter the result (in range 18-108): ");

            inputValidation();                     //validating integer input
            result = scanInput.nextInt();
        }

        //updating undo stacks
        stackUndoScore.push(playerRecords.get(enteredName));
        stackUndoCommand.push("edit");

        playerRecords.put(enteredName, result);                //updating entry
        playerResults.add(result);

    }


    //---~~~sub menu methods~~~---
    private static void enterDetails() {
        //getting the count of golfers in the group
        System.out.println("How many golfers are in this group?");

        inputValidation();                     //validating integer input
        int numOfGolfers = scanInput.nextInt();


        while (numOfGolfers > 0) {

            System.out.println("\nEnter a name of a Golfer");
            String name = scanName.nextLine();


            if (playerRecords.containsKey(name)) {

                Scanner alterInput = new Scanner(System.in);                  //Asking the user whether to alter results or not
                System.out.println("This Golfer has a previous entry! Do you wish to over-write this?");
                String alter = alterInput.nextLine();

                alter = alter.toLowerCase();                        //to accept input in capital letters as well    |   reassigned because strings are immutable

                if (alter.equals("yes") || alter.equals("y")) {

                    changeScore(name);                              //change the score of the record related to this name
                    stackUndoName.push(name);

                } else if (alter.equals("no") || alter.equals("n")) {
                    System.out.println("The record for Golfer, " + name + " wasn't changed.");

                } else {
                    System.out.println("Invalid input.");
                }

            } else {
                int result;                                 //initialing result to get the while loop to work
                do {                                            //result range: 18-108

                    //getting result of golfer
                    System.out.print("Enter the result (in range 18-108): ");

                    inputValidation();                     //validating integer input
                    result = scanInput.nextInt();

                } while (result < 18 || result > 108);
                playerRecords.put(name, result);                //new entry
                playerResults.add(result);

                //updating undo stacks
                stackUndoCommand.push("add");
                stackUndoName.push(name);
                stackUndoScore.push(result);

            }
            numOfGolfers -= 1;
        }
    }


    private static void editData() {
        displayScores();                //show the user the available records

        System.out.println("Enter a name that you want to edit the score of: ");
        String name = scanName.nextLine();
        if (playerRecords.containsKey(name)) {
            changeScore(name);                              //change the score of the record related to this name
            stackUndoName.push(name);

        } else {
            System.out.println("There is no record related to this name");
        }

    }


    private static void deleteData() {
        displayScores();

        System.out.println("Enter a name that you want to delete the record of: ");
        String name = scanName.nextLine();

        if (playerRecords.containsKey(name)) {

            deletedRecords.put(name, playerRecords.get(name));                //adding deleted record into HashMap
            playerRecords.remove(name);


            int position = 0;                                       //resetting position
            //finding the same positions of the name entered in both playerNames & playerResults ArrayLists
            for (int n = 0; n < playerNames.size(); n++) {                        //n is an index of the two Array Lists, above
                if (playerNames.get(n).equals(name)) {                          //getting matching position of score to name
                    position = n;
                }
            }

            //updating undo stacks
            stackUndoCommand.push("delete");
            stackUndoName.push(name);
            stackUndoScore.push(playerResults.get(position));


            //playerRecords value will be automatically removed
            playerResults.remove(position);                //removing old result from results array list

            System.out.println("The record for, " + name + " was deleted");


        } else {
            System.out.println("There is no recorded entry related to this name.");
        }
    }


    private static void restoreData() {
        System.out.println("Enter a name that you want to restore the record of: ");
        String name = scanName.nextLine();

        if (deletedRecords.containsKey(name)) {
            //updating undo stacks
            stackUndoCommand.push("restore");
            stackUndoName.push(name);
            stackUndoScore.push(deletedRecords.get(name));


            //changing in playerResults
            playerResults.remove(playerRecords.get(name));                //removing previously changed result from playerResults array list
            playerResults.add(deletedRecords.get(name));        //restoring deleted record to playerRecords ArrayList

            playerRecords.put(name, deletedRecords.get(name));                //adding deleted record into HashMap

            deletedRecords.remove(name);                        //removing record from deleted, since restored now.
            System.out.println("The record for player, " + name + " was restored.");

        } else {
            System.out.println("There is no deleted record related to this name.");
        }
    }


    private static void undo() {

        String undoName = stackUndoName.pop();
        int undoScore = stackUndoScore.pop();
        String undoCommand = stackUndoCommand.pop();

        stackRedoName.push(undoName);
        stackRedoScore.push(undoScore);
        stackRedoCommand.push(undoCommand);

        int position = 0;                               //to be used later in switch

        /*if multiple undos are required, numOfGolfers can be assigned to a variable and the switch statement can
          be looped till all the entries are removed*/

        switch (undoCommand) {                     //to check which process happened last
            case "add":                             //undoing adding of record
            case "restore":                         //undoing restoring of deleted record
                //removing the records
                playerRecords.remove(undoName);

                for (int n = 0; n < playerNames.size(); n++) {                        //n is an index of the two Array Lists, above
                    if (playerNames.get(n).equals(undoName)) {                          //getting matching position of score to name
                        position = n;
                        playerNames.remove(position);               //removing name from playerNames arrayList
                    }
                }
                playerResults.remove(position);         //removing score from playerResults arrayList

                break;

            case "edit":                            //undoing editing of record
                for (int n = 0; n < playerNames.size(); n++) {                        //n is an index of the two Array Lists, above
                    if (playerNames.get(n).equals(undoName)) {                          //getting matching position of score to name
                        position = n;
                    }
                }
                playerResults.remove(position);         //removing score from playerResults arrayList

                changedScore = playerRecords.get(undoName);         //used, if required to redo

                playerRecords.put(undoName, undoScore);
                playerResults.add(undoScore);
                break;

            case "delete":                          //undoing deleting of record
                //adding back the records
                if (!playerRecords.containsKey(undoName)) {         //making sure that records aren't duplicated
                    playerRecords.put(undoName, undoScore);
                    playerResults.add(undoScore);
                    playerNames.add(undoName);
                }
                break;

            default:
                System.out.println("Unable to undo, no change of data has happened before.");

        }


    }

    private static void redo() {

        String redoName = stackRedoName.pop();
        int redoScore = stackRedoScore.pop();
        String redoCommand = stackRedoCommand.pop();

        //adding back to the undo stack. If required to undo again, can be done
        stackUndoName.push(redoName);
        stackUndoScore.push(redoScore);
        stackUndoCommand.push(redoCommand);

        int position = 0;                               //to be used later in switch

        switch (redoCommand) {                     //to check which process happened last
            case "add":                             //undoing adding of record
            case "restore":                         //undoing restoring of deleted record
                //adding back the records
                playerRecords.put(redoName, redoScore);
                playerResults.add(redoScore);
                playerNames.add(redoName);

                break;

            case "edit":                            //changing record back to edited score

                for (int n = 0; n < playerNames.size(); n++) {                        //n is an index of the two Array Lists, above
                    if (playerNames.get(n).equals(redoName)) {                          //getting matching position of score to name
                        position = n;
                    }
                }
                playerResults.remove(position);         //removing current score from playerResults arrayList


                playerRecords.put(redoName, changedScore);
                playerResults.add(changedScore);
                break;

            case "delete":                          //undoing deleting of record
                //removing the records
                playerRecords.remove(redoName);

                for (int n = 0; n < playerNames.size(); n++) {                        //n is an index of the two Array Lists, above
                    if (playerNames.get(n).equals(redoName)) {                          //getting matching position of score to name
                        position = n;
                        playerNames.remove(position);               //removing name from playerNames arrayList
                    }
                }
                playerResults.remove(position);         //removing score from playerResults arrayList

                break;


            default:
                System.out.println("Unable to redo, undo action hasn't been used.");

        }
    }
}