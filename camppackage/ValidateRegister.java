package camppackage;

import clock.Time;
import filehandler.Database;

/**
 * Class responsible for checking the validity of registration to camps
 */
public class ValidateRegister {
    /**
     * Method that checks if a student can register for a camp or not
     * Message will be printed to console if student cannot register for a camp
     * 
     * @param campInfo Database of Camps
     * @param camp Camp being checked
     * @param userID Student's user identification
     * @param database Database of Users
     * @param clock Current date
     * @return Boolean value indicating if a student can be registered to a camp or not
     */
    public static boolean canRegister(CampInfo campInfo, Camp camp, String userID, Database database, Time clock){
        if(ValidateDate.checkRegClosingDate(clock.getDate(), camp.getRegClosingDate())){
            System.out.println("Registration closing date has passed! Unable to process registration!");
            return false;
        }
        
        if(ValidateDate.doDatesCollide(camp.getDates(), userID, database)){
            System.out.println("Collision of dates between camps that user has registered for and the camp they want to register!");
            System.out.println("Unable to process registration!");
            return false;
        };

        if(camp.getBlacklist().findStudent(userID)){
            System.out.println(userID+" has previously withdraw from camp, unable to process re-registration request!");
            return false;
        }

        if(camp.getCommitteeList().findStudent(userID) || camp.getAttendeeList().findStudent(userID)){
            System.out.println("Student has already registered for this camp!");
            return false;
        }

        
        return true;
    }

    /**
     * Method that checks if a student can register for a camp or not
     * Does not print anything to console, method is primarily used for checking when a student is not registering for a camp
     * 
     * @param campInfo Database of Camps
     * @param camp Camp being checked
     * @param userID Student's user identification
     * @param database Database of Users
     * @param clock Current date
     * @return Boolean value indicating if a student can register for a camp or not
     */
    public static boolean canRegisterForCamp(CampInfo campInfo, Camp camp, String userID, Database database, Time clock){
        if(ValidateDate.checkRegClosingDate(clock.getDate(), camp.getRegClosingDate())){
            return false;
        }
        
        if(ValidateDate.doDatesCollide(camp.getDates(), userID, database)){
            return false;
        };
        if(camp.getBlacklist().findStudent(userID)){
            return false;
        }
        if(camp.getCommitteeList().findStudent(userID) || camp.getAttendeeList().findStudent(userID)){
            return false;
        }

        
        return true;
    }
    
}
