package report;

import java.util.ArrayList;

import camppackage.Camp;
import camppackage.ValidateDate;
import user.Faculty;

public class Filter {
    public static ArrayList<Camp> filterByCampFaculty(ArrayList<Camp> listOfCamps, Faculty faculty){
        ArrayList<Camp> returnList = new ArrayList<Camp>();

        for(int i = 0; i < listOfCamps.size(); i++){
            Camp currentCamp = listOfCamps.get(i);
            Faculty compareTo = currentCamp.getUserGroup();
            if(compareTo == faculty || compareTo == Faculty.ALL){
                returnList.add(currentCamp);
            }
        }


        return returnList;
    }

    public static ArrayList<Camp> filterByLocation(ArrayList<Camp> listOfCamps, String location){
        ArrayList<Camp> returnList = new ArrayList<Camp>();

        for(int i = 0; i < listOfCamps.size(); i++){
            Camp currentCamp = listOfCamps.get(i);
            String compareTo = currentCamp.getLocation();
            if(compareTo.matches(location)){
                returnList.add(currentCamp);
            }
        }


        return returnList;
    }

    public static ArrayList<Camp> filterByDate(ArrayList<Camp> listOfCamps, int date){
        ArrayList<Camp> returnList = new ArrayList<Camp>();

        for(int i = 0; i < listOfCamps.size(); i++){
            Camp currentCamp = listOfCamps.get(i);
            int[] dates = currentCamp.getDates();

            if(ValidateDate.withinRange(date, dates)){
                returnList.add(currentCamp);
            }
        }


        return returnList;
    }
}
