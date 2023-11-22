package report;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import camppackage.Camp;
import camppackage.CampInfo;
import filehandler.ClearFiles;
import user.Student;

/**
 * Class responsible for generating performance report
 * Class implements ReportGeneration interface
 */
public class PerformanceReport implements ReportGeneration{
    /**
     * <p>
     * Prints performance report of commmittee members for all camps created by staff,
     * to be used in StaffUI.java
     * </p>
     * <p>
     * StaffUI to be responsible to ask staff if they want to print performance report for all camps or one specific camp
     * </p>
     * <p>
     * Pass in listOfcamps by using database.getUser(userID).getListOfCamps(), ensure that user object has been typecasted to Staff,
     * order of report is alphabetical order
     * </p>
     * @param listOfCamps List of camps to print the performance report for
     * @param campInfo Database of Camps
     * 
     * @Override
     */
    public static void printReport(ArrayList<Camp> listOfCamps, CampInfo campInfo){
        ClearFiles.clearPerformanceReport();
        System.out.println("Generating Report . . .");
        for(int i = 0; i < listOfCamps.size(); i++){
            Camp currentCamp = listOfCamps.get(i);

            String campName = currentCamp.getCampName();
            String role = "Committee member";
            String location = currentCamp.getLocation();
            String description = currentCamp.getDescription();
            
            ArrayList<Student> committeeList = currentCamp.getCommitteeList().getListOfMembers();
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Performance_Report.csv", true)); 

                
                for(int j = 0; j < committeeList.size(); j++){
                    Student committeeMember = committeeList.get(j);
                    
                    String userID = committeeMember.getUserID();
                    String faculty = committeeMember.getFaculty().toString();
                    int points = committeeMember.getCommitteePoints();

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

    
    /**
     * <p>
     * Overloaded version of printReport
     * </p>
     * <p>
     * Prints performance report of commmittee members for a specific camp created by staff,
     * to be used in StaffUI.java
     * </p>
     * 
     * @param camp Specific camp to generate performance report for
     * @param campInfo Database of Camps
     * @Override
     */
    public static void printReport(Camp camp, CampInfo campInfo){
        ClearFiles.clearPerformanceReport();

        System.out.println("Generating Report . . .");

        ArrayList<Student> committeeList = camp.getCommitteeList().getListOfMembers();
        try { 
            PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Performance_Report.csv", true)); 

            for(int j = 0; j < committeeList.size(); j++){
                Student committeeMember = committeeList.get(j);
                String userID = committeeMember.getUserID();
                String faculty = committeeMember.getFaculty().toString();
                int points = committeeMember.getCommitteePoints();
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
