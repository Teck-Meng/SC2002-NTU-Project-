package report;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import camppackage.Camp;
import camppackage.CampInfo;
import filehandler.clearFiles;
import user.Student;

public class PerformanceReport implements ReportGeneration{
    /*
     * Prints performance report of commmittee members for all camps created by staff
     * To be used in StaffUI.java
     * StaffUI to be responsible to ask staff if they want to print performance report for all camps or one specific camp
     * Pass in @param listOfcamps by using database.getUser(@param String userID).getListOfCamps()
     */
    public static void printReport(ArrayList<Camp> listOfCamps, CampInfo campInfo){
        /*
         * Clear attendance report to make way for new one
         */
        clearFiles.clearPerformanceReport();
        System.out.println("Generating Report . . .");
        for(int i = 0; i < listOfCamps.size(); i++){
            /*
             * Extract current camp object
             */
            Camp currentCamp = listOfCamps.get(i);
            /*
             * Extract necessary camp attributes
             */
            String campName = currentCamp.getCampName();
            String role = "Committee member";
            String location = currentCamp.getLocation();
            String description = currentCamp.getDescription();
            /*
             * Extract committee List for that camp
             */
            ArrayList<Student> committeeList = currentCamp.getCommitteeList().getListOfMembers();
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Performance_Report.csv", true)); 

                /*
                * Extract every committee member iteratively
                    */
                for(int j = 0; j < committeeList.size(); j++){
                    Student committeeMember = committeeList.get(j);
                    /*
                    * Extract all attributes to be written to csv
                    */
                    String userID = committeeMember.getUserID();
                    String faculty = committeeMember.getFaculty().toString();
                    int points = committeeMember.getCommitteePoints();
                    /*
                    * Input to csv
                    */
                    csvWriter.println(userID + "," + faculty + "," + campName + "," + role + "," + location + "," + description 
                    + "," + points + ","); // Write the data in CSV format  
                    
                }
                csvWriter.close(); 
            } catch (IOException e) { 
                    e.printStackTrace(); 
            }
            
        }
        System.out.println("Performance report successfully generated!");
    }

    /*
     * Overloaded version of printReport
     * Prints performance report of commmittee members for all camps created by staff
     * To be used in StaffUI.java
     * Pass in @param listOfcamps by using database.getUser(@param String userID).getListOfCamps()
     */
    public static void printReport(Camp camp, CampInfo campInfo){
        /*
         * Clear attendance report to make way for new one
         */
        clearFiles.clearPerformanceReport();
        System.out.println("Generating Report . . .");
        /*
         * Extract committee list for specific camp
         */
        ArrayList<Student> committeeList = camp.getCommitteeList().getListOfMembers();
        try { 
            PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Performance_Report.csv", true)); 

            /*
            * Extract every committee member iteratively
                */
            for(int j = 0; j < committeeList.size(); j++){
                Student committeeMember = committeeList.get(j);
                /*
                * Extract all attributes to be written to csv
                */
                String userID = committeeMember.getUserID();
                String faculty = committeeMember.getFaculty().toString();
                int points = committeeMember.getCommitteePoints();
                /*
                * Input to csv
                */
                csvWriter.println(userID + "," + faculty + "," + camp.getCampName() + ",Committee member," + camp.getLocation() 
                + "," + camp.getDescription() + "," + points + ","); // Write the data in CSV format  
                
            }
            csvWriter.close(); 
        } catch (IOException e) { 
                e.printStackTrace(); 
        }
        System.out.println("Performance report successfully generated!");
    }

}
