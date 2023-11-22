package enquiry;

import java.util.Scanner;

import camppackage.Camp;
import user.Student;
import filehandler.Database;

/**
 * Class responsible for handling committee member suggestions
 */
public class CommitteeSuggestion {
    /**
     * System prompt to allow committee member to submit a suggestion
     * 
     * @param userID User identification
     * @param camp Committee member's camp
     * @param list List of suggestions
     * @param database Database of Users
     */
    public static void giveSuggestion(String userID, Camp camp, ListOfSuggestions list, Database database){
        Scanner sc = new Scanner(System.in);
        String suggestion;

        System.out.println("Kindly enter your suggestion for "+camp.getCampName());
        suggestion = sc.nextLine();
        
        list.addSuggestion(suggestion, userID, database, camp);
    }

    /**
     * Method to print all suggestions submitted by Committee Member
     * 
     * @param userID User identification
     * @param list List of suggestions
     * @param database Database of Users
     */
    public static void viewSuggestion(String userID, ListOfSuggestions list, Database database){
        Student committeeMember = ((Student)database.getUser(userID));
        list.printAllSuggestions(committeeMember.getCommitteeCamp(), committeeMember.getUserID(), false);
    }

}
