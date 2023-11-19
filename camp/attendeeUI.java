import java.util.Scanner;

import camppackage.AttendeeRegister;
import enquiry.AttendeeEnquiry;
import camppackage.Camp;
import camppackage.CampInfo;
import enquiry.ListOfEnquiries;
import filehandler.Database;
import user.Student;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class AttendeeUI {
    
    public static void DisplayUI(Camp camp, CampInfo campInfo, Student attendee, Database database, ListOfEnquiries enquiries){
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
            System.out.println("7. Exit");
            System.out.println("Please enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //print camp descriptions
                    System.out.println("Camp Description: " + camp.getDescription());
                    break;

                case 2:
                    /*
                    * Peform double check to see if student wishes to withdraw
                    */
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
                    }

                    if(withdrawChoice == 1){
                        AttendeeRegister.withdrawCamp(campInfo, camp, attendee.getUserID(), database);
                    }
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
                    //edit the an enquiries 
                    int index = -1;
                    String newEnquiry = null;
                    /*
                     * Show list of enquiries open for student edit
                     */
                    enquiries.printAllEnquiries(attendee.getUserID(), false);

                    while(index == -1){
                        try{
                            System.out.println("Enter the index of the enquiry to edit:");
                            index = sc.nextInt();
                        }
                        catch(InputMismatchException e){
                            sc.nextLine();
                            System.out.println("Enter a valid integer choice!");
                        }
                    }
            
                    System.out.println("Kindly enter your enquiry for "+camp.getCampName());
                    newEnquiry = sc.nextLine();

                    AttendeeEnquiry.editEnquiry(enquiries, attendee.getUserID(), newEnquiry, index);
                    break;

                case 6:
                    // delete a chosen enquiry by the user
                    int index1 = -1;
                    /*
                     * Show list of enquiries open for student edit
                     */
                    enquiries.printAllEnquiries(attendee.getUserID(), false);

                    while(index1 == -1){
                        try{
                            System.out.println("Enter the index of the enquiry to delete:");
                            index = sc.nextInt();
                        }
                        catch(InputMismatchException e){
                            sc.nextLine();
                            System.out.println("Enter a valid integer choice!");
                        }
                    }

                    AttendeeEnquiry.deleteQuestion(index1, attendee.getUserID(), enquiries, database);
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

