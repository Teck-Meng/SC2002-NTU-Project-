package camppackage;

import java.util.ArrayList;
import user.Student;

/**
 * Class responsible for computation of camp numerical attributes within camppackage
 */
public class CampUtility {
    /**
     * Method that searches for index position of a camp in list of camps
     * @param camp Camp that is being searched for
     * @param listOfCamps Database of Camps
     * @return Index position of camp if it exist in list, otherwise, return -1
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
    

    /**
     * Method that checks if attendee slots is full
     * 
     * @param camp Camp that is being checked
     * @param attendeeSlotsUsed Attendee slots used for camp
     * @param committeeSlots Committee slots of a camp
     * @return Boolean value indicating if a camp is full or not
     */
    protected static boolean isFull(Camp camp, int attendeeSlotsUsed, int committeeSlots){
        if(camp.getTotalSlots() - committeeSlots - attendeeSlotsUsed <= 0){
            return true;
        }
        return false;
    }

    /**
     * For CommitteeList use: To find index of committee member in the list,
     * and For AttendeeList use: To find index of attendee in the list,
     * and For blackList use: To verify existence of student in blacklist
     * 
     * @param student Student to find in a list
     * @param list Blacklist/Attendee list/Camp committee list
     * @return Index position of student in list if they are in the list, otherwise, return -1
     */
    protected static int userPos(Student student, ArrayList<Student> list){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == student){
                return i;
            }
        }
        return -1;
    }

    /**
     * Overloaded method that passes userID instead of user object
     * 
     * @param userID User identification of student to search for in list
     * @param list Blacklist/Attendee list/Camp committee list
     * @returnIndex position of student in list if they are in the list, otherwise, return -1
     */
    protected static int userPos(String userID, ArrayList<Student> list){
        for(int i = 0; i < list.size(); i++){
            if(userID.equals(list.get(i).getUserID())){
                return i;
            }
        }
        return -1;
    }

    

}
