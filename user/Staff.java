package user;

import java.util.ArrayList;
import camppackage.Camp;
import camppackage.CampInfo;
import camppackage.CreateCampUI;
import camppackage.DuplicateCheck;
import password.Database;

public class Staff extends User{
    /*
     * Stores all the camps that the staff has created
     */
    private ArrayList<Camp> myCamps = new ArrayList<Camp>();


    /*
     * Standard constructor
     */
    public Staff(String UserID, Faculty facultyInfo){
        super(UserID, facultyInfo);
    }

    /*
     * Console prompt to create camp
     */
    public void createCamp(CampInfo campInfo, Database database){
        Camp newCamp = new Camp(super.getUserID(), database);
        /*
         * Prompt Staff to enter basic information such as camp name for the camp
         * Use of set methods will set the attributes after the prompt
         */
        boolean nameTaken = true;
        String campName = "";
        /*
         * Check if camp name has been taken
         * This is to prevent duplicate camps from being created 
         */
        while(nameTaken == true){
            campName = CreateCampUI.askCampName();
            nameTaken = DuplicateCheck.isNameTaken(campName, myCamps);
            if(nameTaken == true){
                System.out.println("Camp Name has already been taken!");
            }
        }
        newCamp.setCampName(campName);

        int[] dates = CreateCampUI.askDates();
        newCamp.setDates(dates);

        int regClosingDate = CreateCampUI.askRegClosingDate();
        newCamp.setRegClosingDate(regClosingDate);

        Faculty useGroup = CreateCampUI.askUserGroup();
        newCamp.setUserGroup(useGroup);

        String location = CreateCampUI.askLocation();
        newCamp.setLocation(location);

        int totalSlots = CreateCampUI.askTotalSlots();
        newCamp.setTotalSlots(totalSlots);

        int campCommitteeSlots = CreateCampUI.askCampCommitteeSlots();
        newCamp.setCampCommitteeSlots(campCommitteeSlots);

        String description = CreateCampUI.askDescription();
        newCamp.setDescription(description);
        /*
         * Add to list of camps created by staff
         */
        myCamps.add(newCamp);
        /*
         * Set visibility of camp
         */
        boolean visiblity = CreateCampUI.askVisibility();
        campInfo.addCamp(newCamp, visiblity);

        System.out.println("Camp added successfully!");
    }


    public ArrayList<Camp> getListOfCamps(){
        return myCamps;
    }

    /*
     * Checks if camp name has already been taken
     */
    

    /*
     * Prints out list of names of staffs' camps
     */
    public void printListOfCamps(){
        for(int i = 0; i < myCamps.size(); i++){
            System.out.println(myCamps.get(i).getCampName());
        }
    } 

    public void deleteCamp(Camp camp, CampInfo campInfo){
        /*
         * Delete camp in Staff's list of camps
         */
        for(int i = 0; i < myCamps.size(); i++){
            if(camp.getCampName() == myCamps.get(i).getCampName()){
                myCamps.remove(i);
            }
        }
        /*
         * Delete camp from camp info database
         */
        campInfo.deleteCamp(camp);
    }
}
