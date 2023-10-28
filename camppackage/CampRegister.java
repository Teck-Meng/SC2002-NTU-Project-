package camppackage;

import java.util.ArrayList;
import user.User;

public interface CampRegister {
    //return false if camp is full
    public boolean registerCamp(CampInfo campInfo, Camp camp, User user);
    public ArrayList<Camp> viewCamps(CampInfo campInfo, User user);
    
}
