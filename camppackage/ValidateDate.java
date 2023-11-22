package camppackage;

import user.Student;
import java.util.ArrayList;

import filehandler.Database;

/**
 * Class responsible for validating the legality of date inputs
 */
public class ValidateDate {
    /**
     * <p>
     * Method that checks if date is valid
     * </p>
     * <p>
     * Ensure date follow DD/MM/YYYY format
     * </p>
     * <p>
     * Minimum of 7 digits when date column is single digit, maximum of 8 digits
     * </p>
     * <p>
     * Assume all months have 31 days
     * </p>
     * @param date Date to check
     * @return Boolean value indicating if a given date is valid or not
     */
    public static boolean isDateValid(int date){
        int duplicate = date;
        if(date<1){
            return false;
        }
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

    /**
     * Method that checks if the dates of a camp collides with the current camps that a student has registered for
     * @param dates Dates of camp that is being checked
     * @param userID User identification of student that is being checked
     * @param database Database of Users
     * @return Boolean value indicating if the dates collide or not
     */
    public static boolean doDatesCollide(int[] dates, String userID, Database database){
        Student student = (Student)database.getUser(userID);
        ArrayList<Camp> studentCamps = student.getListOfCamps();
        Camp committeeCamp = student.getCommitteeCamp();

        int[] compareDatesWith;

        if(committeeCamp != null){
            if(checkDates(dates, committeeCamp.getDates()) == true){
                return true;
            }
        }

        for(int i = 0; i < studentCamps.size(); i++){
            compareDatesWith = studentCamps.get(i).getDates();
            if(checkDates(dates, compareDatesWith) == true){
                return true;
            } 
        }
        return false;
    }

    
    /**
     * Computes if dates collide in doDatesCollide() for a pair of dates
     * 
     * @param date1 First set of dates
     * @param date2 Second set of dates
     * @return boolean value indicating if dates collide or not
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

    /**
     * Method that checks if a set of dates(date1) is earlier than another set of dates(date2)
     * @param date1 First set of dates
     * @param date2 Second set of dates
     * @return Boolean value indicating if date1 is earlier than date2
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

    
    /**
     * <p>
     * Verifies if the day section of the date is valid
     * </p>
     * <p>
     * Assumption is that day ranges from 1-31 regardless of month
     * </p>
     * @param date Date to be checked
     * @return Boolean value indicating if the day section of date is valid
     */
    private static boolean checkDay(int date){
        date /= 1_000_000; 
        if(date < 1||date > 31){
            return false;
        }
        return true;
    }

    /**
     * Verifies if the month section of the date is valid
     * 
     * @param date Date to be checked
     * @return Boolean value indicating if the month section of date is valid
     */
    private static boolean checkMonth(int date){

        int month = (date / 10_000) % 100;
        if(month < 1||month > 12){
            return false;
        }
        return true;
    }

    /**
     * Method that checks if registration closing date has passed
     * @param currentTime Current time
     * @param regClosingDate Registration closing date
     * @return Boolean value indicating if registration closing date has passed
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
        if(currentTime > regClosingDate){
            return true;
        }
        else if(currentTime < regClosingDate){
            return false;
        }
        return true;
    }

    /**
     * Method that checks if a given date is within range of a pair of dates
     * 
     * @param date Date that is being checked
     * @param campDates Pair of dates 
     * @return Boolean value if a given date is in range set by pair of dates
     */
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
