import java.util.InputMismatchException;
import java.util.Scanner;
import user.UserAuthenticator;
import filehandler.Database;
import filehandler.PasswordManager;


public class LoginUI {
    protected static int promptLogin(Database database){
        
        /*
         * Intialize integer variable to track user choice in the app
         */
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
                /*
                 * Use of sc.nextLine() to prevent infinite looping
                 */
                sc.nextLine();
            }
            catch(NumberFormatException n){
                System.out.println("Enter a valid integer choice!");
                sc.nextLine();
            }
            switch(userInput){
                case 1:
                    /*
                     * Check for userID and password using UserAuthenticator class
                     */
                    userIDIndex = UserAuthenticator.verifyLogin(database);
                    /*
                     * Prompt app to allow users to utilize app features
                     */
                    if(userIDIndex >= 0){
                        return userIDIndex;
                    }
                    break;
                case 2:
                    /*
                     * 
                     */
                    int verifyIndex = UserAuthenticator.verifyChangePassword(database);
                    // if userID is correct, prompt password change using the index
                    if(verifyIndex != -1){
                        PasswordManager.changePassword(verifyIndex, database);
                    }
                    break;
                case 3:
                    /*
                     * Prompt application to close completely
                     */
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
