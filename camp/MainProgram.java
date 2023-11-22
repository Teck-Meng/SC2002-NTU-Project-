
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
/**
 * The main program 
 */
public class MainProgram {
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        Time time = new Time(10_29_2023);

        Database database = new Database();
        CampInfo campInfo = new CampInfo();
        ListOfSuggestions suggestions = new ListOfSuggestions();
        ListOfEnquiries enquiries = new ListOfEnquiries();
        ReplyToStudent replies = new ReplyToStudent();
        
        readFileInfo(campInfo, database, enquiries, suggestions, replies);

        int userIDIndex;
        User currentUser;
        
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Welcome to the Camp Application and Management System!");
            userIDIndex = LoginUI.promptLogin(database);
            if(userIDIndex < 0){
                System.out.println("Terminating program. . .");
                sc.close();
                break;
            }
            currentUser = database.getUser(userIDIndex);
            if(currentUser instanceof Staff){
                StaffUI.main(campInfo, database, enquiries, suggestions, replies, time, (Staff)currentUser);
            }
            else{
                StudentUI.main((Student)currentUser, campInfo, database, time, enquiries, suggestions, replies);
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
        saveFileInfo(campInfo, database, enquiries, suggestions, replies);

        sc.close();
    }

    /**
     * Method to read data from csv files into corresponding databases
     * 
     * @param campInfo Database of Camps
     * @param database Database of Users
     * @param enquiries Database of Enquiries
     * @param suggestions Database of Suggestions
     * @param replies Database of Replies to Enquiries
     */
    public static void readFileInfo(CampInfo campInfo, Database database, ListOfEnquiries enquiries, ListOfSuggestions suggestions,
                                    ReplyToStudent replies){
        ReadFromFile.readUserList(database); 
        ReadFromFile.readPasswords(database);
        ReadFromFile.readListOfCamps(campInfo, database); 
        ReadFromFile.readAttendeeList(campInfo, database);
        ReadFromFile.readCommitteeList(campInfo, database);
        ReadFromFile.readBlacklist(campInfo, database);
        ReadFromFile.readEnquiries(campInfo, database, enquiries, replies);
        ReadFromFile.readSuggestions(campInfo, database, suggestions);   
    }

    /**
     * Methods to write data from databases into csv files
     * 
     * @param campInfo Database of Camps
     * @param database Database of Users
     * @param enquiries Database of Enquiries
     * @param suggestions Database of Suggestions
     * @param replies Database of Replies
     */
    public static void saveFileInfo(CampInfo campInfo, Database database, ListOfEnquiries enquiries, ListOfSuggestions suggestions,
                                    ReplyToStudent replies){
        ClearFiles.clearAttendanceLists();
        ClearFiles.clearPasswords();
        ClearFiles.clearCampInfoAttributes();
        ClearFiles.clearCampInfo();
        ClearFiles.clearEnquiries();
        ClearFiles.clearSuggestions();

        WriteToFile.writeToBlacklist(campInfo);
        WriteToFile.writeToAttendeeList(campInfo);
        WriteToFile.writeToCommitteeList(campInfo);
        WriteToFile.writeToPasswords(database);
        WriteToFile.writeCampInfo(campInfo);
        WriteToFile.writeToEnquiries(enquiries, replies);
        WriteToFile.writeToSuggestion(suggestions);
    }
}
