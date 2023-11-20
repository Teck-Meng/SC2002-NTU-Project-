import java.util.InputMismatchException;
import java.util.Scanner;

import camppackage.CampInfo;
import clock.Time;
import enquiry.ListOfEnquiries;
import enquiry.ListOfSuggestions;
import enquiry.ReplyToStudent;
import filehandler.*;
import user.User;
import user.Staff;
import user.Student;
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
        int userIDIndex;
        /*
         * currentUser will store the object of the current user after they have successfully logged in
         */
        User currentUser;
        
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Welcome to the Camp Application and Management System!");
            /*
            * Login verification by calling loginUI class
            */
            userIDIndex = loginUI.promptLogin(database);
            if(userIDIndex < 0){
                /*
                 * Terminate program if login inside loginUI has failed
                 */
                System.out.println("Terminating program. . .");
                sc.close();
                break;
            }
            /*
            * Else grab user object from database and prepare interfaces
            */
            currentUser = database.getUser(userIDIndex);
            /*
            * To have another method call to 2 other classes called staffUI and studentUI
            */
            if(currentUser instanceof Staff){
                /*
                * To call Staff UI
                */
                StaffUI.main(campInfo, database, enquiries, suggestions, replies, time, (Staff)currentUser);
            }
            else{
                /*
                * Call student UI
                */
                StudentUI.Main((Student)currentUser, campInfo, database, time, enquiries, suggestions, replies);
            }

            System.out.println("Would you like to terminate program?");
            System.out.println("Enter 1 if you would like to do so");
            int userChoice = -1;
            try{
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
            }
            if(userChoice == 1){
                System.out.println("Terminating program. . .");
                break;
            }
        }
         
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
