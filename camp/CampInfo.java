import java.util.ArrayList;
import user.User;
import user.Staff;

public class CampInfo {
    ArrayList<Camp> listOfCamps;
    ArrayList<Integer> attendeeSlotsUsed;
    ArrayList<Integer> campCommitteeSlotsUsed;
    ArrayList<Boolean> visibility;

    CampInfo(){
        listOfCamps = new ArrayList<Camp>();
        attendeeSlotsUsed = new ArrayList<Integer>();
        campCommitteeSlotsUsed = new ArrayList<Integer>();
        visibility = new ArrayList<Boolean>();
    }

    public void getCampList(User user){
        if(user instanceof Staff){
            

        }
        else{
            
        }
    }
}
