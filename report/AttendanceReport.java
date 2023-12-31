package report;


import user.Student;
import camppackage.CampInfo;
import camppackage.Camp;
import filehandler.ClearFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * Class responsible for generating attendance report
 * </p>
 * <p>
 * Class implements ReportGeneration interface
 * </p>
 */
public class AttendanceReport implements ReportGeneration{

    /**
     * <p>
     * This is the staff version of printReport,
     * to be added to StaffUI
     * </p>
     * <p>
     * Pass in listOfcamps by using database.getUser(userID).getListOfCamps(), ensure that database.getUser is typecasted to staff first.
     * To apply sorting in the future, it is recommended to apply sorting on listOfCamps of staff object first based on whatever
     * attributes one wishes to sort by
     * </p>
     * <p>
     * The default arrangement is based on which camp is registered first, 
     * this method will work as a console prompt to simulate filter
     * </p>
     * @param listOfCamps List of camps that a staff has created
     * @param campInfo Database of Camps
     * @Override
     */
    public static void printReport(ArrayList<Camp> listOfCamps, CampInfo campInfo){
        Scanner sc = new Scanner(System.in);
        int userChoice = -1;

        while(userChoice < 0|| userChoice > 3){
            try{
                System.out.println("What additonal filters would you like to apply to the report that you want to print?");
                System.out.println("0 - Both attendees and committee members included");
                System.out.println("1 - Only attendees included");
                System.out.println("2 - Only camp committee members included");
                System.out.println("3 - Quit report generation");
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Kindly enter a valid integer choice!");
                sc.nextLine();
            }
            if(userChoice < 0|| userChoice > 3){
                System.out.println("Please enter a choice from 0 to 3!");
            }
        }
        if(userChoice == 3){
            System.out.println("Exiting report generation page . . .");
            return;
        }
        System.out.println("Generating report . . .");

        ClearFiles.clearAttendanceReport();

        for(int i = 0; i < listOfCamps.size(); i++){
            Camp currentCamp = listOfCamps.get(i);

            switch(userChoice){
                case 0:
                    attendeeReportHandling(currentCamp.getAttendeeList().getListOfAttendees(), currentCamp);
                    committeeReportHandling(currentCamp.getCommitteeList().getListOfMembers(), currentCamp);
                    break;
                case 1:
                    attendeeReportHandling(currentCamp.getAttendeeList().getListOfAttendees(), currentCamp);
                    break;
                case 2:
                    committeeReportHandling(currentCamp.getCommitteeList().getListOfMembers(), currentCamp);
                    break;
            }
        }
        System.out.println("Attendance Report successfully generated");
    }

    /**
     * <p>
     * Overloaded method of printReport
     * </p>
     * <p>
     * This can also be used by staff if they only want to print report for one specific camp,
     * To be used by camp committee member
     * </p>
     * <p>
     * Overall, this method to be added to StaffUI and CommitteeMemUI
     * </p>
     * <p>
     * Use getCommitteCamp() on Student object in committeMemUI.java for committeeCamp
     * </p>
     * <p>
     * For StaffUI, kindly add a console prompt method in the UI class to prompt staff to choose the camp to be passed in as arg
     * </p>
     * @param camp Specific camp that the report will be printed for
     * @param campInfo Database of Camps
     * 
     * @Override
     */
    public static void printReport(Camp camp, CampInfo campInfo){
        Scanner sc = new Scanner(System.in);
        int userChoice = -1;

        while(userChoice < 0|| userChoice > 3){
            try{
                System.out.println("What filters would you like to apply to the report that you want to print?");
                System.out.println("0 - Both attendees and committee members included");
                System.out.println("1 - Only attendees included");
                System.out.println("2 - Only camp committee members included");
                System.out.println("3 - Quit report generation");
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Kindly enter a valid integer choice!");
                sc.nextLine();
            }
            if(userChoice < 0|| userChoice > 3){
                System.out.println("Please enter a choice from 0 to 3!");
            }
        }
        if(userChoice == 3){
            System.out.println("Exiting report generation page . . .");
            return;
        }
        System.out.println("Generating report . . .");

        ClearFiles.clearAttendanceReport();

        switch(userChoice){
                case 0:
                    attendeeReportHandling(camp.getAttendeeList().getListOfAttendees(), camp);
                    committeeReportHandling(camp.getCommitteeList().getListOfMembers(), camp);
                    break;
                case 1:
                    attendeeReportHandling(camp.getAttendeeList().getListOfAttendees(), camp);
                    break;
                case 2:
                    committeeReportHandling(camp.getCommitteeList().getListOfMembers(), camp);
                    break;
            }
        System.out.println("Attendance Report successfully generated");
    }

    /**
     * Method called by printReport to allow Staff to choose to print only attendees in report,
     * call this together with committeeReportHandling to print for all
     * 
     * @param attendeeList List of attendees in specific camp
     * @param camp Current camp to generate report for
     */
    public static void attendeeReportHandling(ArrayList<Student> attendeeList, Camp camp){
            try { 
                    PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/AttendanceReport.csv", true));
                    String campName = camp.getCampName();
                    String role = "Attendee";
                    String location = camp.getLocation();
                    String description = camp.getDescription();
                    for(int i = 0; i < attendeeList.size(); i++){
                        Student attendee = attendeeList.get(i);
                        // write to csv
                        String userID = attendee.getUserID();
                        String faculty = attendee.getFaculty().toString();
                        
                        csvWriter.println(userID + "," + faculty + "," + campName + "," + role + "," + location + "," +
                                        description + ",");              
                    }
                    csvWriter.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                }
            
        }
    /**
     * Method called by printReport to allow Staff to choose to print only committee members in report,
     * call this together with attendeeReportHandling to print for all
     * 
     * @param committeeList List of committee members in specific camp
     * @param camp Specific canp
     */
    private static void committeeReportHandling(ArrayList<Student> committeeList, Camp camp){
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/AttendanceReport.csv", true));
                String campName = camp.getCampName();
                String role = "Camp committee member";
                String location = camp.getLocation();
                String description = camp.getDescription();
                for(int i = 0; i < committeeList.size(); i++){
                    Student committeeMember = committeeList.get(i);
                    // write to csv
                    String userID = committeeMember.getUserID();
                    String faculty = committeeMember.getFaculty().toString();
                    
                    csvWriter.println(userID + "," + faculty + "," + campName + "," + role + "," + location + "," +
                                    description + ",");              
                }
                csvWriter.close(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            }
        }

    
}
