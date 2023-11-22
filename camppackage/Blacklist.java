package camppackage;

import user.Student;
import java.util.ArrayList;

/**
 * <p>
 * A list containing students who have withdrawn from a specfic camp
 * </p>
 * <p>
 * This list is used to ensure that students who have withdrawn are unable to re-register for that specific camp
 * </p>
 * <p>
 * Each camp will have one blacklist
 * </p>
 */
public class Blacklist {
    private ArrayList<Student> blacklist = new ArrayList<Student>();

    /**
     * Add Student to blacklist after they have withdraw from camp
     * 
     * @param withdrawee Student object of attendee who have withdrawn from camp
     */
    public void addStudent(Student withdrawee){
        blacklist.add(withdrawee);
    }

    /**
     * Standard get method for blacklist
     * 
     * @return Blacklist
     */
    public ArrayList<Student> getBlacklist(){
        return blacklist;
    }

    /**
     * Method to check if a student is in a blacklist
     * 
     * @param userID User identification of student that is being searched for in the blacklist
     * @return Existence of student in blacklist
     */
    public boolean findStudent(String userID){
        // Use userPos to verify existence of student in blacklist
        if(CampUtility.userPos(userID, blacklist)!=-1){
            return true;
        }
        return false;
       
    }

    
}
