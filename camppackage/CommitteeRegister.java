package camppackage;

import java.util.ArrayList;

import user.Student;
import user.User;
import clock.Time;
import filehandler.Database;

/**
 * Class responsible for registration of camp committee member to a camp, implements CampRegister interface
 */
public class CommitteeRegister implements CampRegister {
    /**
     * Method that registers a student to a camp as camp committee member
     * 
     * @param campInfo Database of Camps
     * @param camp Camp that student is registering for
     * @param userID User identification of student that is registering for camp
     * @param database Database of Users
     * @param clock Current date
     * @return Boolean value indicating if registration is successful or not
     * @Override
     */
    public boolean registerCamp(CampInfo campInfo, Camp camp, String userID, Database database, Time clock){
        if(isCampFull(campInfo, camp)){
            System.out.println("Unable to process registration due to camp being at full capacity!");
            return false;
        }
        if(!ValidateRegister.canRegister(campInfo, camp, userID, database, clock)){
            return false;
        }
        User user = database.getUser(userID);
        
        if(camp.getBlacklist().findStudent(userID)){
            System.out.println(user.getUserID()+" has previously withdraw from camp, unable to process re-registration request!");
            return false;
        }
        camp.getCommitteeList().addCommitteeMember((Student)user, campInfo, camp);
        campInfo.updateAttendeeSlotsUsed(true, camp, 1);
        ((Student)user).addCamp(camp, true);
        return true;
    }
    
    /**
     * Method to view list of camps
     * 
     * @param campInfo Database of camps
     * @param userID User identification of user trying to view camps
     * @param database Database of Users
     * @return List of Camps visible to user
     * @Override
     */
    public ArrayList<Camp> viewCamps(CampInfo campInfo, String userID, Database database){
        User user = database.getUser(userID);
        return campInfo.getCampList(user);
    }

    /**
     * Method to check if camp committee slots is full
     * @param campInfo Database of Camps
     * @param camp Specific camp that is being checked
     * @return Boolean value indicating if a camp is full or not
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
