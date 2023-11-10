package user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import filehandler.Database;
import filehandler.PasswordManager;


public class UserAuthenticator {

    private static int checkID(Database database, String enteredID){
        /*
         * Check if the ID entered is in database
         * Extract list of users from database
         */
        ArrayList<User> users = database.getUsers();
        for(int i = 0; i < users.size(); i++){
            if(enteredID.equals(users.get(i).getUserID())){
                return i;
            }
        }
        //if id does not exist in database, return -1
        return -1;
    }

    /*
     * Prompt user to input their login id, to be called by VerifyLogin method
     * Returns index of valid user id
     * Returns -1 if user ID is wrong and user would like to try again
     * Returns -2 if user would to quit login, this will allow them to choose to change password in loginUI if they wish to
     */
    private static int promptUserIDInput(Database database){
        /*
         * Intialize scanner and intialize variables for user input of ID and password
         */
        Scanner sc = new Scanner(System.in);
        String enteredID;
        int userChoice;
        /*
         * Console prompt user to enter details, immediately check for correctness of userID and password
         * Console will promptly react accordingly
         */
        System.out.println("Enter your user ID: ");
        enteredID = sc.nextLine();
        int returnIndex = checkID(database, enteredID);
        /*
         * If user id not found, prompt user to see if they would like to terminate program
         */
        if(returnIndex == -1){
            System.out.println("Would you like to terminate program?");
            System.out.println("Enter 0 if you would like to do so");
            System.out.println("Enter any other number if you would like to reattempt entering user ID");
            userChoice = sc.nextInt();
            /*
             * Prompts verifyLogin to terminate login attempt
             */
            if(userChoice == 0){
                sc.close();
                return -2;
            }
        }
        sc.close();
        return returnIndex;
    }


    /*
     * Return value meaning:
     * 0 == incorrect password
     * 1 == prompt userID method
     * 2 == Password is correct
     */
    private static int promptUserPasswordInput(int userIDIndex, Database database){
        String password;
        boolean isPasswordCorrect = false;
        int userChoice;
        Scanner sc = new Scanner(System.in);
        /*
         * Accept user input of password
         */
        System.out.println("Enter your password: ");
        password = sc.nextLine();
        isPasswordCorrect = PasswordManager.checkPassword(userIDIndex, password, database);
        if(!isPasswordCorrect){
            /*
             * Provide user with option to go back to entering user ID if password entered is wrong, to be done in verifyLogin
             */
            System.out.println("Would you like to re-enter user ID?");
            System.out.println("Enter 1 if you would like to re-enter user ID");
            System.out.println("Enter any other number to re-attempt entering password");
            userChoice = sc.nextInt();
            if(userChoice != 1){
                sc.close();
                return 0;
            }
            System.out.println("Kindly re-attempt entering of password.");
            sc.close();
            return 1;
        }
        sc.close();
        return 2;
    }

    public static boolean verifyLogin(Database database){
        /*
         * Main method to call in main program to verify login
         */
        int userIDIndex;

        do{
            /*
             * userID verification and retrieve index of userID if valid
             */
            userIDIndex = promptUserIDInput(database);
            if(userIDIndex == -2){
                /*
                 * Prompt loginUI class to exit login call
                 */
                return false;
            }
        }while(userIDIndex == -1);
        /*
         * Password verification for user ID
         * int variable outcome will prompt the system to execute the decision of promptUserPasswordInput as stated earlier
         */
        int outcome;
        do{
            outcome = promptUserPasswordInput(userIDIndex, database);
            if(outcome == 1){
                /*
                 * prompt user id input again due to choice made by user
                 */
                userIDIndex = promptUserIDInput(database);
                if(userIDIndex == -2){
                /*
                 * Prompt loginUI class to exit login call
                 */
                return false;
            }
            }
        }while(outcome != 2);
        return true;
    }

    /*
     * Return -1 if verification fails
     * Otherwise return index of user in database
     */
    public static int verifyChangePassword(Database database){
        /*
         * Main method to call in main program to verify login
         */
        
        /*
         * Intialize scanner and intialize variables for user input of ID and password
         */
        Scanner sc = new Scanner(System.in);
        String enteredID;
        int userIDIndex = -1;
        /*
         * Console prompt user to enter details, immediately check for correctness of userID and password
         * Console will promptly react accordingly
         */
        do{
            /*
             * userID verification
             */
            System.out.println("Enter your user ID that you want to change password for: ");
            enteredID = sc.nextLine();
            userIDIndex = checkID(database, enteredID);
            /*
             * Inform user if userID does not exist and ask them if they still want to continue changing password
             */
            if(userIDIndex == -1){
                System.out.println("User not found in database, would you still like to continue: ");
                System.out.println("Enter 1 if you would like to continue attempting changing your password.");
                System.out.println("Enter any other number if you would like to quit changing your password.");
                /*
                 * Change the value 1 to -1 so that user can continue their attempt to enter userID to change password
                 */
                while(userIDIndex == -1){
                    try{
                    userIDIndex = sc.nextInt()*(-1);
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input! Enter an integer value as input!");
                    /*
                     * To prevent infinite loop, sc.nextLine() is called
                     */
                    sc.nextLine();
                }
                }
            }
            else{
                sc.close();
                return userIDIndex;
            }
        }while(userIDIndex == -1);

        sc.close();
        return -1;
    }
}
