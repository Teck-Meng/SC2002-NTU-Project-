package camppackage;

import java.util.ArrayList;

import clock.Time;
import filehandler.Database;

/**
 * Interface for registration of students to camps
 */
public interface CampRegister {
    /**
     * Method to register a student to a camp
     * 
     * @param campInfo Database of Camps
     * @param camp The camp that the student wishes to register for
     * @param userID User identification of student
     * @param database Database of Users
     * @param clock Current date
     * @return Boolean value indicating success of registration
     */
    public boolean registerCamp(CampInfo campInfo, Camp camp, String userID, Database database, Time clock);

    /**
     * Method that allows users to view list of camps
     * @param campInfo Database of Camps
     * @param userID User identification of user who wishes to view list of camps
     * @param database Database of Users
     * @return List of Camps visible to user
     */
    public ArrayList<Camp> viewCamps(CampInfo campInfo, String userID, Database database);

    /**
     * Method that checks if a camp is full
     * 
     * @param campInfo Database of Camps
     * @param camp Camp that is being checked for vacancy
     * @return Boolean value indicating if a camp is full or not
     */
    public boolean isCampFull(CampInfo campInfo, Camp camp);
}
