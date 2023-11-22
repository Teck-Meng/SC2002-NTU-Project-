package user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import filehandler.Database;
import filehandler.PasswordManager;

/**
 * Class responsible for authenticating login
 */
public class UserAuthenticator {

    /**
     * Method to check if a userID that is entered is inside the database of users, returns -1 if user not found.
     * @param database Database of users
     * @param enteredID User ID entered in by user for login
     * @return Index of user within the database of users, -1 if user not found
     */
    private static int checkID(Database database, String enteredID){
        ArrayList<User> users = database.getUsers();
        for(int i = 0; i < users.size(); i++){
            if(enteredID.equals(users.get(i).getUserID())){
                return i;
            }
        }
        return -1;
    }

    
    /**
     * <p>
     * Prompt user to input their login id, to be called by VerifyLogin method
     * </p>
     * <p>
     * Returns -1 if user ID is wrong and user would like to try again,
     * Returns -2 if user would to quit login, this will allow them to choose to change password in loginUI if they wish to
     * </p>
     * @param database Database of Users
     * @return Index of user within the database or instructions for system in response to user ID input
     */
    private static int promptUserIDInput(Database database){
        Scanner sc = new Scanner(System.in);
        String enteredID;
        int userChoice;

        System.out.println("Enter your user ID: ");
        enteredID = sc.nextLine();
        int returnIndex = checkID(database, enteredID);

        if(returnIndex == -1){
            try{
                System.out.println("Would you like to terminate program?");
                System.out.println("Enter 0 if you would like to do so");
                System.out.println("Enter any other number if you would like to reattempt entering user ID");
                userChoice = Integer.parseInt(sc.nextLine());

                if(userChoice == 0){
                    return -2;
                }
            }
            catch(InputMismatchException e){
                return -2;
            }
            catch(NumberFormatException n){
                return -2;
            }
        }
        return returnIndex;
    }


    
    /**
     * <p>
     * Prompts user to enter their password
     * </p>
     * <p>
     * Return value meaning:
     * 0 == incorrect password,
     * 1 == prompt userID method,
     * 2 == Password is correct
     * </p>
     * @param userIDIndex
     * @param database
     * @return
     */
    private static int promptUserPasswordInput(int userIDIndex, Database database){
        String password;
        boolean isPasswordCorrect = false;
        int userChoice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your password: ");
        password = sc.nextLine();
        isPasswordCorrect = PasswordManager.checkPassword(userIDIndex, password, database);
        if(!isPasswordCorrect){

            System.out.println("Would you like to re-enter user ID?");
            System.out.println("Enter 1 if you would like to re-enter user ID");
            System.out.println("Enter any other number to re-attempt entering password");
            userChoice = Integer.parseInt(sc.nextLine());
            if(userChoice != 1){
                return promptUserPasswordInput(userIDIndex, database);
            }
            System.out.println("Kindly re-attempt entering of password.");
            return 1;
        }
        if(password.matches("password")){
            int verifyIndex = verifyChangePassword(database);
            // if userID is correct, prompt password change using the index
            if(verifyIndex != -1){
                PasswordManager.changePassword(verifyIndex, database);
                if(verifyLogin(database) < 0){
                    return -2;
                }
            }
            else{
                return -2;
            }
        }
        return 2;
    }

    /**
     * <p>
     * System prompt to login
     * </p>
     * <p>
     * Return value meaning:
     * -1 == login failed,
     * Else, return index of login
     * </p>
     * @param database Database of users
     * @return Index of user within database
     */
    public static int verifyLogin(Database database){
        int userIDIndex;

        do{
            userIDIndex = promptUserIDInput(database);
            
        }while(userIDIndex == -1);

        if(userIDIndex == -2){
                return -1;
            }
        int outcome;
        do{
            outcome = promptUserPasswordInput(userIDIndex, database);
            if(outcome == 1){
                userIDIndex = promptUserIDInput(database);
                if(userIDIndex == -2){
                return -1;
            }
            }
            else if(outcome == -2){
                return -1;
            }
        }while(outcome != 2);
        return userIDIndex;
    }

    
    /**
     * <p>
     * System prompt to change password
     * </p>
     * <p>
     * Return value meaning:
     * -1 == verification to change password has failed.
     * Otherwise, return index of user in database
     * </p>
     * @param database Database of Users
     * @return Index of user in database
     */
    public static int verifyChangePassword(Database database){
        Scanner sc = new Scanner(System.in);
        String enteredID;
        int userIDIndex = -1;
        do{
            System.out.println("Enter your user ID that you want to change password for: ");
            enteredID = sc.nextLine();
            userIDIndex = checkID(database, enteredID);
            if(userIDIndex == -1){
                System.out.println("User not found in database, would you still like to continue: ");
                System.out.println("Enter 1 if you would like to continue attempting changing your password.");
                System.out.println("Enter any other number if you would like to quit changing your password.");
                while(userIDIndex == -1){
                    try{
                        userIDIndex = sc.nextInt();
                        sc.nextLine();
                    }
                    catch(InputMismatchException e){
                        System.out.println("Invalid input! Enter an integer value as input!");
                        sc.nextLine();
                    }
                }
                if(userIDIndex == 1){
                    return verifyChangePassword(database);
                }
                else{
                    return -1;
                }
            }
            else{
                return userIDIndex;
            }
        }while(userIDIndex == -1);

    }
}
