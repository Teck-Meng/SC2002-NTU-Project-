package filehandler;

import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter;

/*
 * Class will contain all methods to write to csv
 * csv file name will be passed as @param_type String
 */
public class writeToFile {
    public static void main(String[] args){
        writeToUserList();
    }
    /*
     * Method to write 
     */
    public static void writeToUserList(){
        try { 
            PrintWriter csvWriter = new PrintWriter(new FileWriter("./data/Performance_Report.csv", true)); // "results.csv" is the name of the CSV file 
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
