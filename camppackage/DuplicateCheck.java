package camppackage;

import java.util.ArrayList;
/*
 * Class responsible for identifying duplication
 */
public class DuplicateCheck {
    public static boolean isNameTaken(String campName, ArrayList<Camp> camps){
        for(int i = 0; i < camps.size(); i++){
            if(camps.get(i).getCampName() == campName){
                return true;
            }
        }
        return false;
    }
}
