package camppackage;

import java.util.ArrayList;

import user.Student;
import sort.AlphaSort;

public class AttendeeList{
    private ArrayList<Student> listOfAttendees = new ArrayList<Student>();

    //default empty constructor to be used as all attributes starts off empty 
    
    public ArrayList<Student> getListOfAttendees(){
        return listOfAttendees;
    }

    //addAttendee() will be called when student registers for camp
    public void addAttendee(Student attendee){
        AlphaSort.add(listOfAttendees, attendee);
        
    }

    //deleteAttendee() to be called when student withdraws camp
    //return true if attendee exist and deletion is successful
    //else return false
    public boolean deleteAttendee(Student attendee){
        int index = CampUtility.userPos(attendee, listOfAttendees);
        //terminate method if student does not exist in committee list
        if(index == -1){
            return false;
        }
        //successful removal of attendee
        listOfAttendees.remove(index);
        return true;
    }

    public boolean findStudent(String userID){
        // Use userPos to verify existence of student in blacklist
        if(CampUtility.userPos(userID, listOfAttendees) != -1){
            return true;
        }
        return false;
       
    }


    
}