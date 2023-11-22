package filehandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * The purpose of this class is to empty out the csv file to prepare for writing
 * </p>
 * <p>
 * Clearing out the entire csv and rewriting is more efficient than having to match inputs of csv with inputs from application data
 * </p>
 */
public class ClearFiles {
    /**
     * Clear the blacklist, attendee list and committee list,
     * to be used before calling corresponding write methods
     */
    public static void clearAttendanceLists(){
        try{
                PrintWriter blacklistCsvWriter = new PrintWriter(new FileWriter("./data/Blacklist.csv", false));
                PrintWriter attendancelistCsvWriter = new PrintWriter(new FileWriter("./data/Attendee_List.csv", false));
                PrintWriter committeeListCsvWriter = new PrintWriter(new FileWriter("./data/Committee_Member_Camp_List.csv", false));
                
                /*
                 * Add relevant headers for each list
                 */
                blacklistCsvWriter.println("userID, campName,");
                attendancelistCsvWriter.println("userID, campName,");
                committeeListCsvWriter.println("userID, campName, points,");

                blacklistCsvWriter.close();
                attendancelistCsvWriter.close();
                committeeListCsvWriter.close();
        } catch (IOException e) { 
               e.printStackTrace(); 
        }
    }
    
    /**
     * Clear passwords csv file, to be used before calling corresponding write methods
     */
    public static void clearPasswords(){
        try{
                PrintWriter passwordCsvWriter = new PrintWriter(new FileWriter("./data/passwords.csv", false));
                
                
                /*
                 * Add relevant headers for passwords.csv
                 */
                passwordCsvWriter.println("userID,password,");
                passwordCsvWriter.close();
        } catch (IOException e) { 
               e.printStackTrace(); 
        }
    }
    
    /**
     * Clear list of camps info csv file, to be used before calling corresponding write methods
     */
    public static void clearCampInfo(){
        try{
                PrintWriter campInfoCsvWriter = new PrintWriter(new FileWriter("./data/List_Of_Camp_Info.csv", false));
                
                
                /*
                 * Add relevant headers for List_Of_Camp_Info.csv
                 */
                campInfoCsvWriter.println("userID, campName, dateStart, dateEnd, regClosingDate, userGroup, location, totalSlots, campCommitteeSlots, description,");
                campInfoCsvWriter.close();
        } catch (IOException e) { 
               e.printStackTrace(); 
        }
    }
    
    /**
     * Clear CampInfo Attributes csv file, to be used before calling corresponding write methods
     */
    public static void clearCampInfoAttributes(){
        try{
                PrintWriter campInfoCsvWriter = new PrintWriter(new FileWriter("./data/CampInfo_Attrib.csv", false));
                
                
                /*
                 * Add relevant headers for CampInfo_Attrib.csv
                 */
                campInfoCsvWriter.println("campName,attendeeSlotsUsed,campCommitteeSlotsUsed,visibility,");
                campInfoCsvWriter.close();
        } catch (IOException e) { 
               e.printStackTrace(); 
        }
    }

    /**
     * Clears attendance report to set up a new attendance report to be written, to only be called by AttendanceReport.java
     */
    public static void clearAttendanceReport(){
        try{
                PrintWriter reportCsvWriter = new PrintWriter(new FileWriter("./data/AttendanceReport.csv", false));
                
                
                /*
                 * Add relevant headers for AttendanceReport.csv
                 */
                reportCsvWriter.println("userID, faculty, campName, role, location, campDescription,");
                reportCsvWriter.close();
        } catch (IOException e) { 
               e.printStackTrace(); 
        }
    }

    /**
     * Clears performance report to set up a new performance report to be written, to only be called by Performance_Report.java
     */
    public static void clearPerformanceReport(){
        try{
                PrintWriter reportCsvWriter = new PrintWriter(new FileWriter("./data/Performance_Report.csv", false));
                
                
                /*
                 * Add relevant headers for clearPerformanceReport.csv
                 */
                reportCsvWriter.println("userID, faculty, campName, role, location, campDescription, points,");
                reportCsvWriter.close();
        } catch (IOException e) { 
               e.printStackTrace(); 
        }
    }
    
    /**
     * Clears enquiries csv
     * To be used before reading enquiries into csv
     */
    public static void clearEnquiries(){
        try{
                PrintWriter reportCsvWriter = new PrintWriter(new FileWriter("./data/Enquiries.csv", false));
                
                
                /*
                 * Add relevant headers for Enquries.csv
                 */
                reportCsvWriter.println("enquiry,enquirer,enquiredCamp,enquiryID,reply,ptrToEnquiry,");
                reportCsvWriter.close();
        } catch (IOException e) { 
               e.printStackTrace(); 
        }
    }

    /**
     * Clears suggestion csv, to be used before reading suggestions into csv
     */
    public static void clearSuggestions(){
        try{
                PrintWriter reportCsvWriter = new PrintWriter(new FileWriter("./data/Suggestions.csv", false));
                
                
                /*
                 * Add relevant headers for Enquries.csv
                 */
                reportCsvWriter.println("suggestion,suggestor,suggestedCamp,suggestionID,isApproved,");
                reportCsvWriter.close();
        } catch (IOException e) { 
               e.printStackTrace(); 
        }
    }
}

