package filehandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * The purpose of this class is to empty out the csv file to prepare for writing
 * Clearing out the entire csv and rewriting is more efficient than having to match inputs of csv with inputs from application data
 */
public class clearFiles {
    /*
     * Clear the blacklist, attendee list and committee list
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
}

