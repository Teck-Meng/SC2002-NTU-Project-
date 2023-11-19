package user;

import java.util.ArrayList;
import java.util.InputMismatchException;

import camppackage.Camp;
import camppackage.CampInfo;
import camppackage.CreateCampUI;
import camppackage.DuplicateCheck;
import clock.Time;
import filehandler.Database;

import java.util.Scanner;

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
    public void createCamp(CampInfo campInfo, Database database, Time time){
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
         * Do not allow empty string to be password
         */
        while(nameTaken == true || campName == ""){
            campName = CreateCampUI.askCampName();
            nameTaken = DuplicateCheck.isNameTaken(campName, myCamps);
            if(nameTaken == true){
                System.out.println("Camp Name has already been taken!");
            }
        }
        newCamp.setCampName(campName);

        int[] dates = CreateCampUI.askDates(time);
        newCamp.setDates(dates);

        int regClosingDate = CreateCampUI.askRegClosingDate(time);
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

    /*
     * Console prompt to ask staff which camp details to edit
     */
    public void editCamp(int choice, Camp camp, CampInfo campInfo, Database database, Time time){
        Scanner sc = new Scanner(System.in);
        
        /*
         * Boolean variable to allow staff to edit multiple details without multiple calls of editCamp
         */
        boolean wantToEdit = true;
        int userChoice = 0;

        while(wantToEdit == true){
            while(userChoice == 0){
                try{
                    System.out.println("Which camp detail would you like to edit? Enter the corresponding integer choice: ");
                    System.out.println("1 - Camp Name");
                    System.out.println("2 - Dates");
                    System.out.println("3 - Registration Closing Date");
                    System.out.println("4 - Location");
                    System.out.println("5 - Attendee Slots");
                    System.out.println("6 - Camp Committee Slots");
                    System.out.println("7 - Description");
                    System.out.println("8 - Visibility");
                    System.out.println("9 - Quit Editing");
                    userChoice = sc.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("Please enter a proper integer choice!");
                    sc.nextLine();
                }
                /*
                 * Verification of valid edits will be done by CreateCampUI class
                 * Assume user group cannot be edited
                 * Do not allow direct edit of total slots, can only be edited indirectly through the other 2 slots
                 */
                switch(userChoice){
                    case 1:
                        boolean nameTaken = true;
                        String campName = "";
                        while(nameTaken == true){
                            campName = CreateCampUI.askCampName(camp, campInfo);
                            nameTaken = DuplicateCheck.isNameTaken(campName, myCamps);
                            if(nameTaken == true){
                                System.out.println("Camp Name has already been taken!");
                            }
                        }
                        /*
                         * Only allow edit if nobody has registered for camp
                         */
                        if(campName != ""){
                            camp.setCampName(campName);
                        }
                        break;
                    case 2:
                        int[] dates = CreateCampUI.askDates(time);
                        camp.setDates(dates);
                        break;
                    case 3:
                        int regClosingDate = CreateCampUI.askRegClosingDate(time);
                        camp.setRegClosingDate(regClosingDate);
                        break;
                    case 4:
                        String location = CreateCampUI.askLocation();
                        camp.setLocation(location);
                        break;
                    case 5:
                    /*
                         * To edit attendee slot
                         */
                        int attendeeSlots = CreateCampUI.askAttendeeSlots(camp, campInfo);
                        if(attendeeSlots == -1){
                            break;
                        }
                        camp.setTotalSlots(campInfo.getCampCommitteeSlotsUsed(camp) + attendeeSlots);
                        break;
                    case 6:
                        int campCommitteeSlots = CreateCampUI.askCampCommitteeSlots(camp, campInfo);
                        if(campCommitteeSlots == -1){
                            break;
                        }
                        camp.setCampCommitteeSlots(campCommitteeSlots);
                        break;
                    case 7:
                        String description = CreateCampUI.askDescription();
                        camp.setDescription(description);
                        break;
                    case 8:
                        boolean visiblity = CreateCampUI.askVisibility();
                        campInfo.setVisiblity(camp, visiblity);
                        break;
                    case 9:
                        wantToEdit = false;
                        System.out.println("Quitting editing interface...");
                        break;
                    default:
                        System.out.println("Kindly enter a choice between 1 to 8");
                }
            }
            
        }
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

    public void addCamp(Camp camp){
        myCamps.add(camp);
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

    /*
     * Call this method when the staff accept the suggestion from a committee member
     * Staff can only check suggestion from their own camps so we do not need to check if camp is under the staff's jurisdiction
     */
    public void addPoints(Student committeeMember, int numberOfPoints){
        for(int i = 0; i < numberOfPoints ; i++){
            /*
             * Add points iteratively to committee member
             * If student is not a committee member, terminate addPoints() immediately
             */
            if(!committeeMember.addCommitteePoints(1)){
                return;
            }
        }
    }

}
