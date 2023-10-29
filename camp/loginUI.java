import java.util.Scanner;
import user.UserAuthenticator;
import password.Database;
import password.PasswordManager;
import clock.Time;


public class loginUI {
    public static boolean promptLogin(Database database){
        /*
         * Set the current time of the program
         */
        Time time = new Time(10_29_2023);
        /*
         * Intialize integer variable to track user choice in the app
         */
        int userInput;
        boolean canLogin = false;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("Welcome to the login screen: ");
            System.out.println("Kindly Enter your choice: 1 - Login, 2- Change Password, 3- Quit: ");
            userInput = sc.nextInt();
            switch(userInput){
                case 1:
                    /*
                     * Check for userID and password using UserAuthenticator class
                     */
                    canLogin = UserAuthenticator.verifyLogin(database);
                    /*
                     * Prompt app to allow users to utilize app features
                     */
                    if(canLogin == true){
                        return true;
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
                    sc.close();
                    return false;
                default:
                    System.out.println("Kindly enter a valid choice!");
            }
        }while(canLogin == false);

        sc.close();
        return true;
        /*
         * Break out of loop if login successful
         */

    }
}
