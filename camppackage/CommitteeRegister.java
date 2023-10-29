package camppackage;

import java.util.ArrayList;

import user.Student;
import user.User;
import password.Database;
import clock.Time;


public class CommitteeRegister implements CampRegister {
    public boolean registerCamp(CampInfo campInfo, Camp camp, String userID, Database database, Time clock){
        /*
         * Verify if registration closing date has been passed
         * Output of checkRegClosingDate is true if current time has passed registration closing date
         */
        if(ValidateDate.checkRegClosingDate(clock.getDate(), camp.getRegClosingDate())){
            System.out.println("Registration closing date has passed! Unable to process registration!");
            return false;
        }
        /*
         * Verify if camp is full, if yes, terminate registration
         */
        if(isCampFull(campInfo, camp)){
            System.out.println("Unable to process registration due to camp being at full capacity!");
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
        /*
         * Extract user object from database
         */
        User user = database.getUser(userID);
        
        // checks if the user has withdrew from this particular camp as an attendee before
        if(camp.getBlacklist().findStudent(userID)){
            System.out.println(user.getUserID()+" has previously withdraw from camp, unable to process re-registration request!");
            return false;
        }
        /*
         * Update the committee list, Committee slot value and also keeping data of the camp into
         * student's own database
         */
        camp.getCommitteeList().addCommitteeMember((Student)user);
        campInfo.updateAttendeeSlotUsed(true, camp);
        // Adds student into camp while denoting that the student is joining as committee member with boolean param
        ((Student)user).addCamp(camp, true);
        return true;
    }
    
    public ArrayList<Camp> viewCamps(CampInfo campInfo, String userID, Database database){
        User user = database.getUser(userID);
        return campInfo.getCampList(user);
    }

    /*
     * Checks if camp committee is full
     */
    public boolean isCampFull(CampInfo campInfo, Camp camp){
        int committeeSlots = camp.getCampCommitteeSlots();
        int committeeSlotsUsed = campInfo.getCampCommitteeSlotsUsed(camp);
        if(committeeSlots - committeeSlotsUsed <= 0){
            return true;
        }
        return false;
    }
}
