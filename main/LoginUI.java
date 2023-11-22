package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import user.UserAuthenticator;
import filehandler.Database;
import filehandler.PasswordManager;

/**
 * Login interface of main program
 */
public class LoginUI {
    /**
     * System prompt for user to log in
     * 
     * @param database Database of Users
     * @return user identification number if login successful and -1 if unsuccessful
     */
    protected static int promptLogin(Database database){
        int userInput = 0;
        int userIDIndex = -1;
        Scanner sc = new Scanner(System.in);

        do{
            try{
                System.out.println("Welcome to the login screen: ");
                System.out.println("Kindly Enter your choice: 1 - Login, 2- Change Password, 3- Quit: ");
                userInput = Integer.parseInt(sc.nextLine());
            }
            catch(InputMismatchException e){
                System.out.println("Input mismatch! Kindly enter an integer value as your choice");
                sc.nextLine();
            }
            catch(NumberFormatException n){
                System.out.println("Enter a valid integer choice!");
                sc.nextLine();
            }
            switch(userInput){
                case 1:
                    userIDIndex = UserAuthenticator.verifyLogin(database);
                    if(userIDIndex >= 0){
                        return userIDIndex;
                    }
                    break;
                case 2:
                    int verifyIndex = UserAuthenticator.verifyChangePassword(database);
                    if(verifyIndex != -1){
                        PasswordManager.changePassword(verifyIndex, database);
                    }
                    break;
                case 3:
                    return -1;
                default:
                    System.out.println("Kindly enter a valid choice!");
            }
        }while(userIDIndex < 0);

        return userIDIndex;
        /*
         * Break out of loop if login successful
         */

    }
}
