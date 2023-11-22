package camppackage;

import java.util.ArrayList;
/**
 * Class responsible for identifying duplication in camp name
 */
public class DuplicateCheck {
    /**
     * Method to check if a camp name is already taken or not
     * 
     * @param campName Camp name that is being checked
     * @param camps List of Camps
     * @return Boolean value indicating if a camp name is already being used or not
     */
    public static boolean isNameTaken(String campName, ArrayList<Camp> camps){
        for(int i = 0; i < camps.size(); i++){
            if(campName.equals(camps.get(i).getCampName())){
                return true;
            }
        }
        return false;
    }
}
