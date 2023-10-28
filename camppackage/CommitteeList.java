package camppackage;

import java.util.ArrayList;
import user.Student;

public class CommitteeList {
    //capacity checking to be dealt with in register class 
    private ArrayList<Student> listOfCCMembers = new ArrayList<Student>();
    //arraylist for point system for performance report to view data

    /*linked with arraylist listOfCCMembers, CC member at index 1 will have their points at index 1 of 
    arraylist points*/
    private ArrayList<Integer> points = new ArrayList<Integer>();
    
    public void addCommitteeMember(Student CommitteeMember){
        // check if camp committee is full
        if(listOfCCMembers.size() >= 10){
            System.out.println("Camp Committee is full! Unable to add new Committee Member!");
            return;
        }
        listOfCCMembers.add(CommitteeMember);
        //All members start off with 0 points
        points.add(0);
    }

    //deletion method not provided as CCMembers cannot quit camp

    /*updatePoint() will add only 1 point, 
    it will be the responsiblity of the enquiry package to disseminate the points*/
    //assumption will be that points cannot be deducted from CCMembers in this program
    //updatePoint() will also have a boolean confirmation that points have been successfully added
    public boolean updatePoint(Student CommitteeMember){
        int index = CampUtility.UserPos(CommitteeMember, listOfCCMembers);
        //terminate method if student does not exist in committee list
        if(index == -1){
            return false;
        }
        //update the points of that particular student
        int currentPoints = points.get(index);
        points.set(index, ++currentPoints);
        return true;
    }
}
