package camppackage;

import java.util.ArrayList;
import user.Student;
import user.User;
import password.Database;
import clock.Time;

public class AttendeeRegister implements CampRegister {

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
        // checks if the user has withdrew from this particular camp
        if(camp.getBlacklist().findStudent(userID)){
            System.out.println(userID+" has previously withdraw from camp, unable to process re-registration request!");
            return false;
        }
        /*
         * Update the attendancelist, attendance slot value and also keeping data of the camp into
         * student's own database
         */
        User toAddUser = database.getUser(userID);

        camp.getAttendeeList().addAttendee((Student)toAddUser);
        campInfo.updateAttendeeSlotUsed(true, camp);
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
            campInfo.updateAttendeeSlotUsed(false, camp);
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
        int attendeeSlotsUsed = campInfo.getAttendeeSlotUsed(camp);
        if(total - committeeSlots - attendeeSlotsUsed <= 0){
            return true;
        }
        return false;
    }
}
