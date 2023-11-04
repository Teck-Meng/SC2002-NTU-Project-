package camppackage;

import java.util.ArrayList;
import user.Student;

public class CommitteeList {
    //capacity checking to be dealt with in register class 
    private ArrayList<Student> listOfCCMembers = new ArrayList<Student>();
    //arraylist for point system for performance report to view data

    
    public void addCommitteeMember(Student CommitteeMember){
        // check if camp committee is full
        if(listOfCCMembers.size() >= 10){
            System.out.println("Camp Committee is full! Unable to add new Committee Member!");
            return;
        }
        listOfCCMembers.add(CommitteeMember);
    }

    //deletion method not provided as CCMembers cannot quit camp

    public void print(){
        for(int i = 0;i<listOfCCMembers.size();i++){
            System.out.println(listOfCCMembers.get(i).getUserID());
        }
    }
    
}
