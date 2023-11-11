package camppackage;

import java.util.ArrayList;
import user.Student;
import user.User;
import clock.Time;
import filehandler.Database;

public class AttendeeRegister implements CampRegister {

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
         * Update the attendancelist, attendance slot value and also keeping data of the camp into
         * student's own database
         */
        User toAddUser = database.getUser(userID);

        camp.getAttendeeList().addAttendee((Student)toAddUser);
        campInfo.updateAttendeeSlotsUsed(true, camp, 1);
        // Adds student into camp while denoting that the student is joining as attendee with boolean param
        ((Student)toAddUser).addCamp(camp, false);
        return true;
    }
    
    //gets the camp list that attendee registered, do implement the printing of camp list in user package
    public ArrayList<Camp> viewCamps(CampInfo campInfo, String UserID, Database database){
        User user = database.getUser(UserID);
        return campInfo.getCampList(user);
    }

    public void withdrawCamp(CampInfo campInfo, Camp camp, String userID, Database database){
        Student withdrawee = (Student)database.getUser(userID);
        /*
         * Checks if user's own database of registered camps contains the current camp
         * If successful, procceed to update all data structures of withdrawee's withdrawal
         */ 
        if(withdrawee.withdrawCamp(camp)){
            //Update blacklist to ensure student is unable to re-register for this particular camp
            camp.getBlacklist().addStudent(withdrawee);
            camp.getAttendeeList().deleteAttendee(withdrawee);
            //reduce value of slots used by 1 as student is removed from camp
            campInfo.updateAttendeeSlotsUsed(false, camp, 1);
            System.out.println(withdrawee.getUserID()+" has successfully withdrawn from "+camp.getCampName());
            return;
        }
        System.out.println("Student does not belong to camp! Withdraw unsuccessful!");
        
    }

    /*
     * Checks if camp is full for attendees
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
