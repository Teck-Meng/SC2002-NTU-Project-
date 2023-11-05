package filehandler;

import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;


import camppackage.Camp;
import user.Faculty;
import user.Student;
import user.User;
import camppackage.CampInfo;


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

        clearFiles.clearAttendanceLists();
        clearFiles.clearPasswords();

        writeToBlacklist(campInfo);
        writeToAttendeeList(campInfo);
        writeToCommitteeList(campInfo);
        writeToPasswords(database);
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
        //iterate through all the camps to update the csv file corresponding to password
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

    public static void writeCampInfo(CampInfo campInfo){

    }




    public static void writeToUserList(){
        try { 
            PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/test.csv", true)); // "results.csv" is the name of the CSV file 
               //csvWriter.println("No. of vertices,TimeTaken"); // Write the header 
            //edit here to change graph size 
             
            for (int i=0;i<10;i++) 
            {    
             
              
             System.out.println("Execution Time: "+ 5); 
             csvWriter.println(i + "," + 2); // Write the data in CSV format 
            }   
            csvWriter.close(); 
           } catch (IOException e) { 
               e.printStackTrace(); 
           }
    }
}
