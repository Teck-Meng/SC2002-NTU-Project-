package camppackage;

import java.util.ArrayList;

import user.Student;
import user.User;
import clock.Time;
import filehandler.Database;


public class CommitteeRegister implements CampRegister {
    public boolean registerCamp(CampInfo campInfo, Camp camp, String userID, Database database, Time clock){
        /*
         * Verify if camp is full, if yes, terminate registration
         */
        if(isCampFull(campInfo, camp)){
            System.out.println("Unable to process registration due to camp being at full capacity!");
            return false;
        }
        /*
         * Check if student satisfies all the requirements for registration
         */
        if(!ValidateRegister.canRegister(campInfo, camp, userID, database, clock)){
            return false;
        }
        /*
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
        camp.getCommitteeList().addCommitteeMember((Student)user, campInfo, camp);
        campInfo.updateAttendeeSlotsUsed(true, camp, 1);
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
