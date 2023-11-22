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
/**
 * Class representing a staff user
 * Staff users will have access to features in Staff UI
 * Subclass of User
 */
public class Staff extends User{
    /*
     * Stores all the camps that the staff has created
     */
    private ArrayList<Camp> myCamps = new ArrayList<Camp>();


    /**
     * Standard constructor of staff
     * 
     * @param UserID User identification 
     * @param facultyInfo Faculty that a staff belongs to
     */
    public Staff(String UserID, Faculty facultyInfo){
        super(UserID, facultyInfo);
    }

    /**
     * Console prompt to prompt staff to create camp
     * 
     * @param campInfo Database of camps
     * @param database Database of users
     * @param time Current Time
     */
    public void createCamp(CampInfo campInfo, Database database, Time time){
        Camp newCamp = new Camp(super.getUserID(), database);
        
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

        int regClosingDate = CreateCampUI.askRegClosingDate(time, dates[0]);
        newCamp.setRegClosingDate(regClosingDate);

        Faculty userGroup = CreateCampUI.askUserGroup();
        newCamp.setUserGroup(userGroup);

        String location = CreateCampUI.askLocation();
        newCamp.setLocation(location);

        int totalSlots = CreateCampUI.askTotalSlots();
        newCamp.setTotalSlots(totalSlots);

        int campCommitteeSlots = CreateCampUI.askCampCommitteeSlots();
        newCamp.setCampCommitteeSlots(campCommitteeSlots);

        String description = CreateCampUI.askDescription();
        newCamp.setDescription(description);
        
        myCamps.add(newCamp);
        
        boolean visiblity = CreateCampUI.askVisibility();
        campInfo.addCamp(newCamp, visiblity);

        System.out.println("Camp added successfully!");
    }

    /**
     * Console prompt for staff to edit camp
     * 
     * @param camp Camp that Staff wants to edit
     * @param campInfo Database of Camps
     * @param database Database of Users
     * @param time Current Time
     */
    public void editCamp(Camp camp, CampInfo campInfo, Database database, Time time){
        Scanner sc = new Scanner(System.in);
        
        /*
         * Boolean variable to allow staff to edit multiple details without multiple calls of editCamp
         */
        boolean wantToEdit = true;
        int userChoice = 0;

        while(wantToEdit == true){
            userChoice = 0;
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
                        while(nameTaken){
                            campName = CreateCampUI.askCampName(camp, campInfo);
                            if(campName == ""){
                                break;
                            }
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
                        int regClosingDate = CreateCampUI.askRegClosingDate(time, (camp.getDates())[0]);
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
                        camp.setTotalSlots(camp.getCampCommitteeSlots() + attendeeSlots);
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
                        campInfo.setVisiblity(camp, visiblity, true);
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

    /**
     * 
     * @return List of camps that Staff object has created
     */
    public ArrayList<Camp> getListOfCamps(){
        return myCamps;
    }
    

    /**
     * Prints out list of camps 
     */
    public void printListOfCamps(){
        for(int i = 0; i < myCamps.size(); i++){
            System.out.print((i+1) + ": ");
            System.out.println(myCamps.get(i).getCampName());
        }
    } 

    /**
     * Method to add camp to staff's list of camps
     * @param camp Newly created Camp to be added into staff's database
     */
    public void addCamp(Camp camp){
        myCamps.add(camp);
    }

    /**
     * For Staff to delete a camp
     * 
     * @param camp Camp to be deleted
     * @param campInfo Database of Camps
     */
    public void deleteCamp(Camp camp, CampInfo campInfo){
        for(int i = 0; i < myCamps.size(); i++){
            if(camp.getCampName() == myCamps.get(i).getCampName()){
                myCamps.remove(i);
            }
        }
        campInfo.deleteCamp(camp);
    }

    /**
     * For staff to reward committee member points
     * 
     * @param committeeMember Committee Member who will receive the points
     * @param numberOfPoints Number of Points to be added to committee member
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
