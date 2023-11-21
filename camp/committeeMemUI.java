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
            // END: ed8c6549bwf9
            switch (choice) {
                case 1:
                    /*
                    * Print all the camp details
                    */
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
                    
    

        
}
