import java.util.Scanner;

import camppackage.CampInfo;
import clock.Time;
import enquiry.ListOfEnquiries;
import enquiry.ListOfSuggestions;
import enquiry.ReplyToStudent;
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
        Database database = new Database();
        CampInfo campInfo = new CampInfo();
        ListOfSuggestions suggestions = new ListOfSuggestions();
        ListOfEnquiries enquiries = new ListOfEnquiries();
        ReplyToStudent replies = new ReplyToStudent();

        readFileInfo(campInfo, database, enquiries, suggestions, replies);
        
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
        saveFileInfo(campInfo, database, enquiries, suggestions, replies);

        sc.close();
    }

    public static void readFileInfo(CampInfo campInfo, Database database, ListOfEnquiries enquiries, ListOfSuggestions suggestions,
                                    ReplyToStudent replies){
        readFromFile.readUserList(database); 
        readFromFile.readPasswords(database);
        readFromFile.readListOfCamps(campInfo, database); 
        readFromFile.readAttendeeList(campInfo, database);
        readFromFile.readCommitteeList(campInfo, database);
        readFromFile.readBlacklist(campInfo, database);
        readFromFile.readEnquiries(campInfo, database, enquiries, replies);
        readFromFile.readSuggestions(campInfo, database, suggestions);   
    }

    public static void saveFileInfo(CampInfo campInfo, Database database, ListOfEnquiries enquiries, ListOfSuggestions suggestions,
                                    ReplyToStudent replies){
        clearFiles.clearAttendanceLists();
        clearFiles.clearPasswords();
        clearFiles.clearCampInfoAttributes();
        clearFiles.clearCampInfo();
        clearFiles.clearEnquiries();
        clearFiles.clearSuggestions();

        writeToFile.writeToBlacklist(campInfo);
        writeToFile.writeToAttendeeList(campInfo);
        writeToFile.writeToCommitteeList(campInfo);
        writeToFile.writeToPasswords(database);
        writeToFile.writeCampInfo(campInfo);
        writeToFile.writeToEnquiries(enquiries, replies);
        writeToFile.writeToSuggestion(suggestions);
    }
}
