package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


import camppackage.Camp;
import camppackage.CampInfo;
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

/**
 * Student interface
 */
public class StudentUI {
    /**
     * Method that opens up student interface
     * 
     * @param student Student that opened up the student interface
     * @param campInfo Database of camps
     * @param database Database of users
     * @param clock Current date
     * @param enquiries List of enquiries
     * @param suggestions List of suggestions
     * @param replies List of replies
     */
    protected static void main(Student student, CampInfo campInfo, Database database, Time clock, ListOfEnquiries enquiries,
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
                    CampManagementUI.printListOfCamps(campInfo, student);
                    break;
                case 2:
                    CampManagementUI.registerCamp(campInfo, student, database, clock);
                    break;
                case 3:
                    callAttendeeUI(student, campInfo, database, enquiries, replies);
                    break;
                case 4:
                    if(student.getCommitteeCamp() == null){
                        System.out.println("You are not authorized to use this feature yet!");
                    }
                    else{
                        CommitteeMemUI.main(student.getCommitteeCamp(), student.getUserID(), database,
                                             enquiries, suggestions, replies, campInfo, student);
                    }
                    break;
                case 5:
                    CampManagementUI.printListOfRegisteredCamps(student, campInfo);
                    break;
                case 6:
                    if(student.getCommitteeCamp() == null){
                        System.out.println("You are not a committee member in any camp.");
                    }
                    else{
                        System.out.println("You are a camp committee member for " + student.getCommitteeCamp().getCampName());
                        System.out.println("You currently have " + student.getCommitteePoints() + " points!");
                    }
                    break;
                case 7:
                    enquiryManagement(campInfo, student, database, enquiries);
                    break;
                case 8:
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

    /**
     * The method that prompts student to choose which camp they want to access the attendee UI for
     * 
     * @param student Student requesting to enter attendee interface
     * @param campInfo Database of camps
     * @param database Database of users
     * @param enquiries List of enquiries
     * @param replies List of replies
     */
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
        AttendeeUI.displayUI(camp, campInfo, student, database, enquiries, replies);
    }
   
    /**
     * Enquiry management interface for students
     * 
     * @param campInfo Database of camps
     * @param student Student who opened the enquiry management interface
     * @param database Database of users
     * @param enquiries List of enquiries
     * @param attendee A
     */
    private static void enquiryManagement(CampInfo campInfo, Student student, Database database, ListOfEnquiries enquiries){
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
                    AttendeeEnquiry.viewQuestion(student.getUserID(), enquiries, database, currentCamp);  
                    break;
                case 2:
                    /*
                    * Make enquiry
                    */
                    AttendeeEnquiry.askQuestion(student.getUserID(), currentCamp, enquiries, database);
                    break;
                case 3:
                    /*
                     * Edit Enquiry
                     */
                    EnquiriesUI.editEnquiry(enquiries, student, currentCamp);
                    break;
                case 4:
                    /*
                    *  Delete Enquiry
                    */
                    EnquiriesUI.deleteEnquiry(enquiries, student, database);
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

