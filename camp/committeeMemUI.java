
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

/**
 * Class representing the committee member interface in the main program
 */
public class CommitteeMemUI {
    /**
     * Method to call committee member interface in main program
     * 
     * @param camp Camp that the commitee member registered for
     * @param userID User identification of camp committee member
     * @param database Database of Users
     * @param enquiries List of enquiries
     * @param suggestions List of suggestions
     * @param replyToStudent List of replies
     * @param campInfo Database of Camps
     * @param committeeMem Student object representing committee member
     */
    protected static void main(Camp camp, String userID, Database database, ListOfEnquiries enquiries, 
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
            switch (choice) {
                case 1:
                    PrintCampDetails.print(camp);
                    break;
                case 2:
                    SuggestionsUI.modifySuggestion(camp, userID, database, suggestions);
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
                    break;
                case 6:
                    EnquiriesUI.printReplies(enquiries, camp, replyToStudent);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                }

                }

                } 
                    
    

        
}
