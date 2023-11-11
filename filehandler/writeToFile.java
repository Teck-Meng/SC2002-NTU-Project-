package filehandler;

import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;


import camppackage.Camp;
import user.Student;
import user.Staff;
import user.User;
import camppackage.CampInfo;

import report.PerformanceReport;


/*
 * Class will contain all methods to write to csv
 * csv file name will be passed as @param_type String
 */
public class writeToFile {
    public static void main(String[] args){
        /*
         * Testing environment
         */
        Database database = readFromFile.readUserList();
        database = readFromFile.readPasswords(database);
        CampInfo campInfo = readFromFile.readListOfCamps(database);
        readFromFile.readAttendeeList(campInfo, database);
        readFromFile.readCommitteeList(campInfo, database);
        readFromFile.readBlacklist(campInfo, database);

        Camp c = campInfo.getCamp("Orientation");
        PerformanceReport.printReport(c, campInfo);

        clearFiles.clearAttendanceLists();
        clearFiles.clearPasswords();
        clearFiles.clearCampInfoAttributes();
        clearFiles.clearCampInfo();

        writeToBlacklist(campInfo);
        writeToAttendeeList(campInfo);
        writeToCommitteeList(campInfo);
        writeToPasswords(database);
        writeCampInfo(campInfo);
    }
    /*
     * Method to write blacklist information on corresponding csv file
     */
    public static void writeToBlacklist(CampInfo campInfo){
        ArrayList<Camp> listOfCamps = campInfo.getFullList();
        //iterate through all the camps to update the csv file corresponding to blacklist
        for(int i = 0; i < listOfCamps.size(); i++){
            /*
             * Extract blacklist for current camp
             */
            Camp currentCamp = listOfCamps.get(i);
            ArrayList<Student> currentList = currentCamp.getBlacklist().getBlacklist();
            String campName = currentCamp.getCampName();
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/BlackList.csv", true)); 
             
                for (int j = 0;j < currentList.size();j++) {
                    /*
                     * Extract userID to write to csv
                     */
                    String userID = currentList.get(j).getUserID();
                    csvWriter.println(userID + "," + campName + ","); // Write the data in CSV format 
                }   
                csvWriter.close(); 
           } catch (IOException e) { 
               e.printStackTrace(); 
           }
    }
        }
    /*
     * Method to write attendee list information on corresponding csv file
     */
    public static void writeToAttendeeList(CampInfo campInfo){
        ArrayList<Camp> listOfCamps = campInfo.getFullList();
        //iterate through all the camps to update the csv file corresponding to attendee list
        for(int i = 0; i < listOfCamps.size(); i++){
            /*
             * Extract attendee list for current camp
             */
            Camp currentCamp = listOfCamps.get(i);
            ArrayList<Student> currentList = currentCamp.getAttendeeList().getListOfAttendees();
            String campName = currentCamp.getCampName();
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Attendee_List.csv", true)); 
             
                for (int j = 0;j < currentList.size();j++) {
                    /*
                     * Extract userID to write to csv
                     */
                    String userID = currentList.get(j).getUserID();
                    csvWriter.println(userID + "," + campName + ","); // Write the data in CSV format 
                }   
                csvWriter.close(); 
           } catch (IOException e) { 
               e.printStackTrace(); 
           }
    }
        }

    /*
     * Method to write camp committee member list information on corresponding csv file
     */
    public static void writeToCommitteeList(CampInfo campInfo){
        ArrayList<Camp> listOfCamps = campInfo.getFullList();
        //iterate through all the camps to update the csv file corresponding to blacklist
        for(int i = 0; i < listOfCamps.size(); i++){
            /*
             * Extract blacklist for current camp
             */
            Camp currentCamp = listOfCamps.get(i);
            ArrayList<Student> currentList = currentCamp.getCommitteeList().getListOfMembers();
            String campName = currentCamp.getCampName();
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Committee_Member_Camp_List.csv", true)); 
             
                for (int j = 0;j < currentList.size();j++) {
                    /*
                     * Extract userID to write to csv
                     */
                    Student currentMember = currentList.get(j);
                    String userID = currentMember.getUserID();
                    csvWriter.println(userID + "," + campName + "," + currentMember.getCommitteePoints() + ","); 
                    // Write the data in CSV format 
                }   
                csvWriter.close(); 
           } catch (IOException e) { 
               e.printStackTrace(); 
           }
    }
        }

    /*
     * Method to store passwords for users who do not have default password
    */
    public static void writeToPasswords(Database database){
        //iterate through all the users to update the csv file corresponding to password
        ArrayList<User> users = database.getUsers();
        for(int i = 0; i < users.size(); i++){
            String currentUserID = users.get(i).getUserID();
            String password = database.getPassword(i);
            if(!password.equals("password")){
                try { 
                    PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/passwords.csv", true)); 
                
                    csvWriter.println(currentUserID + "," + password + ","); // Write the data in CSV format  
                    csvWriter.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                }
            }
    }
        
    }

    /*
     * Method to store campInfo into csv
     */
    public static void writeCampInfo(CampInfo campInfo){

        ArrayList<Camp> listOfCamps = campInfo.getFullList();
        /*
         * Iterate every camp and input the information into corresponding excel files
         */
        for(int i = 0; i < listOfCamps.size(); i++){
            /*
             * Extract current camp object to reduce unnecessary reference calls to campInfo 
             */
            Camp currentCamp = listOfCamps.get(i);
            try { 
                /*
                 * Initialize Printwriter for both list of camps and camp info attributes csv files
                 */
                PrintWriter campInfoCsvWriter = new PrintWriter(new FileWriter("./data/List_Of_Camp_Info.csv", true)); 
                PrintWriter campInfoAttributesWriter = new PrintWriter(new FileWriter("./data/CampInfo_Attrib.csv", true)); 
                
                /*
                 * Update list of camps csv
                 * First, extract all attributes of camp object
                 */
                String staffID = currentCamp.getStaffID();
                String campName = currentCamp.getCampName();
                int[] dates = currentCamp.getDates();
                int regClosingDate = currentCamp.getRegClosingDate();
                String userGroup = (currentCamp.getUserGroup()).toString();
                String location = currentCamp.getLocation();
                int totalSlots = currentCamp.getTotalSlots();
                int campCommitteeSlots = currentCamp.getCampCommitteeSlots();
                String description = currentCamp.getDescription();
                /*
                 * Write the attributes into a csv file
                 */
                campInfoCsvWriter.println(staffID + "," + campName + "," + dates[0] + "," + dates[1] + "," + regClosingDate + ","
                                        + userGroup + "," + location + "," + totalSlots + "," + campCommitteeSlots + "," + 
                                        description + ",");    
                /*
                 * Update camp info attributes csv
                 * Extract attributes first
                 */
                int attendeeSlotsUsed = campInfo.getAttendeeSlotsUsed(currentCamp);
                int campCommitteeSlotsUsed = campInfo.getCampCommitteeSlotsUsed(currentCamp);
                boolean visibility = campInfo.getVisibility(currentCamp);


                campInfoAttributesWriter.println(campName + "," + attendeeSlotsUsed + "," + campCommitteeSlotsUsed + "," + 
                                                visibility + ",");
                // Close Printwriter
                campInfoCsvWriter.close(); 
                campInfoAttributesWriter.close();
            } catch (IOException e) { 
                    e.printStackTrace(); 
            }
    }
    }

}
