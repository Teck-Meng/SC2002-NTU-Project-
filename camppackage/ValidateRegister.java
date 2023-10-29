package camppackage;

import clock.Time;
import password.Database;

public class ValidateRegister {
    public static boolean canRegister(CampInfo campInfo, Camp camp, String userID, Database database, Time clock){
        /*
         * Verify if registration closing date has been passed
         * Output of checkRegClosingDate is true if current time has passed registration closing date
         */
        if(ValidateDate.checkRegClosingDate(clock.getDate(), camp.getRegClosingDate())){
            System.out.println("Registration closing date has passed! Unable to process registration!");
            return false;
        }
        
        /*
         * Check if there are any collision of dates
         * If yes, terminate registration
         */
        if(ValidateDate.doDatesCollide(camp.getDates(), userID, database)){
            System.out.println("Collision of dates between camps that user has registered for and the camp they want to register!");
            System.out.println("Unable to process registration!");
            return false;
        };
        // checks if the user has withdrew from this particular camp
        if(camp.getBlacklist().findStudent(userID)){
            System.out.println(userID+" has previously withdraw from camp, unable to process re-registration request!");
            return false;
        }
        return true;
    }
}
