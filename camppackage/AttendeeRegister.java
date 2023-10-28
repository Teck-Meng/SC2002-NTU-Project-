package camppackage;

import java.util.ArrayList;
import user.Student;
import user.User;

public class AttendeeRegister implements CampRegister {

    public boolean registerCamp(CampInfo campInfo, Camp camp, User user){
        // checks if user is of student class
        if(!(user instanceof Student)){
            System.out.println(user.getUserID()+" is not a student, unable to process registration!");
            return false;
        }
        // checks if the user has withdrew from this particular camp
        if(camp.getBlacklist().findStudent((Student)user)){
            System.out.println(user.getUserID()+" has previously withdraw from camp, unable to process re-registration request!");
            return false;
        }
        /*
         * Update the attendancelist, attendance slot value and also keeping data of the camp into
         * student's own database
         */
        camp.getAttendeeList().addAttendee((Student)user);
        campInfo.updateAttendeeSlotUsed(true, camp);
        // Adds student into camp while denoting that the student is joining as attendee with boolean param
        ((Student)user).addCamp(camp, false);
        return true;
    }
    
    //gets the camp list that attendee registered, do implement the printing of camp list in user package
    public ArrayList<Camp> viewCamps(CampInfo campInfo, User user){
        return campInfo.getCampList(user);
    }

    public void withdrawCamp(CampInfo campInfo, Camp camp, Student withdrawee){
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
}
