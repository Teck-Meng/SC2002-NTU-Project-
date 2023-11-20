import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import camppackage.AttendeeList;
import camppackage.AttendeeRegister;
import camppackage.Camp;
import camppackage.CampInfo;
import camppackage.CommitteeList;
import camppackage.PrintCampDetails;
import clock.Time;
import enquiry.ListOfEnquiries;
import enquiry.ReplyToStudent;
import filehandler.Database;
import user.Student;

public class CampManagementUI {
    /*
     *  Student version to print all camps
     * Returns camp list that student have access to
     */
    protected static ArrayList<Camp> printListOfCamps(CampInfo campInfo, Student student){
        int count = 0;
        ArrayList<Camp> listOfCamps = campInfo.getCampList(student);
        System.out.println("These are the list of camps available to you:");
        for(int i = 0; i < listOfCamps.size(); i++){
            System.out.print((++count + 1) + ": ");
            PrintCampDetails.print(listOfCamps.get(i));
            PrintCampDetails.printRemainingSlots(listOfCamps.get(i), campInfo);
        }
        return listOfCamps;
    }

    protected static void registerCamp(CampInfo campInfo, Student student, Database database, Time clock){
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
                    PrintCampDetails.print(listOfCamps.get(i));
                    PrintCampDetails.printRemainingSlots(listOfCamps.get(i), campInfo);
                    }
                    System.out.println("Enter 0 if you wish to quit the registration interface.");
                    System.out.println("Enter your choice of camp based on its numerical index: ");
                    registerChoice = sc.nextInt();
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
        if(registerChoice == 0){
            return;
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
    
    protected static void printListOfRegisteredCamps(Student student, CampInfo campInfo){
        ArrayList<Camp> registeredCamps = student.getListOfCamps();
        System.out.println("The following are the camps you have registered for as an attendee: ");
        for(int i = 0; i < registeredCamps.size(); i++){
            System.out.print((i+1) + " : ");
            PrintCampDetails.print(registeredCamps.get(i));
            PrintCampDetails.printRemainingSlots(registeredCamps.get(i), campInfo);
        }
        if(student.getCommitteeCamp() != null){
            System.out.println("This is the camp that you have registered for as a committee member: ");
            PrintCampDetails.print(student.getCommitteeCamp());
            PrintCampDetails.printRemainingSlots(student.getCommitteeCamp(), campInfo);
        }
    }

    protected static boolean withdrawCamp(Camp camp, CampInfo campInfo, Student attendee, Database database){
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
}
