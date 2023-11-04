import java.util.Scanner;

import camppackage.CampInfo;
import password.Database;
import filehandler.readFromFile;
/*
 * Main program will call methods to operate the features of the app
 */
public class MainProgram {
    public static void main(String[] args) {
        /*
         * Initialize campInfo and database data structures from csv files
         */
        Database database = readFromFile.readUserList();
        database = readFromFile.readPasswords(database);
        CampInfo campInfo = readFromFile.readListOfCamps(database);
        readFromFile.readAttendeeList(campInfo, database);
        readFromFile.readCommitteeList(campInfo, database);
        readFromFile.readBlacklist(campInfo, database);
        
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
