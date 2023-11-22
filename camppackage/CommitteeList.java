package camppackage;

import java.util.ArrayList;

import sort.AlphaSort;
import user.Student;

/**
 * <p>
 * Class containing the list of camp committe member for a camp
 * </p>
 * <p>
 * Every camp will contain this object
 * </p>
 */
public class CommitteeList {
    private ArrayList<Student> listOfCCMembers = new ArrayList<Student>();

    /**
     * Method to add camp committee member to list of camp committee member
     * 
     * @param CommitteeMember Camp comnmittee member to be added to list of camp committee member
     * @param campInfo Database of Camps
     * @param camp Camp that the camp committee member is being added to
     */
    public void addCommitteeMember(Student CommitteeMember, CampInfo campInfo, Camp camp){
        int committeeSlots = camp.getCampCommitteeSlots();
        if(listOfCCMembers.size() >= committeeSlots){
            System.out.println("Camp Committee is full! Unable to add new Committee Member!");
            System.out.println("Registration unsuccessful!");
            return;
        }
        AlphaSort.add(listOfCCMembers, CommitteeMember);
    }


    /**
     * Standard get method to get list of camp committee members
     * 
     * @return List of camp committee members
     */
    public ArrayList<Student> getListOfMembers(){
        return listOfCCMembers;
    }

    /**
     * Method that checks if a student is in camp committee list
     * 
     * @param userID User identification of student to search for in camp committee list
     * @return Boolean indication of existence of student in camp committee list
     */
    public boolean findStudent(String userID){
        if(CampUtility.userPos(userID, listOfCCMembers) != -1){
            return true;
        }
        return false;
       
    }
    
}
