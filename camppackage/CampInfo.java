package camppackage;

import java.util.ArrayList;
import user.User;
import user.Staff;
import user.Faculty;

public class CampInfo {
    ArrayList<Camp> listOfCamps = new ArrayList<Camp>();
    ArrayList<Integer> attendeeSlotsUsed = new ArrayList<Integer>();
    ArrayList<Integer> campCommitteeSlotsUsed = new ArrayList<Integer>();
    ArrayList<Boolean> visibility = new ArrayList<Boolean>();

    //default empty constructor as arraylist is empty for all attributes

    public ArrayList<Camp> getCampList(User user){
        if(user instanceof Staff){
            return listOfCamps;
        }
        else{
            /* For students, we return a filtered-out camp list that matches the criteria for visibility */
            ArrayList<Camp> returnList = new ArrayList<Camp>();
            for(int i = 0; i < listOfCamps.size(); i++){
                /* For every iteration, check if camp is open to all students, if not, check if
                 * user group matches student's faculty, if yes, insert into arrayList
                 */
                if(visibility.get(i)&&matchFaculty(user, listOfCamps.get(i).getUserGroup())){
                    returnList.add(listOfCamps.get(i));
                }
            }
            return returnList;
        }
    }

    public void updateAttendeeSlotUsed(boolean isIncrement, Camp camp){
        // store return value of campPos due to possible repeated usage
        int index = CampUtility.CampPos(camp, listOfCamps);
        // checks for existence of camp
        if(index == -1){
            return;
        } // check if there is space for students
        else if(CampUtility.isFull(camp, attendeeSlotsUsed.get(index)) == true){
            System.out.println("Camp is at full capacity! Unable to register more attendees!");
            return;
        }
        int intialValue = attendeeSlotsUsed.get(index);
        if(isIncrement == true){
            attendeeSlotsUsed.set(index, ++intialValue);
        }
        else{
            attendeeSlotsUsed.set(index, --intialValue);
        }
    }

    public void updateCommitteeSlotUsed(Camp camp){
        // store return value of campPos due to possible repeated usage
        int index = CampUtility.CampPos(camp, listOfCamps);
        // checks for existence of camp
        if(index == -1){
            return;
        } // check if capacity of committee is already at maximum
        else if(campCommitteeSlotsUsed.get(index) >= 10){
            System.out.println("Camp Committee is full! Unable to process registration!");
        }
        //Process the addition of camp committee member
        int intialValue = campCommitteeSlotsUsed.get(index);
        campCommitteeSlotsUsed.set(index, ++intialValue);
    }

    public int getAttendeeSlotUsed(Camp camp){
        int index = CampUtility.CampPos(camp, listOfCamps);
        if(index == -1){
            //if unsuccessful, return -1
            return -1;
        }
        return attendeeSlotsUsed.get(index);
    }

    public int getCampCommitteeSlotsUsed(Camp camp){
        int index = CampUtility.CampPos(camp, listOfCamps);
        if(index == -1){
            //return -1 if unsuccessful
            return index;
        }
        return campCommitteeSlotsUsed.get(index);
    }

    public ArrayList<Camp> getFullList(){
        return listOfCamps;
    }

    public boolean getVisibility(Camp camp){
        int index = CampUtility.CampPos(camp, listOfCamps);
        // Check for existence of camp, if it does not exist, exit program immediately
        if(index == -1){
            return false;
        }
        return visibility.get(index);
    }

    //allows staff to set visiblity of a specific camp that they have created
    public boolean setVisiblity(Camp camp, boolean isVisible){
         int index = CampUtility.CampPos(camp, listOfCamps);
        if(index == -1){
            return false;
        }
        visibility.set(index, isVisible);
        return true;
    }

    public void addCamp(Camp camp, boolean isVisible){
        /*
         * Add camp information into data structures
         */
        listOfCamps.add(camp);
        visibility.add(isVisible);
        attendeeSlotsUsed.add(0);
        campCommitteeSlotsUsed.add(0);
    }

    public boolean deleteCamp(Camp camp){
        for(int i = 0; i < listOfCamps.size(); i++){
                if(camp == listOfCamps.get(i)){
                    listOfCamps.remove(i);
                    attendeeSlotsUsed.remove(i);
                    campCommitteeSlotsUsed.remove(i);
                    visibility.remove(i);
                    System.out.println("Camp deletion successful!");
                    return true;
                }
            }
            System.out.println("Camp deletion unsuccessful!");
            return false;
        }

    

    //private method to check if faculty of user matches that of the camp
    private boolean matchFaculty(User user, Faculty faculty){
        if(faculty == Faculty.ALL){
            return true;
        }
        else if(user.getFaculty()==faculty){
            return true;
        }
        return false;
    }
}

    /* Checks for position of camp, if camp does not exist, return -1 and print out error message
     * Else, return the index of the camp in the data structure listOfCamps
     */
    