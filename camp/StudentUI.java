import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import camppackage.AttendeeList;
import camppackage.Camp;
import camppackage.CampInfo;
import camppackage.CommitteeList;
import user.Student;
import filehandler.Database;
import clock.Time;
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
                System.out.println("6. Exit");
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
                    printListOfCamps(campInfo, student);
                    break;
                case 2:
                    // Camp Registration
                    registerCamp(campInfo, student, database, clock);
                    break;
                case 3:
                    callAttendeeUI(student, campInfo, database, enquiries);
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
                                             enquiries, suggestions, replies, campInfo);
                    }
                    break;
                case 5:
                    // View all registered camps
                    printListOfRegisteredCamps(student);
                    break;
                case 6:
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

    private static void printListOfCamps(CampInfo campInfo, Student student){
        ArrayList<Camp> listOfCamps = campInfo.getCampList(student);
        System.out.println("This are the list of camps available to you:");
        for(int i = 0; i < listOfCamps.size(); i++){
            System.out.print((i + 1) + ": ");
            listOfCamps.get(i).print();
        }
    }

    private static void registerCamp(CampInfo campInfo, Student student, Database database, Time clock){
        Scanner sc = new Scanner(System.in);
        int registerChoice = -1;
        int campCommitteeChoice = -1;
        /*
            * Get a new arraylist containing only camps that are open to student
            * Exclude camps that they have already registered for or are blacklisted from
            */
        ArrayList<Camp> listOfCamps = campInfo.getCampList(student, campInfo, database, clock);
        if(listOfCamps.size() == 0){
            System.out.println("No camps are available for registration!");
            return;
        }

        while(registerChoice < 0 || registerChoice > listOfCamps.size()){
            try{
                for(int i = 0; i < listOfCamps.size(); i++){
                    listOfCamps.get(i).print();
                    }
                    System.out.println("Enter 0 if you wish to quit the registration interface.");
                    System.out.println("Enter your choice of camp based on its numerical index: ");
                    registerChoice = sc.nextInt();

                    if(registerChoice == 0){
                        break;
                }
                /*
                    * Error message if choice is out of bound
                    */
                if(registerChoice < 0 || registerChoice > listOfCamps.size()){
                    System.out.println("Please enter a choice between 0 to " + listOfCamps.size());
                }
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid integer value!");
                sc.nextLine();
            }
        }

        Camp selectedCamp = listOfCamps.get(registerChoice - 1);

        /*
            * Check if student is already a committee member for another camp
            * This is as a student can only be committee member for one camp
            */
        if(student.getCommitteeCamp() == null){
            while(campCommitteeChoice == -1){
                try{
                    System.out.println("Are you joining this camp as camp committee?: ");
                    System.out.println("Enter 1 if you wish to register for camp committee for " + selectedCamp.getCampName());
                    System.out.println("Enter your choice: ");
                    campCommitteeChoice = sc.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("Enter a valid integer value!");
                    sc.nextLine();
                }
            }
        }

        if(campCommitteeChoice == 1){
            /*
                * student want to be camp committee member
                */
            student.addCamp(selectedCamp, true);
            CommitteeList committeeList = selectedCamp.getCommitteeList();
            committeeList.addCommitteeMember(student, campInfo, selectedCamp);
        }
        else{
            /*
                * Register as attendee
                */
            student.addCamp(selectedCamp, false);
            AttendeeList attendeeList = selectedCamp.getAttendeeList();
            attendeeList.addAttendee(student);
        }
        System.out.println("Camp registered Successfully!");
        //Register for a camp
    }
    
    private static void printListOfRegisteredCamps(Student student){
        ArrayList<Camp> registeredCamps = student.getListOfCamps();
        System.out.println("The following are the camps you have registered for as an attendee: ");
        for(int i = 0; i < registeredCamps.size(); i++){
            System.out.print((i+1) + " : ");
            registeredCamps.get(i).print();
        }
        if(student.getCommitteeCamp() != null){
            System.out.println("This is the camp that you have registered for as a committee member: ");
            student.getCommitteeCamp().print();
        }
    }

    private static void callAttendeeUI(Student student, CampInfo campInfo, Database database, ListOfEnquiries enquiries){
        Scanner sc = new Scanner(System.in);
        int attendeeChoice = -1;
        ArrayList<Camp> registeredCamps = student.getListOfCamps();
        while(attendeeChoice < 0 || attendeeChoice > registeredCamps.size()){
            try{
                System.out.println("The following are the camps you have registered for as an attendee: ");
                for(int i = 0; i < registeredCamps.size(); i++){
                    System.out.print((i+1) + " : ");
                    registeredCamps.get(i).print();
                }
                System.out.println("Enter 0 if you wish to quit the registration interface.");
                System.out.println("Enter your choice of camp based on its numerical index: ");
                attendeeChoice = sc.nextInt();
                if(attendeeChoice == 0){
                    return;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid integer choice!");
                sc.nextLine();
            }
        }

        Camp camp = registeredCamps.get(attendeeChoice - 1);
        AttendeeUI.DisplayUI(camp, campInfo, student, database, enquiries);
    }
   
}
