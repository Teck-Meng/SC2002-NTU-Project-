import user.Faculty;
import user.Staff;
import user.User;
import java.util.ArrayList;

public class Camp{
    private String campName;
    private ArrayList<Integer> dates = new ArrayList<Integer>(2);
    //Format for dates will be 8 digit integer in the format DD/MM/YYYY
    private int regClosingDate;
    private Faculty userGroup;
    private String location;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private Staff StaffIC;
    private AttendeeList attendeeList;
    private CommitteeList committeeList;
    

    public String getCampName(){
        return campName;
    }

    public ArrayList<Integer> getDates(){
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
        if(user == this.StaffIC){
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
}