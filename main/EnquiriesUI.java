package main;

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

/**
 * Class responsible for handling request regarding enquiries on the console level
 */
public class EnquiriesUI {
    /**
     * Console prompt to edit enquiry
     * 
     * @param enquiries List of enquiries
     * @param attendee Student object of attendee who wishes to edit enquiry
     * @param camp The camp that the student wishes to edit enquiries for
     */
    protected static void editEnquiry(ListOfEnquiries enquiries, Student attendee, Camp camp){
        Scanner sc = new Scanner(System.in);
        int index = -1;
        String newEnquiry = null;
        ArrayList<Integer> pointerToEnquiry = new ArrayList<Integer>();
        
        ArrayList<String> list = enquiries.getEnquiries();
        int count = 0;
        
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

    /**
     * Console prompt to delete enquiry
     * 
     * @param enquiries List of enquiries
     * @param attendee Student object of attendee who wishes to delete enquiry
     * @param database Database of Users
     */
    protected static void deleteEnquiry(ListOfEnquiries enquiries, Student attendee, Database database){
        System.out.println("The following are enquiries you can delete: ");
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> pointerToEnquiry = new ArrayList<Integer>();
        int index1 = -1;
        
        ArrayList<String> list = enquiries.getEnquiries();
        int count = 0;
        
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

    /**
     * <p>
     * Console prompt for committee member to reply to enquiry
     * </p>
     * <p>
     * Commitee Member not allowed to reply to their own enquiry
     * </p>
     * @param enquiries List of enquiries
     * @param database Database of Users
     * @param camp Camp committee member's camp
     * @param replies List of replies
     */
    protected static void replyToEnquiry(ListOfEnquiries enquiries, Database database, Camp camp, ReplyToStudent replies){
        
    }
    
    /**
     * Prints student's enquiries and corresponding replies onto console
     * @param enquiries List of enquiries
     * @param attendee Student object of attendee who wishes to print all replies
     * @param replies List of replies
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

    /**
     * Print all replies and corresponding enquiries onto console
     * For Camp Committee member use
     * 
     * @param enquiries List of enquiries
     * @param camp Camp committee member's camp
     * @param replies List of replies
     */
    protected static void printReplies(ListOfEnquiries enquiries, Camp camp, ReplyToStudent replies){
        System.out.println("The following are the replies for this camp");
        int count = 0;
        ArrayList<String> list = enquiries.getEnquiries();
        for(int i = 0; i < list.size(); i++){
            String toPrint = enquiries.getEnquiry(i);
            int ptr = enquiries.getEnquiryID(i);
            
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

    /**
     * Print all enquiries and their corresponding replies onto console for all camps belonging to a staff
     * 
     * @param enquiries List of enquiries
     * @param campInfo Database of Camps
     * @param replies List of replies
     * @param staff Staff who wishes to print all replies
     */
    protected static void printReplies(ListOfEnquiries enquiries, CampInfo campInfo, ReplyToStudent replies, Staff staff){
        System.out.println("The following are the replies for your camps: ");
        int count = 0;
        ArrayList<String> list = enquiries.getEnquiries();
        for(int i = 0; i < list.size(); i++){
            
            String toPrint = enquiries.getEnquiry(i);
            int ptr = enquiries.getEnquiryID(i);
            
            Camp currentCamp = campInfo.getCamp(enquiries.getCampEnquiredID(i));
            System.out.println("Replies for " + currentCamp.getCampName());
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

    /**
     * System prompt for Staff/Committee member to reply to enquiries
     *  
     * @param enquiries List of enquiries
     * @param camp Camp which receieved the enquiry
     * @param userID User identification of staff/committee member
     * @param replyToStudent List of replies
     * @param database Database of Users
     */
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
        
        sc.nextLine();
        System.out.println("Enter your reply: ");
        String reply = sc.nextLine();
        replyToStudent.addReply(reply, enquiryID, enquiries);


        Student currentUser = (Student)database.getUser(userID);
        currentUser.addCommitteePoints(1);

    }
}
