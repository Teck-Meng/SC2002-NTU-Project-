import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import camppackage.Camp;
import camppackage.CampInfo;
import enquiry.AttendeeEnquiry;
import enquiry.ListOfEnquiries;
import enquiry.ReplyToStudent;
import filehandler.Database;
import user.Student;
import user.Staff;

public class EnquiriesUI {
    protected static void editEnquiry(ListOfEnquiries enquiries, Student attendee, Camp camp){
        //edit the an enquiries 
        Scanner sc = new Scanner(System.in);
        int index = -1;
        String newEnquiry = null;
        ArrayList<Integer> pointerToEnquiry = new ArrayList<Integer>();
        /*
            * Show list of enquiries open for student edit
            */
        ArrayList<String> list = enquiries.getEnquiries();
        int count = 0;
        /*
        * Print all enquiries that can be edited
            */
        System.out.println("The following are enquiries that you can edit: ");
        for(int i = 0; i < list.size() ; i++){
            String toPrint = enquiries.getEnquiry(i, attendee.getUserID());
            if(toPrint != null){
                System.out.print((++count)+": ");
                System.out.println(toPrint);
                pointerToEnquiry.add(enquiries.getEnquiryID(i));
            }
        }
        if(count == 0){
            System.out.println("You do not have any enquiries");
            return;
        }
        System.out.println("Press any key to continue:");
        sc.nextLine();
        while(true){
            try{
                System.out.println("Enter the index of the enquiry to edit:");
                index = sc.nextInt();
                if(index < 1 || index > count){
                    System.out.println("Kindly enter a choice between 1 and " + count);
                }
                else{
                    break;
                }                            
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid integer choice!");
                sc.nextLine();
            }
            catch(NumberFormatException n){
                System.out.println("Enter a valid integer choice!");
                sc.nextLine();
            }
            System.out.println("index: " + index);
        }
        sc.nextLine();
        System.out.println("Kindly enter your enquiry for " + camp.getCampName());
        newEnquiry = sc.nextLine();

        AttendeeEnquiry.editEnquiry(enquiries, attendee.getUserID(), newEnquiry, pointerToEnquiry.get(index - 1));
    }

    protected static void deleteEnquiry(ListOfEnquiries enquiries, Student attendee, Database database){
        System.out.println("The following are enquiries you can delete: ");
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> pointerToEnquiry = new ArrayList<Integer>();
        int index1 = -1;
        /*
            * Show list of enquiries open for student edit
            */
        ArrayList<String> list = enquiries.getEnquiries();
        int count = 0;
        /*
        * Print all enquiries that can be edited
            */
        for(int i = 0; i < list.size() ; i++){
            String toPrint = enquiries.getEnquiry(i, attendee.getUserID());
            if(toPrint != null){
                System.out.print((++count)+": ");
                System.out.println(toPrint);
                pointerToEnquiry.add(enquiries.getEnquiryID(i));
            }
        }
        if(count == 0){
            System.out.println("You do not have any enquiries");
            return;
        }
        System.out.println("Press any key to continue:");
        sc.nextLine();
        while(index1 == -1){
            try{
                System.out.println("Enter the index of the enquiry to delete:");
                index1 = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Enter a valid integer choice!");
            }
            catch(NumberFormatException n){
                System.out.println("Enter a valid integer choice!");
                sc.nextLine();
            }
        }

        AttendeeEnquiry.deleteQuestion(pointerToEnquiry.get(index1 - 1), attendee.getUserID(), enquiries, database);
    }

    /*
     * For committee member to reply to enquiry
     * Will include check to ensure committee does not reply to own enquiry
     */
    protected static void replyToEnquiry(ListOfEnquiries enquiries, Database database, Camp camp, ReplyToStudent replies){
        
    }
    
    /*
     * printReplies for student use for their own enquiries in that camp
     */
    protected static void printReplies(ListOfEnquiries enquiries, Student attendee, ReplyToStudent replies){
        System.out.println("The following are the replies you have received for your enquiries");
        int count = 0;
        ArrayList<String> list = enquiries.getEnquiries();
        for(int i = 0; i < list.size(); i++){
            String toPrint = enquiries.getEnquiry(i, attendee.getUserID());
            int ptr = enquiries.getEnquiryID(i);
            if(toPrint != null && enquiries.isEnquiryAnswered(i)){
                System.out.print("Question " + (++count) + ": ");
                System.out.println(toPrint);
                System.out.print("Reply to Question "+ (count) + ": ");
                System.out.println(replies.getReplyFromPtr(ptr));
            }
        }
        if(count == 0){
            System.out.println("You do not have any replies to any of your enquiries!");
            return;
        }
    }

    /*
     * printReplies for committeeUI use to print all replies for a specific camp
     */
    protected static void printReplies(ListOfEnquiries enquiries, Camp camp, ReplyToStudent replies){
        System.out.println("The following are the replies for this camp");
        int count = 0;
        ArrayList<String> list = enquiries.getEnquiries();
        for(int i = 0; i < list.size(); i++){
            String toPrint = enquiries.getEnquiry(i);
            int ptr = enquiries.getEnquiryID(i);
            /*
             * Ensure that only replies belonging to specific camp is printed out
             */
            if(enquiries.getCampEnquiredID(i).matches(camp.getCampName()) && enquiries.isEnquiryAnswered(i)){
                System.out.print("Question " + (++count) + ": ");
                System.out.println(toPrint);
                System.out.print("Reply to Question "+ (count) + ": ");
                System.out.println(replies.getReplyFromPtr(ptr));
            }
        }
        if(count == 0){
            System.out.println("You do not have any replies to any of your enquiries!");
            return;
        }
    }

    protected static void printReplies(ListOfEnquiries enquiries, CampInfo campInfo, ReplyToStudent replies, Staff staff){
        System.out.println("The following are the replies for this camp");
        int count = 0;
        ArrayList<String> list = enquiries.getEnquiries();
        for(int i = 0; i < list.size(); i++){
            String toPrint = enquiries.getEnquiry(i);
            int ptr = enquiries.getEnquiryID(i);
            /*
             * Extract current camp object
             */
            Camp currentCamp = campInfo.getCamp(enquiries.getCampEnquiredID(i));
            /*
             * Ensure that only replies belonging to specific camp is printed out
             */
            if(currentCamp.verifyStaff(staff) && enquiries.isEnquiryAnswered(i)){
                System.out.print("Question " + (++count) + ": ");
                System.out.println(toPrint);
                System.out.print("Reply to Question "+ (count) + ": ");
                System.out.println(replies.getReplyFromPtr(ptr));
            }
        }
        if(count == 0){
            System.out.println("You do not have any replies to any of your enquiries!");
            return;
        }
    }

    protected static void replyToEnquiries(ListOfEnquiries enquiries, Camp camp, String userID, ReplyToStudent replyToStudent, Database database){
        Scanner sc = new Scanner(System.in);
        int enquiryID = -1;

        while(enquiryID == -1){
            try{
                enquiries.printAllEnquiries(camp, database.getUser(userID), false);
                System.out.println("Enter the enquiry ID of the enquiry you want to reply to: ");
                enquiryID = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer choice!");
            }
        }
        
        sc.nextLine(); // Consume the newline character left by previous nextInt()
        System.out.println("Enter your reply: ");
        String reply = sc.nextLine();
        replyToStudent.addReply(reply, enquiryID, enquiries);

        //points added to committee member

        Student currentUser = (Student)database.getUser(userID);
        currentUser.addCommitteePoints(1);

    }
}
