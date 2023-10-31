package user;
import java.util.ArrayList;
import camppackage.Camp;

public class Student extends User{
    private ArrayList<Camp> myCamps = new ArrayList<Camp>();
    private Camp committeeCamp;
    /*
     * CommitteePoints will only be accessible and usable when student become committee member
     */
    private int committeePoints;

    public Student(String UserID, Faculty facultyInfo){
        super(UserID, facultyInfo);
        committeeCamp = null;
        /*
         * Set all intitial points to 0
         */
        committeePoints = 0;
    }

    public ArrayList<Camp> getListOfCamps(){
        /*
         * For date validation
         */
        return myCamps;
    }

    public Camp getCommitteCamp(){
        /*
         * For date validation
         */
        return committeeCamp;
    }

    /*
     * Return number of points for camp committee
     * Return -1 if user is not in camp committee
     * System will print warning statement if user is not in camp committee
     */
    public int getCommitteePoints(){
        if(committeeCamp == null){
            System.out.println("Student is not a camp committee member!");
            return -1;
        }
        return committeePoints;
    }

    /*
     * Every call will check if student is camp committee
     * Return false if student is not in camp committee
     * Return true and add 1 point if student is in camp committee
     * To be used by staff class
     */
    protected boolean addCommitteePoints(){
        if(committeeCamp == null){
            System.out.println("Student is not a camp committee member!");
            return false;
        }
        committeePoints++;
        return true;
    }


    public void addCamp(Camp camp, boolean isJoiningCommitteeMem){
        // update student's database to save the camp he has registered
        if(isJoiningCommitteeMem==false){
            myCamps.add(camp);
        }
        else if(!hasCommitteePos()){
            committeeCamp = camp;
        }
    }

    //returns true if withdraw successful
    public boolean withdrawCamp(Camp camp){
        int initialSize = myCamps.size();
        myCamps.remove(camp);
        if(initialSize!=myCamps.size()){
            return true;
        }
        return false;
    }

    public boolean hasCommitteePos(){
        if(committeeCamp == null){
            return false;
        }
        return true;
    }

}
