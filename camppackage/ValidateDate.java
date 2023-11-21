package camppackage;

import user.Student;
import java.util.ArrayList;

import filehandler.Database;

/*
 * To validate dates and also to check dates with student's collection
 */
public class ValidateDate {
    public static void main(String[] args){
        System.out.println(isDateValid(20042024));
    }
    /*
     * Ensure date follow DD/MM/YYYY format
     * Minimum of 7 digits when date column is single digit
     * Maximum of 8 digits
     * Assume all months have 31 days
     */
    public static boolean isDateValid(int date){
        int duplicate = date;
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
        while(duplicate != 0){
            duplicate /= 10;
            count++;
        }
        if(count==7||count==8){
            return (checkDay(date) && checkMonth(date));
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
        Camp committeeCamp = student.getCommitteeCamp();
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
        if(isDateSmaller(date2[1], date1[0])){
            return false;
        }
        else if(isDateSmaller(date1[1] , date2[0])){
            return false;
        }

        return true;
    }

    /*
     * Returns true if date1 is earlier than date2
     * Otherwise return false
     */
    public static boolean isDateSmaller(int date1, int date2){
        int date1Year = date1 % 10000;
        int date2Year = date2 % 10000;
        date1 /= 10000;
        date2 /= 10000;
        if(date1Year > date2Year){
            return false;
        }
        else if(date1Year < date2Year){
            return true;
        }

        int date1Month = date1 % 100;
        int date2Month = date2 % 100;
        date1 /= 100;
        date2 /= 100;
        if(date1Month > date2Month){
            return false;
        }
        else if(date1Month < date2Month){
            return true;
        }
        if(date1 < date2){
            return true;
        }
        return false;
    }

    

    /*
     * Check if the value for day in the date is between 01 to 31
     * Assume all months have 31 days
     * To be used by isDateValid
     * 20042024
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

        int month = (date / 10_000) % 100;
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
        int currentTimeYear = currentTime % 10000;
        int regClosingDateYear = regClosingDate % 10000;
        if(currentTimeYear > regClosingDateYear){
            return true;
        }
        else if(currentTimeYear < regClosingDateYear){
            return false;
        }
        currentTime /= 10000;
        regClosingDate /= 10000;
        int currentTimeMonth = currentTime % 100;
        int regClosingDateMonth = regClosingDate % 100;
        if(currentTimeMonth > regClosingDateMonth){
            return true;
        }
        else if(currentTimeMonth < regClosingDateMonth){
            return false;
        }

        currentTime /= 100;
        regClosingDate /= 100;
        /*
         * compare days
         */
        if(currentTime > regClosingDate){
            return true;
        }
        else if(currentTime < regClosingDate){
            return false;
        }
        return true;
    }

    public static boolean withinRange(int date, int[] campDates){
        if(date == campDates[0] || date == campDates[1]){
            return true;
        }

        if(isDateSmaller(campDates[0], date) && isDateSmaller(date, campDates[1])){
            return true;
        }
        return false;
    }
}
