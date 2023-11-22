package user;
import java.util.ArrayList;
import camppackage.Camp;

/**
 * Class representing a student user
 * Student will have access to Student UI
 * Subclass of User
 */
public class Student extends User{
    private ArrayList<Camp> myCamps = new ArrayList<Camp>();
    /*
     * If committeeCamp is null, Student did not register for camp committee
     */
    private Camp committeeCamp;
    /*
     * CommitteePoints will only be accessible and usable when student become committee member
     */
    private int committeePoints;

    /**
     * Standard constructor for Student
     * 
     * @param UserID User identification
     * @param facultyInfo Faculty of a student
     */
    public Student(String UserID, Faculty facultyInfo){
        super(UserID, facultyInfo);
        committeeCamp = null;
        committeePoints = 0;
    }

    /**
     * 
     * @return List of attendee Camps
     */
    public ArrayList<Camp> getListOfCamps(){
        return myCamps;
    }

    /**
     * 
     * @return List of committee Camps
     */
    public Camp getCommitteeCamp(){
        return committeeCamp;
    }

    /**
     * If student is not a committee member, return -1
     * 
     * @return number of committee points of a committee member
     */
    public int getCommitteePoints(){
        if(committeeCamp == null){
            System.out.println("Student is not a camp committee member!");
            return -1;
        }
        return committeePoints;
    }

    /**
     * Add points to committee member
     * Method checks if student is committee member before awarding points
     * Return false if student is not a committee member
     * 
     * @param points Number of points to award Committee Member
     * @return Boolean value of whether a student is a committee member
     */
    public boolean addCommitteePoints(int points){
        if(committeeCamp == null){
            System.out.println("Student is not a camp committee member!");
            return false;
        }
        committeePoints += points;
        return true;
    }

    /**
     * Update student's own database of newly added camp
     * 
     * @param camp Newly registered Camp
     * @param isJoiningCommitteeMem Indication of whether a Student is joining camp as attendee or committee member
     */
    public void addCamp(Camp camp, boolean isJoiningCommitteeMem){
        if(isJoiningCommitteeMem==false){
            myCamps.add(camp);
        }
        else if(!hasCommitteePos()){
            committeeCamp = camp;
        }
    }

    /**
     * Method to withdraw from camp
     * Return true if withdrawal is successful
     * 
     * @param camp Camp that a student wishes to withdraw from
     * @return Success of withdrawal
     */
    public boolean withdrawCamp(Camp camp){
        int initialSize = myCamps.size();
        myCamps.remove(camp);
        if(initialSize!=myCamps.size()){
            return true;
        }
        return false;
    }


    /**
     * 
     * @return Whether a student is a committee member or not
     */
    public boolean hasCommitteePos(){
        if(committeeCamp == null){
            return false;
        }
        return true;
    }

}
