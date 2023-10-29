package user;
import java.util.ArrayList;
import camppackage.Camp;

public class Student extends User{
    private ArrayList<Camp> myCamps = new ArrayList<Camp>();
    private Camp CommitteeCamp;

    public Student(String UserID, Faculty facultyInfo){
        super(UserID, facultyInfo);
        CommitteeCamp = null;
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
        return CommitteeCamp;
    }


    public void addCamp(Camp camp, boolean isJoiningCommitteeMem){
        // update student's database to save the camp he has registered
        if(isJoiningCommitteeMem==false){
            myCamps.add(camp);
        }
        else if(!hasCommitteePos()){
            CommitteeCamp = camp;
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
        if(CommitteeCamp == null){
            return false;
        }
        return true;
    }

}
