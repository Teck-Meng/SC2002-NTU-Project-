package enquiry;

import camppackage.Camp;
import filehandler.Database;

import java.util.Scanner;

public class AttendeeEnquiry {
    /*
     * Pass student object as parameter
     * This is a system prompt to ask question
     * AttendeeUI and CommitteeUI class to add option to select @param camp 
     */
    public static void askQuestion(String userID, Camp camp, ListOfEnquiries list, Database database){
        Scanner sc = new Scanner(System.in);
        String enquiry;

        System.out.println("Kindly enter your enquiry for "+camp.getCampName());
        enquiry = sc.nextLine();
        
        list.addEnquiry(enquiry, userID, database, camp);
        System.out.println("Enquiry successfully added!");
    }

    /*
     * Prints all enquiries sent by student
     */
    public static void viewQuestion(String userID, ListOfEnquiries list, Database database){
        int size = list.getSize();
        if(size == 0){
            System.out.println("You do not have any enquiries!");
            return;
        }
        System.out.println("The following are the enquiries you have submitted: ");
        for(int i = 0; i < size; i++){
            String toPrint = list.getEnquiry(i, userID);
            if(toPrint != null){
                System.out.print((i+1)+": ");
                System.out.println(toPrint);
            }
        }
    }

    public static void deleteQuestion(int index, String userID, ListOfEnquiries list, Database database){
        list.deleteEnquiry(index, userID);
        System.out.println("Enquiry deletion successful!");
    }

    public static void editEnquiry(ListOfEnquiries list, String userID, String newEnquiry, int index){
        list.editEnquiry(index, userID, newEnquiry);
        System.out.println("Enquiry edit successful!");
    }
}
