import java.util.InputMismatchException;
import java.util.Scanner;

import user.*;
import report.AttendanceReport;
import filehandler.Database;
import camppackage.Camp;
import camppackage.CampInfo;
import camppackage.PrintCampDetails;
import enquiry.ListOfEnquiries;
import enquiry.ListOfSuggestions;
import enquiry.ReplyToStudent;

public class CommitteeMemUI {

    public static void main(Camp camp, String userID, Database database, ListOfEnquiries enquiries, 
                            ListOfSuggestions suggestions, ReplyToStudent replyToStudent, CampInfo campInfo, Student committeeMem) {
        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--- Camp Committee Member Menu ---");
            System.out.println("1. View Camp Details");
            System.out.println("2. Modify Suggestions for Camp");
            System.out.println("3. Reply to Enquiries");
            System.out.println("4. Generate Report");
            System.out.println("5. View all Enquiries");
            System.out.println("6. View all Replies");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            // END: ed8c6549bwf9
            switch (choice) {
                case 1:
                    /*
                    * Print all the camp details
                    */
                    PrintCampDetails.print(camp);
                    break;
                case 2:
                    modifySuggestion(camp, userID, database, suggestions);
                    break;
                case 3:
                    EnquiriesUI.replyToEnquiries(enquiries, camp, userID, replyToStudent, database);
                    break;
                case 4:
                    AttendanceReport.attendeeReportHandling(camp.getAttendeeList().getListOfAttendees(), camp);
                    System.out.println("Report generated successfully!");
                    break;
                case 5:
                    enquiries.printAllEnquiries(camp, committeeMem, true);
                    /*
                        * View all enquiries
                        */
                    break;
                case 6:
                    EnquiriesUI.printReplies(enquiries, camp, replyToStudent);
                    /*
                        * View all replies
                        */
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                }

                }

                } 
                    
        /*
         * Call this method when committee member wishes to modify suggestion 
         */
        private static void modifySuggestion(Camp camp, String userID, Database database, ListOfSuggestions suggestions){
            Scanner sc = new Scanner(System.in);
            boolean manage = true;
            while(manage){
                System.out.println("1. Submit Suggestion");
                System.out.println("2. View Suggestions");
                System.out.println("3. Edit Suggestion");
                System.out.println("4. Delete Suggestion");
                System.out.println("5. Go back to main menu");
                System.out.println("Enter your choice: ");
                int suggestionChoice = sc.nextInt();
                sc.nextLine(); // Consume the newline character left by previous nextInt()
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
                                /*
                                 * Show the user the suggestions they can edit
                                 */
                                suggestions.printAllSuggestions(camp, userID, false);

                                System.out.println("Enter the suggestion ID of the suggestion you want to edit: ");
                                suggestionID = sc.nextInt();
                            }
                            catch(InputMismatchException e){
                                sc.nextLine();
                                System.out.println("Please enter a valid integer choice!");
                            }
                        }
                        sc.nextLine(); // Consume the newline character left by previous nextInt()
                        System.out.println("Enter your new suggestion: ");
                        String newSuggestion = sc.nextLine();
                        suggestions.editSuggestion(suggestions.getIndexFromID(suggestionID), userID, newSuggestion);
                        break;
                    case 4:
                        int suggestionID2 = -1;
                        while(suggestionID2 == -1){
                            try{
                                /*
                                 * Show the user the suggestions they can delete
                                 */
                                suggestions.printAllSuggestions(camp, userID, false);
                                System.out.println("Enter the suggestion ID of the suggestion you want to delete: ");
                                suggestionID2 = sc.nextInt();
                            }
                            catch(InputMismatchException e){
                                sc.nextLine();
                                System.out.println("Please enter a valid integer choice!");
                            }
                        }
                        sc.nextLine(); // Consume the newline character left by previous nextInt()
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

        
}
