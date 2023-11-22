package camppackage;

import java.util.ArrayList;

import user.Student;
import sort.AlphaSort;

/**
 * Class representing list of attendees
 * Every camp object contains an attendee list
 */
public class AttendeeList{
    private ArrayList<Student> listOfAttendees = new ArrayList<Student>();

    /**
     * Standard get method for list of attendees
     * @return List of attendees
     */
    public ArrayList<Student> getListOfAttendees(){
        return listOfAttendees;
    }

    /**
     * Standard set method to add an attendee to attendee list
     * @param attendee Attendee to be added to list
     */
    public void addAttendee(Student attendee){
        AlphaSort.add(listOfAttendees, attendee);
        
    }


    /**
     * Method to be called when attendee withdraws from camp
     * 
     * @param attendee Attendee to delete from attendee list
     * @return Existence of Student in list
     */
    public boolean deleteAttendee(Student attendee){
        int index = CampUtility.userPos(attendee, listOfAttendees);
        if(index == -1){
            return false;
        }
        listOfAttendees.remove(index);
        return true;
    }

    /**
     * Method to see if student is in a specific camp
     * @param userID User identification of Student to find in attendee list
     * @return Boolean value of whether a student is attending a specific camp
     */
    public boolean findStudent(String userID){
        if(CampUtility.userPos(userID, listOfAttendees) != -1){
            return true;
        }
        return false;
       
    }


    
}