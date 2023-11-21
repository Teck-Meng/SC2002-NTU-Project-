import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import camppackage.AttendeeList;
import camppackage.Camp;
import camppackage.CampInfo;
import camppackage.CommitteeList;
import camppackage.PrintCampDetails;
import user.Student;
import user.UserAuthenticator;
import filehandler.Database;
import filehandler.PasswordManager;
import clock.Time;
import enquiry.AttendeeEnquiry;
import enquiry.ListOfEnquiries;
import enquiry.ListOfSuggestions;
import enquiry.ReplyToStudent;

public class StudentUI {
    public static void Main(Student student, CampInfo campInfo, Database database, Time clock, ListOfEnquiries enquiries,
                            ListOfSuggestions suggestions, ReplyToStudent replies){
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int choice = -1;
        while(!exit){
            try{
                System.out.println("--- Student Menu ---");
                System.out.println("1: View list of camps"); 
                System.out.println("2: Register for a camp"); 
                System.out.println("3: Modify Registered Camps");
                System.out.println("4: Committee Camp Affairs");
                System.out.println("5: View all registered Camps");
                System.out.println("6. View user status");
                System.out.println("7. Manage your Enquiries");
                System.out.println("8. Change password");
                System.out.println("9. Exit");
                System.out.println("Please enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer value!");
            }

            switch(choice){
                case 1:
                    // View list of camps
                    CampManagementUI.printListOfCamps(campInfo, student);
                    break;
                case 2:
                    // Camp Registration
                    CampManagementUI.registerCamp(campInfo, student, database, clock);
                    break;
                case 3:
                    callAttendeeUI(student, campInfo, database, enquiries, replies);
                    // Modify registered Camp, give user choice of camp and call AttendeeUI afterwards
                    break;
                case 4:
                    /*
                     * Call Camp Committee UI if Student has committee camp
                     * Else print out error message
                     */
                    if(student.getCommitteeCamp() == null){
                        System.out.println("You are not authorized to use this feature yet!");
                    }
                    else{
                        CommitteeMemUI.main(student.getCommitteeCamp(), student.getUserID(), database,
                                             enquiries, suggestions, replies, campInfo, student);
                    }
                    break;
                case 5:
                    // View all registered camps
                    CampManagementUI.printListOfRegisteredCamps(student, campInfo);
                    break;
                case 6:
                    // Shows whether student is a camp committee member in system prompt
                    if(student.getCommitteeCamp() == null){
                        System.out.println("You are not a committee member in any camp.");
                    }
                    else{
                        System.out.println("You are a camp committee member for " + student.getCommitteeCamp().getCampName());
                        System.out.println("You currently have " + student.getCommitteePoints() + " points!");
                    }
                    break;
                case 7:
                    /*
                    * Call method to allow students to manage enquiries
                    */
                    enquiryManagement(campInfo, student, database, enquiries, student);
                    break;
                case 8:
                    /*
                     * Change password
                     */
                    int index = database.getUserIndex(student.getUserID());
                    PasswordManager.changePassword(index, database);
                    while(true){
                        int id = UserAuthenticator.verifyLogin(database);
                        if(id > -1){
                            break;
                        }
                    }
                    break;
                case 9:
                    int choiceToExit = -1;
                    while(choiceToExit == -1){
                        try{
                            System.out.println("Are you sure you want to exit from CAM?:");
                            System.out.println("Enter 1 if you wish to exit CAM");
                            System.out.println("Enter any other number if you do not wish to exit CAM");
                            System.out.println("Enter your choice: ");
                            choiceToExit = sc.nextInt();
                            sc.nextLine();
                        }
                        catch(InputMismatchException e){
                            sc.nextLine();
                            System.out.println("Please enter a valid integer value!");
                        }
                    }
                    if(choiceToExit == 1){
                        return;
                    }
                    break;
                default:
                    System.out.println("Kindly enter a valid choice from 1 to 6!");
            }
        }
    }

    private static void callAttendeeUI(Student student, CampInfo campInfo, Database database, ListOfEnquiries enquiries, 
                                        ReplyToStudent replies){
        Scanner sc = new Scanner(System.in);
        int attendeeChoice = -1;
        ArrayList<Camp> registeredCamps = student.getListOfCamps();
        while(attendeeChoice < 0 || attendeeChoice > registeredCamps.size()){
            try{
                System.out.println("The following are the camps you have registered for as an attendee: ");
                for(int i = 0; i < registeredCamps.size(); i++){
                    System.out.print((i+1) + " : ");
                    PrintCampDetails.print(registeredCamps.get(i));
                    PrintCampDetails.printRemainingSlots(registeredCamps.get(i), campInfo);
                }
                System.out.println("Enter 0 if you wish to quit the modification interface.");
                System.out.println("Enter your choice of camp based on its numerical index: ");
                attendeeChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid integer choice!");
                sc.nextLine();
            }
        }

        if(attendeeChoice == 0){
            return;
        }
        Camp camp = registeredCamps.get(attendeeChoice - 1);
        AttendeeUI.DisplayUI(camp, campInfo, student, database, enquiries, replies);
    }
   
    public static void enquiryManagement(CampInfo campInfo, Student student, Database database, ListOfEnquiries enquiries,
                                        Student attendee){
        Scanner sc = new Scanner(System.in);
        ArrayList<Camp> listOfCamps = CampManagementUI.printListOfCamps(campInfo, student);
        Camp currentCamp;
        int choice = -1;
        while(true){
            System.out.println("Kindly enter your choice of camp to manage enquiries for: ");
            try{
                choice = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer choice!");
            }
            if(choice > 0 || choice <= listOfCamps.size()){
                currentCamp = campInfo.getCamp(choice - 1);
                break;
            }
            System.out.println("Invalid option!");
            CampManagementUI.printListOfCamps(campInfo, student);
        }

        int userChoice = -1;
        while(userChoice != 5){
            try{
                System.out.println("Welcome to the enquiry managment interface!");
                System.out.println("The following are the options available for this interface:");
                System.out.println("1. View Enquiries");
                System.out.println("2. Make an Enquiry");
                System.out.println("3. Edit an enquiry");
                System.out.println("4. Delete an Enquiry");
                System.out.println("5. Exit");
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer option!");
            }

            switch(userChoice){
                case 1:
                    /*
                     * View enquiries for camp
                     */
                    AttendeeEnquiry.viewQuestion(attendee.getUserID(), enquiries, database, currentCamp);  
                    break;
                case 2:
                    /*
                    * Make enquiry
                    */
                    AttendeeEnquiry.askQuestion(attendee.getUserID(), currentCamp, enquiries, database);
                    break;
                case 3:
                    /*
                     * Edit Enquiry
                     */
                    EnquiriesUI.editEnquiry(enquiries, attendee, currentCamp);
                    break;
                case 4:
                    /*
                    *  Delete Enquiry
                    */
                    EnquiriesUI.deleteEnquiry(enquiries, attendee, database);
                    break;
                case 5:
                    System.out.println("Exiting enquiry management interface . . .");
                    break;
                default:
                    System.out.println("Invalid option! Please try again!");
            }
    }
}
}

