package enquiry;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReplyToSuggestion{
    /*
     * StaffUI class will be responsible to prompt which suggestion they want to reply to
     * @param index is the index of the enquiry
     * StaffUI class should prompt Staff will another function call to make the necessary edits to be made from approving suggestion
     */
    public void replyToSuggestion(int index, listOfSuggestions list){
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
            }
        }
        sc.close();
        switch(userChoice){
            case 1:
                list.staffAction(index, true);
                System.out.println("Suggestion has been approved! Kindly make the necessary edits as soon as possible!");
                break;
            case 2:
                list.staffAction(index, false);
                System.out.println("Suggestion has been rejected!");
                break;
        } 
    }
}