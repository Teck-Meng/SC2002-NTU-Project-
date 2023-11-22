package sort;

import java.util.ArrayList;

import user.Student;
import camppackage.Camp;
/**
 * Class responsible for alphabetical sorting to ensure report printed is in alphabetical order
 */
public class AlphaSort {
    /**
     * Add student into attendee list or committee list in its alphabetical order
     * @param list to insert student object in, list is either attendee or committee list
     * @param student is the new student that has registered for camp
     */
    public static void add(ArrayList<Student> list, Student student){
        String studentName = student.getUserID();

        for(int i = 0; i < list.size(); i++){
            String compareTo = list.get(i).getUserID();
            if(studentName.compareTo(compareTo) < 0){
                list.add(i, student);
                return;
            }
        }
        list.add(student);
    }

    /**
     * Method for alphabetical sorting
     * @param listOfCamps is the list of created camps
     * @param camp refers to the new created camp to be added into list
     * @param visibility refers to the list of visibility storing the visibility of all camps
     * @param isVisible refers to the visiblity of the newly created camp
     * @param attendeeSlotsUsed refers to the list that contains the number of attendee slots used in each camp
     * @param campCommitteeSlotsUsed refers to the list that contains the number of committee slots used in each camp
     */
    public static void add(ArrayList<Camp> listOfCamps, Camp camp, ArrayList<Boolean> visibility, boolean isVisible,
                            ArrayList<Integer> attendeeSlotsUsed, ArrayList<Integer> campCommitteeSlotsUsed){
        for(int i = 0;i < listOfCamps.size();i++){
            if(camp.getCampName().compareTo(listOfCamps.get(i).getCampName()) < 0){
                listOfCamps.add(i, camp);
                visibility.add(i, isVisible);
                attendeeSlotsUsed.add(0);
                campCommitteeSlotsUsed.add(0);
                return;
            }
        }
        listOfCamps.add(camp);
        visibility.add(isVisible);
        attendeeSlotsUsed.add(0);
        campCommitteeSlotsUsed.add(0);
    }
}
