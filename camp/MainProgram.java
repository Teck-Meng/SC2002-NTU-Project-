import java.util.Scanner;

import camppackage.CampInfo;
import clock.Time;
import filehandler.*;
import user.User;
/*
 * Main program will call methods to operate the features of the app
 */
public class MainProgram {
    public static void main(String[] args) {
        /*
         * Set the current time of the program
         */
        Time time = new Time(10_29_2023);
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
        int userIDIndex;
        boolean canLogin;
        /*
         * currentUser will store the object of the current user after they have successfully logged in
         */
        User currentUser;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Camp Application and Management System!");
        /*
         * Login verification by calling loginUI class
         */
        userIDIndex = loginUI.promptLogin(database);
        if(userIDIndex < 0){
            System.out.println("Terminating program. . .");
            sc.close();
            return;
        }
        /*
         * Else grab user object from database and prepare interfaces
         */
        currentUser = database.getUser(userIDIndex);
        /*
         * To have another method call to 2 other classes called staffUI and studentUI
         */

         
         /*
          * To be called upon program termination, write updated information into csv files
          */
        saveFileInfo(campInfo, database);

        sc.close();
    }


    public static void saveFileInfo(CampInfo campInfo, Database database){
        clearFiles.clearAttendanceLists();
        clearFiles.clearPasswords();
        clearFiles.clearCampInfoAttributes();
        clearFiles.clearCampInfo();

        writeToFile.writeToBlacklist(campInfo);
        writeToFile.writeToAttendeeList(campInfo);
        writeToFile.writeToCommitteeList(campInfo);
        writeToFile.writeToPasswords(database);
        writeToFile.writeCampInfo(campInfo);
    }
}
