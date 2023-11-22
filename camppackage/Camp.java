package camppackage;

import filehandler.Database;
import user.Faculty;
import user.Staff;
import user.User;

/**
 * Class representing a camp
 */
public class Camp{
    private String campName;

    //Format for dates will be 8 digit integer in the format DD/MM/YYYY
    //dates will be in array data structure to fix the size as there should only be 2 dates in dates
    private int[] dates = new int[2];
    private int regClosingDate;
    private Faculty userGroup;
    private String location;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private Staff staffIC;
    private AttendeeList attendeeList;
    private CommitteeList committeeList;
    private Blacklist blacklist;
    
    /**
     * Standard constructor of a camp object
     * 
     * @param UserID User identification of staff who created the camp
     * @param database Database of Users
     */
    public Camp(String UserID, Database database){
        this.staffIC = (Staff)database.getUser(UserID); 
        attendeeList = new AttendeeList();
        committeeList = new CommitteeList();
        blacklist = new Blacklist();
    }

    /**
     * Standard method to get camp name
     * 
     * @return Camp name
     */
    public String getCampName(){
        return campName;
    }

    /**
     * Standard method to get dates of camp
     * 
     * @return Dates
     */
    public int[] getDates(){
        return dates;
    }

    /**
     * Standard method to get registration closing date of camp object
     * 
     * @return Registration closing date
     */
    public int getRegClosingDate(){
        return regClosingDate;
    }

    /**
     * Standard method to get user group of camp object
     * 
     * @return User Group
     */
    public Faculty getUserGroup(){
        return userGroup;
    }

    /**
     * Standard method to get location of camp
     * 
     * @return Location
     */
    public String getLocation(){
        return location;
    }

    /**
     * Standard method to get total slots of camp object
     * 
     * @return Total slots
     */
    public int getTotalSlots(){
        return totalSlots;
    }

    /**
     * Standard method to get camp committee slots of camp object
     * 
     * @return Camp committee slots
     */
    public int getCampCommitteeSlots(){
        return campCommitteeSlots;
    }

    /**
     * Standard method to get description of camp object
     * 
     * @return Description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Method to verify if user is the staff who created the camp
     * 
     * @param user User object to check if they are the staff in charge of camp object
     * @return Boolean value of whether user is the staff in charge of camp object
     */
    public boolean verifyStaff(User user){
        if(user == this.staffIC){
            return true;
        }
        return false;
    }

    /**
     * Standard method to get attendee list of camp object
     * 
     * @return List of attendees
     */
    public AttendeeList getAttendeeList(){
        return attendeeList;
    }

    /**
     * Standard method to get list of camp committee members of camp object
     * 
     * @return List of camp committee members
     */
    public CommitteeList getCommitteeList(){
        return committeeList;
    }

    /**
     * Standard method to get blacklist of camp object
     * 
     * @return Blacklist
     */
    public Blacklist getBlacklist(){
        return blacklist;
    }

    /**
     * Standard set method to change camp name of camp object
     * 
     * @param campName New camp name to replace current camp name
     */
    public void setCampName(String campName){
        this.campName = campName;
    }

    /**
     * Standard set method to change dates of camp object
     * 
     * @param dates New dates to replace current dates
     */
    public void setDates(int[] dates){
        this.dates = dates;
    }

    /**
     * Standard set method to change registration closing date of camp object
     * 
     * @param regClosingDate New registration closing date to replace current registration closing date
     */
    public void setRegClosingDate(int regClosingDate){
        this.regClosingDate = regClosingDate;
    }

    /**
     * Standard set method to change user group of camp object
     * 
     * @param userGroup New user group to replace current user group
     */
    public void setUserGroup(Faculty userGroup){
        this.userGroup = userGroup;
    }

    /**
     * Standard set method to change location of camp object
     * 
     * @param location New location to replace current location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * Standard set method to change total slots of camp object
     * 
     * @param totalSlots New total slots to replace current total slots
     */
    public void setTotalSlots(int totalSlots){
        this.totalSlots = totalSlots;
    }

    /**
     * Standard set method to change camp committee slots of camp object
     * 
     * @param campCommitteeSlots New camp committee slots to replace current camp committee slots
     */
    public void setCampCommitteeSlots(int campCommitteeSlots){
        this.campCommitteeSlots = campCommitteeSlots;
    }

    /**
     * Standard set method to change description of camp object
     * 
     * @param description New description to replace current description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Standard get method for staff user identification
     * @return Staff ID
     */
    public String getStaffID(){
        return staffIC.getUserID();
    }

    


}