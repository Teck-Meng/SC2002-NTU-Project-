package enquiry;

import camppackage.Camp;
import filehandler.Database;

import java.util.Scanner;

/**
 * Class responsible for handling camp participants enquiries
 * 
 */
public class AttendeeEnquiry {
    /**
     * System prompt for Student to send an enquiry to a specific camp
     * 
     * @param userID User identification
     * @param camp Camp that the student wants to send an enquiry to
     * @param list List of Enquiries
     * @param database Database of Users
     */
    public static void askQuestion(String userID, Camp camp, ListOfEnquiries list, Database database){
        Scanner sc = new Scanner(System.in);
        String enquiry;

        System.out.println("Kindly enter your enquiry for "+camp.getCampName());
        enquiry = sc.nextLine();
        
        list.addEnquiry(enquiry, userID, database, camp);
        System.out.println("Enquiry successfully added!");
    }

    /**
     * For student to view all their enquiries that they have sent
     * 
     * @param userID User identification
     * @param list List of enquiries
     * @param database Database of Users
     */
    public static void viewQuestion(String userID, ListOfEnquiries list, Database database){
        int size = list.getSize();
        int count = 0;
        System.out.println("The following are the enquiries you have submitted: ");
        for(int i = 0; i < size; i++){
            String toPrint = list.getEnquiry(i, userID);
            if(toPrint != null){
                System.out.print((++count)+": ");
                System.out.println(toPrint);
            }
        }
        
        if(count == 0){
            System.out.println("You do not have any enquiries!");
        }
    }

    /**
     * View all student's own enquiries for a specific camp
     * 
     * @param userID User identification
     * @param list List of Enquiries
     * @param database Database of Users
     * @param camp Specific Camp
     */
    public static void viewQuestion(String userID, ListOfEnquiries list, Database database, Camp camp){
        int size = list.getSize();
        int count = 0;
        if(size == 0){
            System.out.println("You do not have any enquiries!");
            return;
        }
        System.out.println("The following are the enquiries you have submitted: ");
        for(int i = 0; i < size; i++){
            String toPrint = list.getEnquiry(i, userID);
            if(toPrint != null && list.getCampEnquiredID(i).matches(camp.getCampName())){
                System.out.print((++count)+": ");
                System.out.println(toPrint);
            }
        }
    }

    /**
     * Method call to delete an enquiry
     * 
     * @param index Index of the enquiry in list of enquiries
     * @param userID User identification
     * @param list List of enquiries
     * @param database Database of Users
     */
    public static void deleteQuestion(int index, String userID, ListOfEnquiries list, Database database){
        list.deleteEnquiry(index, userID);
    }

    /**
     * Method call to edit an enquiry
     * 
     * @param list List of enquiries
     * @param userID User identification
     * @param newEnquiry New enquiry to replace the old one
     * @param index Index of the enquiry in list of enquiries
     */
    public static void editEnquiry(ListOfEnquiries list, String userID, String newEnquiry, int index){
        list.editEnquiry(index, userID, newEnquiry);
    }
}
