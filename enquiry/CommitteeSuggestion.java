package enquiry;

import java.util.Scanner;

import camppackage.Camp;
import user.Student;
import filehandler.Database;

public class CommitteeSuggestion {
    public static void giveSuggestion(String userID, Camp camp, ListOfSuggestions list, Database database){
        Scanner sc = new Scanner(System.in);
        String suggestion;

        System.out.println("Kindly enter your suggestion for "+camp.getCampName());
        suggestion = sc.nextLine();
        
        list.addSuggestion(suggestion, userID, database, camp);
        sc.close();
    }

    public static void viewSuggestion(String userID, ListOfSuggestions list, Database database){
        Student committeeMember = ((Student)database.getUser(userID));
        list.printAllSuggestions(committeeMember.getCommitteeCamp(), committeeMember.getUserID(), false);
    }

    public static void deleteSuggestion(){
        
    }
}
