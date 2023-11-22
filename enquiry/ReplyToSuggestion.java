package enquiry;

import java.util.InputMismatchException;
import java.util.Scanner;

import camppackage.Camp;
import camppackage.CampInfo;
import user.Staff;
import filehandler.Database;
import clock.Time;

/**
 * Class handling replies to committee suggestion
 */
public class ReplyToSuggestion{
    /**
     * StaffUI class will be responsible to prompt which suggestion they want to reply to
     * index is the index of the suggestion
     * StaffUI class should prompt Staff will another function call to make the necessary edits to be made from approving suggestion
     * 
     * @param index Index positioning of Suggestion in list of suggestions
     * @param list List of suggestions
     * @param staff Staff trying to reply to suggestion
     * @param camp Camp that received suggestion
     * @param campInfo Database of Camps
     * @param database Database of Users
     * @param time Current date
     */
    public static void replyToSuggestion(int index, ListOfSuggestions list, Staff staff, Camp camp, CampInfo campInfo,
                                        Database database, Time time){
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;

        while(userChoice < 1 || userChoice > 2){
            try{
                System.out.println("Would you like to approve this suggestion?: ");
                System.out.println("Enter 1 if you want to approve");
                System.out.println("Enter 2 if you want to reject");
                userChoice = sc.nextInt();
                if(userChoice < 1 || userChoice > 2){
                    System.out.println("Kindly enter a choice between 1 and 2!");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid choice! Kindly enter an integer value as choice!");
                sc.nextLine();
            }
        }
        
        switch(userChoice){
            case 1:
                list.staffAction(index, true);
                System.out.println("Suggestion has been approved! Kindly make the necessary edits as soon as possible!");
                staff.editCamp(camp, campInfo, database, time);
                break;
            case 2:
                list.staffAction(index, false);
                System.out.println("Suggestion has been rejected!");
                break;
        } 
    }
}