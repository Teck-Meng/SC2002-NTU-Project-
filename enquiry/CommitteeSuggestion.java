package enquiry;

import java.util.Scanner;

import camppackage.Camp;
import user.Student;
import filehandler.Database;

public class CommitteeSuggestion {
    public void giveSuggestion(String userID, Camp camp, listOfSuggestions list, Database database){
        Scanner sc = new Scanner(System.in);
        String suggestion;

        System.out.println("Kindly enter your suggestion for "+camp.getCampName());
        suggestion = sc.nextLine();
        
        list.addSuggestion(suggestion, userID, database, camp);
        sc.close();
    }

    public void viewSuggestion(String userID, listOfSuggestions list, Database database){
        Student committeeMember = ((Student)database.getUser(userID));
        list.printAllSuggestions(committeeMember.getCommitteCamp(), committeeMember.getUserID(), false);
    }

    public void deleteSuggestion(){
        
    }
}
