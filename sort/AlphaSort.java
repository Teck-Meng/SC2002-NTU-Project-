package sort;

import java.util.ArrayList;

import user.Student;
import camppackage.Camp;

public class AlphaSort {
    /*
     * Finds the position that the student should be in in the list and add them in that position
     * This will allow committeeList and attendeeList to always be in alphabetical order
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
        /*
         * Add student to end of the list if their name is greater in alphabetical than the rest of the elements in list
         */
        list.add(student);
    }

    /*
     * Overloaded method for alphabetical sorting in camp list
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
        /*
         * Execute if list of camp has no elements or camp to be added to end of list of camp
         */
        listOfCamps.add(camp);
        visibility.add(isVisible);
        attendeeSlotsUsed.add(0);
        campCommitteeSlotsUsed.add(0);
    }
}
