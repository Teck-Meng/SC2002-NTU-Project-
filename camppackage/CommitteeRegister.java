package camppackage;

import java.util.ArrayList;

import user.Student;
import user.User;


public class CommitteeRegister implements CampRegister {
    public boolean registerCamp(CampInfo campInfo, Camp camp, User user){
        // checks if user is of student class
        if(!(user instanceof Student)){
            System.out.println(user.getUserID()+" is not a student, unable to process registration!");
            return false;
        }
        // checks if the user has withdrew from this particular camp as an attendee before
        if(camp.getBlacklist().findStudent((Student)user)){
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
    
    public ArrayList<Camp> viewCamps(CampInfo campInfo, User user){
        return campInfo.getCampList(user);
    }
}
