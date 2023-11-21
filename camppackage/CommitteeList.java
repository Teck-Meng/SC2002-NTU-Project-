package camppackage;

import java.util.ArrayList;
import java.util.Collection;

import sort.AlphaSort;
import user.Student;

public class CommitteeList {
    //capacity checking to be dealt with in register class 
    private ArrayList<Student> listOfCCMembers = new ArrayList<Student>();
    //arraylist for point system for performance report to view data

    
    public void addCommitteeMember(Student CommitteeMember, CampInfo campInfo, Camp camp){
        int committeeSlots = camp.getCampCommitteeSlots();
        // check if camp committee is full
        if(listOfCCMembers.size() >= committeeSlots){
            System.out.println("Camp Committee is full! Unable to add new Committee Member!");
            System.out.println("Registration unsuccessful!");
            return;
        }
        AlphaSort.add(listOfCCMembers, CommitteeMember);
    }

    //deletion method not provided as CCMembers cannot quit camp


    public ArrayList<Student> getListOfMembers(){
        return listOfCCMembers;
    }

    public boolean findStudent(String userID){
        // Use userPos to verify existence of student in blacklist
        if(CampUtility.userPos(userID, listOfCCMembers) != -1){
            return true;
        }
        return false;
       
    }
    
}
