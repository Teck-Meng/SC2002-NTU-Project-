import java.util.Scanner;

import camppackage.AttendeeRegister;
import enquiry.AttendeeEnquiry;
import camppackage.Camp;
import camppackage.CampInfo;
import camppackage.PrintCampDetails;
import enquiry.ListOfEnquiries;
import enquiry.ReplyToStudent;
import filehandler.Database;
import user.Student;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class AttendeeUI {
    
    public static void DisplayUI(Camp camp, CampInfo campInfo, Student attendee, Database database, ListOfEnquiries enquiries,
                                ReplyToStudent replies){
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Student Attendee Menu ---");
            System.out.println("1: View camp details"); //
            System.out.println("2: Withdraw from camp"); //
            System.out.println("3. View all submitted enquiries");
            System.out.println("4. Submit an enquiry");
            System.out.println("5. Edit an enquiry");
            System.out.println("6. Delete an enquiry");
            System.out.println("7. View all replies to your enquiries");
            System.out.println("8. Exit");
            System.out.println("Please enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //print camp descriptions
                    PrintCampDetails.print(camp);
                    System.out.println("--------------------------------------------");
                    break;

                case 2:
                    /*
                    * Peform double check to see if student wishes to withdraw
                    */
                    exit = CampManagementUI.withdrawCamp(camp, campInfo, attendee, database);
                    break;

                case 3:
                    // assume we are displaying all enquiries of this user
                    AttendeeEnquiry.viewQuestion(attendee.getUserID(), enquiries, database);  
                    break;

                case 4:
                    // submit new enquiries regarding this specific camp
                    AttendeeEnquiry.askQuestion(attendee.getUserID(), camp, enquiries, database);
                    break;
                    
                case 5:
                    EnquiriesUI.editEnquiry(enquiries, attendee, camp);
                    break;

                case 6:
                    // delete a chosen enquiry by the user
                    EnquiriesUI.deleteEnquiry(enquiries, attendee, database);
                    break;
                case 7:
                    /*
                     * Print out replies together with its corresponding enquiry
                     */
                    EnquiriesUI.printReplies(enquiries, attendee, replies);
                    break;
                case 8:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice");
                }

                }

    }
    
    

    
}

