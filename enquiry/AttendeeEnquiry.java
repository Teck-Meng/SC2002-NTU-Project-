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
    public void askQuestion(String userID, Camp camp, listOfEnquiries list, Database database){
        Scanner sc = new Scanner(System.in);
        String enquiry;

        System.out.println("Kindly enter your enquiry for "+camp.getCampName());
        enquiry = sc.nextLine();
        
        list.addEnquiry(enquiry, userID, database, camp);
        sc.close();
    }

    /*
     * Prints all enquiries sent by student
     */
    public void viewQuestion(String userID, listOfEnquiries list, Database database){
        int size = list.getSize();
        for(int i = 0; i < size; i++){
            String toPrint = list.getEnquiry(i, userID);
            if(toPrint != null){
                System.out.println(toPrint);
            }
        }
    }

    public void deleteQuestion(int index, String userID, listOfEnquiries list, Database database){
        list.deleteEnquiry(index, userID);
    }

}
