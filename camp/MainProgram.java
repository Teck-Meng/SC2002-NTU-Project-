import java.util.Scanner;
import password.Database;
/*
 * Main program will call methods to operate the features of the app
 */
public class MainProgram {
    public static void main(String[] args) {
        /*
         * Currently missing addition of users from csv file, to be done later
         */
        Database database = new Database();

        /*
         * Intialize integer variable to track user choice in the app
         */
        int userInput;
        boolean canLogin;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Camp Application and Management System!");
        /*
         * Login verification by calling loginUI class
         */
        canLogin = loginUI.promptLogin(database);
        if(!canLogin){
            System.out.println("Terminating program. . .");
            sc.close();
            return;
        }
        /*
         * To have another method call to 2 other classes called staffUI and studentUI
         */


         sc.close();
    }
}
