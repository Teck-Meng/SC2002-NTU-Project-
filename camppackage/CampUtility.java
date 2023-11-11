package camppackage;

import java.util.ArrayList;
import user.Student;

public class CampUtility {
    /*
     * Checks for existence of camp, if it does exist, return the index position, to be used in CampInfo class
     */
    protected static int CampPos(Camp camp, ArrayList<Camp> listOfCamps){
        for(int i = 0; i < listOfCamps.size(); i++){
            if(listOfCamps.get(i) == camp){
                return i;
            }
        }
        System.out.println("Camp does not exist! Unable to process request!");
        return -1;
    }
    

    /*
     * Check if attendee Slot is full
     */
    protected static boolean isFull(Camp camp, int attendeeSlotsUsed, int committeeSlots){
        if(camp.getTotalSlots() - committeeSlots - attendeeSlotsUsed <= 0){
            return true;
        }
        return false;
    }

    /*
     * For CommitteeList use: To find index of committee member in the list
     * and For AttendeeList use: To find index of attendee in the list
     * and For blackList use: To verify existence of student in blacklist
     */
    protected static int userPos(Student student, ArrayList<Student> list){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == student){
                return i;
            }
        }
        System.out.println("User is not a Committee Member! Unable to process request!");
        return -1;
    }

    /*
     * Student ID version to search for index of a particular student
     */
    protected static int userPos(String userID, ArrayList<Student> list){
        for(int i = 0; i < list.size(); i++){
            if(userID.equals(list.get(i).getUserID())){
                return i;
            }
        }
        System.out.println("User is not a Committee Member! Unable to process request!");
        return -1;
    }

    

}
