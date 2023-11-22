package camppackage;

import java.util.ArrayList;
import user.User;
import user.Staff;
import user.Faculty;
import filehandler.Database;
import sort.AlphaSort;
import clock.Time;

/**
 * Class representing database of camps
 */
public class CampInfo {
    private ArrayList<Camp> listOfCamps = new ArrayList<Camp>();
    private ArrayList<Integer> attendeeSlotsUsed = new ArrayList<Integer>();
    private ArrayList<Integer> campCommitteeSlotsUsed = new ArrayList<Integer>();
    private ArrayList<Boolean> visibility = new ArrayList<Boolean>();


    /**
     * Method to get list of camps visible to a student
     * 
     * @param user Student requesting to get camp list
     * @return List of camps visible to student
     */
    public ArrayList<Camp> getCampList(User user){
        if(user instanceof Staff){
            return listOfCamps;
        }
        else{
            ArrayList<Camp> returnList = new ArrayList<Camp>();
            for(int i = 0; i < listOfCamps.size(); i++){
                if(visibility.get(i) && matchFaculty(user, listOfCamps.get(i).getUserGroup())){
                    returnList.add(listOfCamps.get(i));
                }
            }
            return returnList;
        }
    }

    /**
     * Overloaded method to fetch list of camps that a specific student can register for
     * 
     * @param user Specific student
     * @param campInfo Database of Camps
     * @param database Database of Users
     * @param clock Current date
     * @return List of camps a specific student can register for
     */
    public ArrayList<Camp> getCampList(User user, CampInfo campInfo, Database database, Time clock){
        ArrayList<Camp> returnList = new ArrayList<Camp>();
        for(int i = 0; i < listOfCamps.size(); i++){
                Camp currentCamp = listOfCamps.get(i);
                if(visibility.get(i) && matchFaculty(user, listOfCamps.get(i).getUserGroup()) 
                    && ValidateRegister.canRegisterForCamp(campInfo, currentCamp, user.getUserID(), database, clock)){
                        returnList.add(listOfCamps.get(i));
                }
            }
        return returnList;
    }

    /**
     * Method to update attendee slots used for a specific camp
     * 
     * @param isIncrement Indication of whether attendee slots used should be increased or decreased
     * @param camp Specific camp that requires updating of attendee slots used
     * @param value Number to increase or decrease by during updating of attendee slots used
     */
    public void updateAttendeeSlotsUsed(boolean isIncrement, Camp camp, int value){
        int index = CampUtility.CampPos(camp, listOfCamps);
        if(index == -1){
            return;
        } 
        else if(CampUtility.isFull(camp, attendeeSlotsUsed.get(index),campCommitteeSlotsUsed.get(index)) == true){
            System.out.println("Camp is at full capacity! Unable to register more attendees!");
            return;
        }
        int intialValue = attendeeSlotsUsed.get(index);
        if(isIncrement == true){
            attendeeSlotsUsed.set(index, (intialValue + value));
        }
        else{
            attendeeSlotsUsed.set(index, (intialValue - value));
        }
    }

    /**
     * Method to add committee slots used for a specific camp
     * @param camp Specific camp that requires updating of camp committee slots used
     * @param value Number to increase by during updating of camp committee slots used
     */
    public void updateCommitteeSlotUsed(Camp camp, int value){
        int index = CampUtility.CampPos(camp, listOfCamps);
        if(index == -1){
            return;
        } 
        else if(campCommitteeSlotsUsed.get(index) >= 10){
            System.out.println("Camp Committee is full! Unable to process registration!");
        }
        int intialValue = campCommitteeSlotsUsed.get(index);
        campCommitteeSlotsUsed.set(index, (intialValue + value));
    }

    /**
     * Method to fetch attendee slots used for a specific camp
     * 
     * @param camp Specific Camp
     * @return Attendee slots used for that specific camp, returns -1 if camp does not exist
     */
    public int getAttendeeSlotsUsed(Camp camp){
        int index = CampUtility.CampPos(camp, listOfCamps);
        if(index == -1){
            return -1;
        }
        return attendeeSlotsUsed.get(index);
    }

    /**
     * Method to get camp committee slots used for a specific camp
     * 
     * @param camp Specific camp
     * @return Camp committee slots used for that specific camp, returns -1 if camp does not exist
     */
    public int getCampCommitteeSlotsUsed(Camp camp){
        int index = CampUtility.CampPos(camp, listOfCamps);
        if(index == -1){
            return index;
        }
        return campCommitteeSlotsUsed.get(index);
    }

    /**
     * Standard get method to get full list of camps
     * 
     * @return List of camps
     */
    public ArrayList<Camp> getFullList(){
        return listOfCamps;
    }

    /**
     * Method to fetch visibility of a specific camp
     * 
     * @param camp Specific Camp
     * @return Boolean value indicating if specific camp is visible or not
     */
    public boolean getVisibility(Camp camp){
        int index = CampUtility.CampPos(camp, listOfCamps);
        if(index == -1){
            return false;
        }
        return visibility.get(index);
    }

    /**
     * Standard method to set visibility of a specific camp
     * 
     * @param camp Specific camp
     * @param isVisible Visibility to set specific camp
     * @param isStaff Indicates whether method caller is a Staff object(true) or is from filehandler package(false)
     * @return Boolean value indicating success of setting visibility
     */
    public boolean setVisiblity(Camp camp, boolean isVisible, boolean isStaff){
        int index = CampUtility.CampPos(camp, listOfCamps);
        if(!isStaff){
            /*
             * Give special rights to fileHandler
             */
            visibility.set(index, isVisible);
            return true;
        }
        if(index == -1){
            return false;
        }
        if(campCommitteeSlotsUsed.get(index) > 0 || attendeeSlotsUsed.get(index) > 0){
            System.out.println("Camp already has at least 1 person registered, unable to toggle visiblity!");
            return false;
        }
        visibility.set(index, isVisible);
        return true;
    }

    /**
     * Method to add specific camp into list of camps
     * 
     * @param camp Specific camp
     * @param isVisible Visibility of specific camp
     */
    public void addCamp(Camp camp, boolean isVisible){
        AlphaSort.add(listOfCamps, camp, visibility, isVisible, attendeeSlotsUsed, campCommitteeSlotsUsed);
    }

    /**
     * Method to delete camp from list of camps
     * 
     * @param camp
     * @return Boolean Value indicating success of deletion of camp from list of camps
     */
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

    

    /**
     * Compares user faculty with user group of camp
     * 
     * @param user User object to check faculty for
     * @param faculty User Group of camp
     * @return Whether the user's faculty matches the camp's user group
     */
    private boolean matchFaculty(User user, Faculty faculty){
        if(faculty == Faculty.ALL){
            return true;
        }
        else if(user.getFaculty()==faculty){
            return true;
        }
        return false;
    }

    /**
     * Method that fetches a camp given its numerical index in list of camps
     * 
     * @param index Numerical index position of camp in list of camps
     * @return Camp object at index in list of camps
     */
    public Camp getCamp(int index){
        return listOfCamps.get(index);
    }

    /**
     * Method that fetches a camp given its camp name
     * 
     * @param campName Name of camp
     * @return Corresponding Camp object
     */
    public Camp getCamp(String campName){
        for(int i = 0; i < listOfCamps.size(); i++){
            Camp compareWith = listOfCamps.get(i);
            if(campName.equals(compareWith.getCampName())){
                return compareWith;
            }
        }
        return null;
    }
}

    