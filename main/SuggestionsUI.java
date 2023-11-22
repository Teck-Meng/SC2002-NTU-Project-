package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import camppackage.Camp;
import camppackage.CampInfo;
import clock.Time;
import enquiry.ListOfSuggestions;
import enquiry.ReplyToSuggestion;
import filehandler.Database;
import user.Staff;

/**
 * Class responsible for managing suggestion on the console level
 */
public class SuggestionsUI {
    /**
     * Suggestion handling interface for camp committee member
     * 
     * @param camp Camp committee member's camp
     * @param userID User identification of camp committee member
     * @param database Database of users
     * @param suggestions List of suggestions
     */
    protected static void modifySuggestion(Camp camp, String userID, Database database, ListOfSuggestions suggestions){
        Scanner sc = new Scanner(System.in);
        boolean manage = true;
        while(manage){
            System.out.println("--- Suggestion Handling interface ---");
            System.out.println("1. Submit Suggestion");
            System.out.println("2. View Suggestions");
            System.out.println("3. Edit Suggestion");
            System.out.println("4. Delete Suggestion");
            System.out.println("5. Go back to main menu");
            System.out.println("Enter your choice: ");
            int suggestionChoice = sc.nextInt();
            sc.nextLine(); 
            switch(suggestionChoice){
                case 1:
                    String suggestion;
                    System.out.println("Enter your suggestion for this camp: ");
                    suggestion = sc.nextLine();
                    suggestions.addSuggestion(suggestion, userID, database, camp);
                    break;
                case 2:
                    suggestions.printAllSuggestions(camp, userID, false);
                    break;
                case 3:
                    int suggestionID = -1;
                    while(suggestionID == -1){
                        try{
                            suggestions.printAllSuggestions(camp, userID, false);

                            System.out.println("Enter the suggestion ID of the suggestion you want to edit: ");
                            suggestionID = sc.nextInt();
                        }
                        catch(InputMismatchException e){
                            sc.nextLine();
                            System.out.println("Please enter a valid integer choice!");
                        }
                    }
                    sc.nextLine();
                    System.out.println("Enter your new suggestion: ");
                    String newSuggestion = sc.nextLine();
                    suggestions.editSuggestion(suggestions.getIndexFromID(suggestionID), userID, newSuggestion);
                    break;
                case 4:
                    int suggestionID2 = -1;
                    while(suggestionID2 == -1){
                        try{
                            suggestions.printAllSuggestions(camp, userID, false);
                            System.out.println("Enter the suggestion ID of the suggestion you want to delete: ");
                            suggestionID2 = sc.nextInt();
                        }
                        catch(InputMismatchException e){
                            sc.nextLine();
                            System.out.println("Please enter a valid integer choice!");
                        }
                    }
                    sc.nextLine();
                    suggestions.deleteSuggestion(suggestions.getIndexFromID(suggestionID2), userID);
                    break;
                case 5:
                    manage = false;                           
                    break;
                default:
                    System.out.println("Invalid choice");
                }                   
            }
    }

    /**
     * Suggestion handling interface for staff
     * 
     * @param suggestions List of suggestions
     * @param userID User identification of staff
     * @param database Database of users
     * @param campInfo Database of camps
     * @param time Current date
     */
    protected static void staffSuggestions(ListOfSuggestions suggestions, String userID, Database database, CampInfo campInfo, Time time)
    {
    Scanner sc = new Scanner(System.in);
    boolean exitSuggestions = false;
    while(!exitSuggestions){
        System.out.println("--- Suggestions handling interface ---");
        System.out.println("1. View all Suggestions");
        System.out.println("2. Approve Suggestions");
        System.out.println("3. Go back to main menu");
        System.out.println("Enter your choice: ");
        int suggestionChoice = sc.nextInt();
        sc.nextLine(); 
        switch(suggestionChoice){
            case 1:
                suggestions.printAllSuggestions(userID, database, false);
                break;
            case 2:
                approveSuggestions(suggestions, userID, database, campInfo, time);
                break;
            case 3:
                exitSuggestions = true; 
                break;
            default:
                System.out.println("Invalid choice");
            }                   
        }

    
    }

    /**
     * System prompt for staff to approve or reject suggestions
     * 
     * @param suggestions List of suggestions
     * @param userID User identification of staff
     * @param database Database of users
     * @param campInfo Database of camps
     * @param time Current date
     */
    private static void approveSuggestions(ListOfSuggestions suggestions, String userID, Database database, CampInfo campInfo, Time time){
    Scanner sc = new Scanner(System.in);
    Staff staff = (Staff)database.getUser(userID);
    /*
        * Printing
        */
    boolean proceed = suggestions.printAllSuggestions(userID, database, true);
    if(!proceed){
        return;
    }

    System.out.println("Enter the suggestion ID of the camp you want to approve: ");
    int suggestionID = sc.nextInt();
    int index = suggestions.getIndexFromID(suggestionID);

    String campName = suggestions.getCampEnquiredID(index);
    Camp camp = campInfo.getCamp(campName);

    ReplyToSuggestion.replyToSuggestion(index, suggestions, staff, camp, campInfo, database, time);
}
}
