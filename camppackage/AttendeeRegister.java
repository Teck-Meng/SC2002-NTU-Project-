package camppackage;

import java.util.ArrayList;
import user.Student;
import user.User;
import clock.Time;
import filehandler.Database;

/**
 * <p>
 * Implements CampRegister interface
 * </p>
 * <p>
 * Class is responsiple for handling registration of attendees into a camp
 * </p>
 */
public class AttendeeRegister implements CampRegister {

    /**
     * Method to register an attendee to a camp
     * 
     * @param campInfo Database of Camps
     * @param camp Camp to add the attendee to
     * @param userID User identification of attendee to be registered
     * @param database Database of Users
     * @param clock Current date
     * @return Boolean value indicating success of registration
     * @Override
     */
    public boolean registerCamp(CampInfo campInfo, Camp camp, String userID, Database database, Time clock){
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
        User toAddUser = database.getUser(userID);

        camp.getAttendeeList().addAttendee((Student)toAddUser);
        campInfo.updateAttendeeSlotsUsed(true, camp, 1);
        ((Student)toAddUser).addCamp(camp, false);
        return true;
    }
    
    /**
     * Method to allow stude to fetch all the camps they are registered for
     * 
     * @param campInfo Database of Camps
     * @param userID User identification of attendee
     * @param databse Database of Users
     * @return List of camps registered by attendee
     * @Override
     */
    public ArrayList<Camp> viewCamps(CampInfo campInfo, String userID, Database database){
        User user = database.getUser(userID);
        return campInfo.getCampList(user);
    }

    /**
     * Method for attendee to withdraw from a camp
     * 
     * @param campInfo Database of Camps
     * @param camp Camp that attendee wishes to withdraw from
     * @param userID User identification of attendee trying to withdraw from camp
     * @param database Database of Users
     */
    public static void withdrawCamp(CampInfo campInfo, Camp camp, String userID, Database database){
        Student withdrawee = (Student)database.getUser(userID);
        if(withdrawee.withdrawCamp(camp)){
            camp.getBlacklist().addStudent(withdrawee);
            camp.getAttendeeList().deleteAttendee(withdrawee);
            campInfo.updateAttendeeSlotsUsed(false, camp, 1);
            System.out.println(withdrawee.getUserID()+" has successfully withdrawn from "+camp.getCampName());
            return;
        }
        System.out.println("Student does not belong to camp! Withdraw unsuccessful!");
        
    }

    /**
     * Method to check if a camp is full
     * 
     * @param campInfo Database of Camps
     * @param camp The camp that is being checked for vacancy
     * @return Boolean value indicating if camp is full or not
     * @Override
     */
    public boolean isCampFull(CampInfo campInfo, Camp camp){
        int total = camp.getTotalSlots();
        int committeeSlots = camp.getCampCommitteeSlots();
        int attendeeSlotsUsed = campInfo.getAttendeeSlotsUsed(camp);
        if(total - committeeSlots - attendeeSlotsUsed <= 0){
            return true;
        }
        return false;
    }
}
