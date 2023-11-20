package report;

import user.Faculty;
import user.Student;
import camppackage.CampInfo;
import camppackage.Camp;
import filehandler.clearFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AttendanceReport implements ReportGeneration{
    /*
     * Author's note:
     * This is the staff version of printReport
     * To be added to StaffUI
     * Pass in @param listOfcamps by using database.getUser(@param String userID).getListOfCamps()
     * To apply sorting in the future, it is recommended to apply sorting on listOfCamps of staff object first based on whatever
     * attributes one wishes to sort by
     * The default arrangement is based on which camp is registered first
     * This method will work as a console prompt to simulate filter
     */
    public static void printReport(ArrayList<Camp> listOfCamps, CampInfo campInfo){
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
        /*
         * Terminate printing of report if staff does not wish to continue
         */
        if(userChoice == 3){
            System.out.println("Exiting report generation page . . .");
            return;
        }
        System.out.println("Generating report . . .");
        /*
         * Clear attendance report to make way for new one
         */
        clearFiles.clearAttendanceReport();
        /*
         * Extract staff's list of camps
         */
        /*
         * Iterate through each camp and extract attendee list and camp committee list 
         */
        for(int i = 0; i < listOfCamps.size(); i++){
            /*
             * Extract the necessary list for attendance report generation
             */
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

    /*
     * Overloaded method of printReport
     * This can also be used by staff if they only want to print report for one specific camp
     * To be used by camp committee member
     * Overall, this method to be added to StaffUI and CommitteeMemUI
     * Use getCommitteCamp() on Student object in committeMemUI.java for @param committeeCamp
     * For StaffUI, kindly add a console prompt method in the UI class to prompt staff to choose the camp to be passed in as arg
     */
    public static void printReport(Camp committeeCamp, CampInfo campInfo){
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
        /*
         * Terminate printing of report if staff does not wish to continue
         */
        if(userChoice == 3){
            System.out.println("Exiting report generation page . . .");
            return;
        }
        System.out.println("Generating report . . .");
        /*
         * Clear attendance report to make way for new one
         */
        clearFiles.clearAttendanceReport();
        switch(userChoice){
                case 0:
                    attendeeReportHandling(committeeCamp.getAttendeeList().getListOfAttendees(), committeeCamp);
                    committeeReportHandling(committeeCamp.getCommitteeList().getListOfMembers(), committeeCamp);
                    break;
                case 1:
                    attendeeReportHandling(committeeCamp.getAttendeeList().getListOfAttendees(), committeeCamp);
                    break;
                case 2:
                    committeeReportHandling(committeeCamp.getCommitteeList().getListOfMembers(), committeeCamp);
                    break;
            }
        System.out.println("Attendance Report successfully generated");
    }

    public static void attendeeReportHandling(ArrayList<Student> attendeeList, Camp camp){
             try { 
                    /*
                     * Initialize printwriter
                     */
                    PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/AttendanceReport.csv", true));
                    String campName = camp.getCampName();
                    String role = "Attendee";
                    String location = camp.getLocation();
                    String description = camp.getDescription();
                    for(int i = 0; i < attendeeList.size(); i++){
                        Student attendee = attendeeList.get(i);
                        /*
                         * Write to csv
                         */
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

    public static void committeeReportHandling(ArrayList<Student> committeeList, Camp camp){
            try { 
                    /*
                     * Initialize printwriter
                     */
                    PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/AttendanceReport.csv", true));
                    String campName = camp.getCampName();
                    String role = "Camp committee member";
                    String location = camp.getLocation();
                    String description = camp.getDescription();
                    for(int i = 0; i < committeeList.size(); i++){
                        Student committeeMember = committeeList.get(i);
                        /*
                         * Write to csv
                         */
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

    public static void facultyFilteredReport(ArrayList<Student> attendeeList, ArrayList<Student> committeeList, Camp camp, Faculty faculty){
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
        /*
         * Terminate printing of report if staff does not wish to continue
         */
        if(userChoice == 3){
            System.out.println("Exiting report generation page . . .");
            return;
        }
        System.out.println("Generating report . . .");
        /*
         * Clear attendance report to make way for new one
         */
        clearFiles.clearAttendanceReport();
        switch(userChoice){
                case 0:
                    facultyFilteredAttendee(attendeeList, camp, faculty);
                    facultyFilteredCommittee(committeeList, camp, faculty);
                    break;
                case 1:
                    facultyFilteredAttendee(attendeeList, camp, faculty);
                    break;
                case 2:
                    facultyFilteredCommittee(committeeList, camp, faculty);
                    break;
            }
        System.out.println("Attendance Report successfully generated");
    }

    public static void facultyFilteredAttendee(ArrayList<Student> attendeeList, Camp camp, Faculty filter){
             try { 
                    /*
                     * Initialize printwriter
                     */
                    PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/AttendanceReport.csv", true));
                    String campName = camp.getCampName();
                    String role = "Attendee";
                    String location = camp.getLocation();
                    String description = camp.getDescription();
                    for(int i = 0; i < attendeeList.size(); i++){
                        Student attendee = attendeeList.get(i);
                        /*
                         * Write to csv
                         */
                        if(attendee.getFaculty() == filter){
                            String userID = attendee.getUserID();
                            String faculty = attendee.getFaculty().toString();
                            
                            csvWriter.println(userID + "," + faculty + "," + campName + "," + role + "," + location + "," +
                                            description + ",");   
                        }           
                    }
                    csvWriter.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                }
            
        }

    public static void facultyFilteredCommittee(ArrayList<Student> committeeList, Camp camp, Faculty filter){
            try { 
                    /*
                     * Initialize printwriter
                     */
                    PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/AttendanceReport.csv", true));
                    String campName = camp.getCampName();
                    String role = "Camp committee member";
                    String location = camp.getLocation();
                    String description = camp.getDescription();
                    for(int i = 0; i < committeeList.size(); i++){
                        Student committeeMember = committeeList.get(i);
                        /*
                         * Write to csv
                         */
                        if(committeeMember.getFaculty() == filter){
                            String userID = committeeMember.getUserID();
                            String faculty = committeeMember.getFaculty().toString();
                            
                            csvWriter.println(userID + "," + faculty + "," + campName + "," + role + "," + location + "," +
                                            description + ",");
                        }              
                    }
                    csvWriter.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                }
        }
}
