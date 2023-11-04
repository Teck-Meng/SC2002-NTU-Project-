package camppackage;

import user.Student;
import java.util.ArrayList;

// to keep track of students who already withdraw and not allow them to register for a particular camp again
public class Blacklist {
    ArrayList<Student> blacklist = new ArrayList<Student>();

    public void addStudent(Student withdrawee){
        blacklist.add(withdrawee);
    }

    public ArrayList<Student> getBlacklist(){
        return blacklist;
    }

    public boolean findStudent(String userID){
        // Use userPos to verify existence of student in blacklist
        if(CampUtility.UserPos(userID, blacklist)!=-1){
            return true;
        }
        return false;
       
    }

    public void print(){
        for(int i = 0; i < blacklist.size(); i++){
            System.out.println(blacklist.get(i).getUserID());
        }
    }
    // deleteStudent method not implemented as students who withdraw are assumed to be permanently blacklisted
}
