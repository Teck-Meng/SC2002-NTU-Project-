package camppackage;

import user.Student;
import java.util.ArrayList;

import filehandler.Database;

/*
 * To validate dates and also to check dates with student's collection
 */
public class ValidateDate {
    /*
     * Ensure date follow DD/MM/YYYY format
     * Minimum of 7 digits when date column is single digit
     * Maximum of 8 digits
     * Assume all months have 31 days
     */
    public static boolean isDateValid(int date){
        /*
         * Check for negative values
         */
        if(date<1){
            return false;
        }
        /*
         * Check for 7-8 digits
         */
        int count = 0;
        while(date != 0){
            date /= 10;
            count++;
        }
        if(count==7||count==8){
            return (checkDay(date)&&checkMonth(date));
        }
        return false;
    }

    /*
     * 
     * parameter dates is to be size 2
     * return true if dates collide
     * To be used by classes AttendeeRegister and CommitteeRegister for date validation
     */
    public static boolean doDatesCollide(int[] dates, String userID, Database database){
        /*
         * Extract student object from database
         */
        Student student = (Student)database.getUser(userID);
        ArrayList<Camp> studentCamps = student.getListOfCamps();
        Camp committeeCamp = student.getCommitteCamp();
        /*
         * Contains current dates array used for checking
         */
        int[] compareDatesWith;

        /*
         * Check the camp date for the camp that the student is a committee of
         */
        if(committeeCamp != null){
            if(checkDates(dates, committeeCamp.getDates()) == true){
                return true;
            }
        }

        /*
         * Check the list of camp dates that the student is an attendee of
         */
        for(int i = 0; i < studentCamps.size(); i++){
            compareDatesWith = studentCamps.get(i).getDates();
            if(checkDates(dates, compareDatesWith) == true){
                return true;
            } 
        }
        return false;
    }

    

    /*
     * Compare 2 dates and check for collision, to be used by doDatesCollide
     */
    private static boolean checkDates(int[] date1, int[] date2){
        if(date1[0] > date2[1]){
            return false;
        }
        else if(date1[1] < date2[0]){
            return false;
        }

        return true;
    }

    

    /*
     * Check if the value for day in the date is between 01 to 31
     * Assume all months have 31 days
     * To be used by isDateValid
     */
    private static boolean checkDay(int date){
        date /= 1_000_000; //divide by 1 million to retrieve value of DD
        if(date < 1||date > 31){
            return false;
        }
        return true;
    }

    /*
     * Check if the value of the month is between 1 to 12
     */
    private static boolean checkMonth(int date){
        int day = date / 1_000_000; //divide by 1 million to retrieve value of DD

        int month = date / 10_000 - day;

        if(month < 1||month > 12){
            return false;
        }
        return true;
    }

    /*
     * Checks if registration closing date has been passed
     * returns true if registration closing date has passed
     */
    public static boolean checkRegClosingDate(int currentTime, int regClosingDate){
        return !(regClosingDate > currentTime);
    }
}
