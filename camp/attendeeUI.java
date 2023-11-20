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
                    exit = withdrawCamp(camp, campInfo, attendee, database);
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
                    editEnquiry(enquiries, attendee, camp);
                    break;

                case 6:
                    // delete a chosen enquiry by the user
                    deleteEnquiry(enquiries, attendee, database);
                    break;
                case 7:
                    /*
                     * Print out replies together with its corresponding enquiry
                     */
                    printReplies(enquiries, attendee, replies);
                    break;
                case 8:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice");
                }

                }

    }
    
    private static boolean withdrawCamp(Camp camp, CampInfo campInfo, Student attendee, Database database){
        Scanner sc = new Scanner(System.in);
        int withdrawChoice = -1;
        while(withdrawChoice == -1){
            try{
                System.out.println("Are you sure you want to withdraw from " + camp.getCampName() + "?");
                System.out.println("Enter 1 if you wish to confirm withdrawing process");
                System.out.println("Enter any other number if you do not wish to withdraw");
                withdrawChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid integer choice!");
                sc.nextLine();
            }
            catch(NumberFormatException n){
                System.out.println("Enter a valid integer choice!");
                sc.nextLine();
            }
        }

        if(withdrawChoice == 1){
            AttendeeRegister.withdrawCamp(campInfo, camp, attendee.getUserID(), database);
            System.out.println("Exiting interface due to withdrawal. . .");
            return true;
        }
        else{
            System.out.println("Exiting withdraw camp interface . . .");
        }
        return false;
    }

    private static void editEnquiry(ListOfEnquiries enquiries, Student attendee, Camp camp){
        //edit the an enquiries 
        Scanner sc = new Scanner(System.in);
        int index = -1;
        String newEnquiry = null;
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
                System.out.print((i+1)+": ");
                System.out.println(toPrint);
                count++;
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

        AttendeeEnquiry.editEnquiry(enquiries, attendee.getUserID(), newEnquiry, index - 1);
    }

    private static void deleteEnquiry(ListOfEnquiries enquiries, Student attendee, Database database){
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
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
                System.out.print((i+1)+": ");
                System.out.println(toPrint);
                count++;
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

        AttendeeEnquiry.deleteQuestion(index1 - 1, attendee.getUserID(), enquiries, database);
    }

    private static void printReplies(ListOfEnquiries enquiries, Student attendee, ReplyToStudent replies){
        System.out.println("The following are the replies you have received for your enquiries");
        int count = 0;
        ArrayList<String> list = enquiries.getEnquiries();
        for(int i = 0; i < list.size(); i++){
            String toPrint = enquiries.getEnquiry(i, attendee.getUserID());
            int ptr = enquiries.getEnquiryID(i);
            if(toPrint != null && enquiries.isEnquiryAnswered(i)){
                System.out.print("Question " + (i+1) + ": ");
                System.out.println(toPrint);
                System.out.print("Reply to Question "+ (i+1) + ": ");
                System.out.println(replies.getReplyFromPtr(ptr));
                count++;
            }
        }
        if(count == 0){
            System.out.println("You do not have any replies to any of your enquiries!");
            return;
        }
    }
}

