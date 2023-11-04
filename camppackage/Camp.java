package camppackage;

import user.Faculty;
import user.Staff;
import user.User;
import password.Database;

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
    
    public Camp(String UserID, Database database){
        /*
         * Validity check not required as system will be prompted to log all details into database at the start
         * If staff can log in, they exist in the database
         * Also, not users cannot be deleted from database as assumption is that no user should be deleted from database
         */
        this.staffIC = (Staff)database.getUser(UserID); 
        //only allow staffIC to be set during intialization
    }

    public String getCampName(){
        return campName;
    }

    public int[] getDates(){
        return dates;
    }

    public int getRegClosingDate(){
        return regClosingDate;
    }

    public Faculty getUserGroup(){
        return userGroup;
    }

    public String getLocation(){
        return location;
    }

    public int getTotalSlots(){
        return totalSlots;
    }

    public int getCampCommitteeSlots(){
        return campCommitteeSlots;
    }

    public String getDescription(){
        return description;
    }

    //only allow verification of whether current user is the creator of any specific camp
    //User should not be able to extract Staff class through a get method
    public boolean verifyStaff(User user){
        if(user == this.staffIC){
            return true;
        }
        return false;
    }

    public AttendeeList getAttendeeList(){
        return attendeeList;
    }

    public CommitteeList getCommitteeList(){
        return committeeList;
    }

    public Blacklist getBlacklist(){
        return blacklist;
    }

    public void setCampName(String campName){
        this.campName = campName;
    }

    public void setDates(int[] dates){
        this.dates = dates;
    }

    public void setRegClosingDate(int regClosingDate){
        this.regClosingDate = regClosingDate;
    }

    public void setUserGroup(Faculty userGroup){
        this.userGroup = userGroup;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setTotalSlots(int totalSlots){
        this.totalSlots = totalSlots;
    }

    public void setCampCommitteeSlots(int campCommitteeSlots){
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getStaffID(){
        return staffIC.getUserID();
    }
}