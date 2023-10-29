package camppackage;

import java.util.ArrayList;
import password.Database;
import clock.Time;

public interface CampRegister {
    //return false if camp is full
    public boolean registerCamp(CampInfo campInfo, Camp camp, String userID, Database database, Time clock);
    public ArrayList<Camp> viewCamps(CampInfo campInfo, String userID, Database database);
    public boolean isCampFull(CampInfo campInfo, Camp camp);
}