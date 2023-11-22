package filehandler;

import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;


import camppackage.Camp;
import user.Student;

import user.User;
import camppackage.CampInfo;
import enquiry.ListOfEnquiries;
import enquiry.ListOfSuggestions;
import enquiry.ReplyToStudent;


/*
 * Class will contain all methods to write to csv
 * csv file name will be passed as @param_type String
 */
public class WriteToFile {
    /**
     * Method to write blacklist information on corresponding csv file
     * 
     * @param campInfo Database of Camps
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
    
    /**
     * Method to write attendee list information on corresponding csv file
     * 
     * @param campInfo Database of Camps
     */
    public static void writeToAttendeeList(CampInfo campInfo){
        ArrayList<Camp> listOfCamps = campInfo.getFullList();
        for(int i = 0; i < listOfCamps.size(); i++){
            Camp currentCamp = listOfCamps.get(i);
            ArrayList<Student> currentList = currentCamp.getAttendeeList().getListOfAttendees();
            String campName = currentCamp.getCampName();
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Attendee_List.csv", true)); 
             
                for (int j = 0;j < currentList.size();j++) {
                    String userID = currentList.get(j).getUserID();
                    csvWriter.println(userID + "," + campName + ","); // Write the data in CSV format 
                }   
                csvWriter.close(); 
           } catch (IOException e) { 
               e.printStackTrace(); 
           }
    }
        }

    /**
     * Method to write camp committee member list information on corresponding csv file
     * 
     * @param campInfo Database of Camps
     */
    public static void writeToCommitteeList(CampInfo campInfo){
        ArrayList<Camp> listOfCamps = campInfo.getFullList();
        for(int i = 0; i < listOfCamps.size(); i++){
            Camp currentCamp = listOfCamps.get(i);
            ArrayList<Student> currentList = currentCamp.getCommitteeList().getListOfMembers();
            String campName = currentCamp.getCampName();
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Committee_Member_Camp_List.csv", true)); 
             
                for (int j = 0;j < currentList.size();j++) {
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

    /**
     * Method to store passwords for users who do not have default password
     * 
     * @param database Database of Users
     */
    public static void writeToPasswords(Database database){
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

    /**
     * Method to store campInfo into csv
     * 
     * @param campInfo Database of Camps
     */
    public static void writeCampInfo(CampInfo campInfo){

        ArrayList<Camp> listOfCamps = campInfo.getFullList();
        /*
         * Iterate every camp and input the information into corresponding excel files
         */
        for(int i = 0; i < listOfCamps.size(); i++){
            Camp currentCamp = listOfCamps.get(i);
            try { 
                PrintWriter campInfoCsvWriter = new PrintWriter(new FileWriter("./data/List_Of_Camp_Info.csv", true)); 
                PrintWriter campInfoAttributesWriter = new PrintWriter(new FileWriter("./data/CampInfo_Attrib.csv", true)); 
                
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
                
                int attendeeSlotsUsed = campInfo.getAttendeeSlotsUsed(currentCamp);
                int campCommitteeSlotsUsed = campInfo.getCampCommitteeSlotsUsed(currentCamp);
                boolean visibility = campInfo.getVisibility(currentCamp);


                campInfoAttributesWriter.println(campName + "," + attendeeSlotsUsed + "," + campCommitteeSlotsUsed + "," + 
                                                visibility + ",");
                campInfoCsvWriter.close(); 
                campInfoAttributesWriter.close();
            } catch (IOException e) { 
                    e.printStackTrace(); 
            }
    }
    }
    /**
     * Method to store enquiries and corresponding replies into csv
     * 
     * @param enquiries List of Enquiries
     * @param replies List of Replies
     */
    public static void writeToEnquiries(ListOfEnquiries enquiries, ReplyToStudent replies){
        ArrayList<String> listOfEnquiries = enquiries.getEnquiries();
            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Enquiries.csv", true)); 
                
                for(int i = 0; i < listOfEnquiries.size(); i++){
                    String enquiry = enquiries.getEnquiry(i);
                    String userID = enquiries.getUserID(i);
                    String campName = enquiries.getCampEnquiredID(i);
                    int enquiryID = enquiries.getEnquiryID(i);

                    if(enquiries.isEnquiryAnswered(i)){
                        String reply = replies.getReplyFromPtr(enquiryID);
                        csvWriter.println(enquiry + "," + userID + "," + campName + "," + enquiryID + "," + reply + "," +
                                            enquiryID + ",");
                    }
                    else{
                        csvWriter.println(enquiry + "," + userID + "," + campName + "," + enquiryID + "," + "NaN" + "," +
                                            enquiryID + ",");
                    }
                }
                csvWriter.close(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            }
        
    }

    /**
     * Method to store suggestions into csv
     * 
     * @param suggestions List of suggestions
     */
    public static void writeToSuggestion(ListOfSuggestions suggestions){
        ArrayList<String> listOfSuggestions = suggestions.getListOfSuggestions();
        


            try { 
                PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Suggestions.csv", true)); 
                
                for(int i = 0; i < listOfSuggestions.size(); i++){
                    String suggestion = suggestions.getSuggestion(i);
                    String userID = suggestions.getUserID(i);
                    String campName = suggestions.getCampEnquiredID(i);
                    int suggestionID = suggestions.getSuggestionID(i);
                    

                    if(suggestions.isItApproved(i)){
                        csvWriter.println(suggestion + "," + userID + "," + campName + "," + suggestionID + "," + "yes" + ",");
                    }
                    else{
                        csvWriter.println(suggestion + "," + userID + "," + campName + "," + suggestionID + "," + "no" + ",");
                    }
                }
                csvWriter.close(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            }
        
    }
}
