package enquiry;

import java.util.ArrayList;
import user.User;
import camppackage.Camp;
import filehandler.Database;

/**
 * Class acting as a database of enquiries
 */
public class ListOfEnquiries{
    //unique identification number given to an enquiry for ReplyToStudent to use
    private int id = 0;

    private ArrayList<String> enquiries = new ArrayList<String>();
    private ArrayList<User> enquirer = new ArrayList<User>();
    private ArrayList<Boolean> isAnswered = new ArrayList<Boolean>();
    private ArrayList<Camp> enquiredCamp = new ArrayList<Camp>();
    private ArrayList<Integer> enquiryID = new ArrayList<Integer>();

    /**
     * Standard get method for list of enquiries
     * 
     * @return List of Enquiries
     */
    public ArrayList<String> getEnquiries(){
        return enquiries;
    }

    /**
     * Standard set method to add an enquiry into list of enquiries
     * 
     * @param enquiry Enquiry to be added
     * @param userID User identification for User who submitted enquiry
     * @param database Database of Users
     * @param camp Camp that received the enquiry
     */
    public void addEnquiry(String enquiry, String userID, Database database, Camp camp){
        User student = database.getUser(userID);

        enquiries.add(enquiry);
        enquirer.add(student);
        isAnswered.add(false);
        enquiredCamp.add(camp);
        enquiryID.add(id++);
        System.out.println("Successfully added enquiry!");

    }

    /**
     * For readFromFile to add enquiry from excel sheet
     * 
     * @param enquiry Enquiry to be added
     * @param userID User identification attached to the enquiry
     * @param database Database of Users
     * @param camp Camp that received the enquiry
     * @param enquiryIndex Unique identification for an enquiry acting as a pointer
     * @param isItAnswered Status of whether an enquiry is replied to or not
     */
    public void addEnquiry(String enquiry, String userID, Database database, Camp camp, int enquiryIndex, boolean isItAnswered){
        User student = database.getUser(userID);

        enquiries.add(enquiry);
        enquirer.add(student);
        isAnswered.add(isItAnswered);
        enquiredCamp.add(camp);
        enquiryID.add(enquiryIndex);
        id = ++enquiryIndex;
    }

    /**
     * To update the status of an enquiry after it has been answered
     * 
     * @param index Index positioning of enquiry that has just been answered
     */
    public void answeredEnquiry(int index){
        isAnswered.set(index, true);
    }

    /**
     * Get method to fetch an enquiry for a student that belong to them
     * 
     * @param index Index positioning of enquiry in list of enquiries
     * @param userID User identification requesting to fetch the enquiry
     * @return Enquiry if verification successful and null if verification unsuccessful
     */
    public String getEnquiry(int index, String userID){
        if(userID != enquirer.get(index).getUserID()){
            return null;
        }
        return enquiries.get(index);
    }

    /**
     * For Staff/Committee Member to fetch an enquiry
     * 
     * @param index Index positioning of an enquiry
     * @return Enquiry at that given position
     */
    public String getEnquiry(int index){
        return enquiries.get(index);
    }
    
    /**
     * Method to edit enquiry
     * Unauthorized users will not be able to edit
     * Edits cannot be made after it has been answered
     * UI class will be responsible for system prompt, this method does not provide system prompt
     * 
     * @param id Unique identification number(pointer) of an enquiry
     * @param userID User identification of User trying to edit an enquiry
     * @param newEnquiry New enquiry to replace the old enquiry
     */
    protected void editEnquiry(int id, String userID, String newEnquiry){
        int index = getIndexFromID(id);
        if(userID != enquirer.get(index).getUserID()){
            System.out.println("Unauthorized personnel, you do not have permission to edit this enquiry");
            return;
        }
        if(isEnquiryAnswered(index) == true){
            System.out.println("Enquiry already answered, unable to edit enquiry");
            return;
        }
        enquiries.set(index, newEnquiry);
        System.out.println("Enquiry edited successfully!");
    }

    /**
     * Method to delete enquiry
     * Enquiry can only be deleted by enquirer and if the enquiry is not yet answered
     * 
     * @param id Unique identification number(pointer) of an enquiry
     * @param userID User identification of User trying to edit an enquiry
     */
    protected void deleteEnquiry(int id, String userID){
        int index = getIndexFromID(id);
        if(userID != enquirer.get(index).getUserID()){
            System.out.println("Unauthorized personnel, you do not have permission to delete this enquiry");
            return;
        }
        if(isEnquiryAnswered(index) == true){
            System.out.println("Enquiry already answered, unable to delete enquiry");
            return;
        }
        enquiries.remove(index);
        enquirer.remove(index);
        isAnswered.remove(index);
        enquiryID.remove(index);
        enquiredCamp.remove(index);
        System.out.println("Enquiry deletion is successful!");
    }
    
    /**
     * Standard get method for size of list of enquiries
     * @return Size of list of Enquiries
     */
    protected int getSize(){
        return enquiries.size();
    }

    /**
     * Return answered status of an enquiry given their index
     * 
     * @param index Index positioning in list of enquriies
     * @return Answered Status of an enquiry
     */
    public boolean isEnquiryAnswered(int index){
        return isAnswered.get(index);
    }

    /**
     * Get method to get enquirer for a specific enquiry given the enquiry's index positioning
     * 
     * @param index Index positioning of enquiry in list of enquiries
     * @return User identification of enquirer
     */
    public String getUserID(int index){
        return enquirer.get(index).getUserID();
    }

    /**
     * To be used to ensure only Camp Committee Members and staff of that specific camp can view enquiries
     * Camp Name is a unique identification and will be returned to do the check
     * 
     * @param index Index positioning of enquiry in list of enquiries
     * @return Camp Name that received the enquiry
     */
    public String getCampEnquiredID(int index){
        return enquiredCamp.get(index).getCampName();
    }

    /**
     * Used by ReplyToStudent to get unique enquiry identification number given newly added index position for an enquiry
     * 
     * @param index Index positioning of an enquiry
     * @return Unique identification number for a given enquiry
     */
    public int getEnquiryID(int index){
        return enquiryID.get(index);
    }

    /**
     * Conert unique identification number of an enquiry to its current index positioning in list of enquiries
     * 
     * @param id Unique identification number of an enquiry
     * @return Index position of enquiry in list of enquiries, if enquiry not found, returns -1 instead
     */
    public int getIndexFromID(int id){
        for(int i = 0; i < enquiryID.size(); i++){
            if(getEnquiryID(i) == id){
                return i;
            }
        }
        return -1;
    }

    /**
     * Used by attendee and staff class to print out all their enquiries
     * returns arraylist containing the indexes of all enquiries, this will allow attendees to edit or delete their enquiries
     * using the return arraylist during console prompt
     * Set isStaff to be true to use staff version and false for attendee version
     * 
     * @param userID User Identification
     * @param isStaff Indication of whether method is called by a Staff object
     * @return List of unique identification number of enquiries
     */
    public ArrayList<Integer> printAllEnquiries(String userID, boolean isStaff){
        ArrayList<Integer> returnIndexes = new ArrayList<Integer>();
        int count = 0;
        if(!isStaff){
            for(int i = 0; i < enquiries.size(); i++){
            if(userID == enquirer.get(i).getUserID()){
                System.out.print("Enquiry ID: "+ enquiryID.get(i)+ " : ");
                System.out.println(enquiries.get(i));
                returnIndexes.add(i);
                count++;
            }
        }
            if(count == 0){
                System.out.println("You do not have any enquiries");
                return null;
            }
            return returnIndexes;
        }
        else{
            /*
             * For staff to print all camp enquiries for camps they are in charged of
             */
            for(int i = 0; i < enquiries.size(); i++){
            if(userID == enquiredCamp.get(i).getStaffID()){
                System.out.print("Enquiry for "+ enquiredCamp.get(i).getCampName() + " , ");
                System.out.println("Enquiry ID: "+ enquiryID.get(i)+ " : ");
                System.out.println(enquiries.get(i));
                returnIndexes.add(i);
                count++;
            }
        }
            if(count == 0){
                System.out.println("Your camps do not have any enquiries");
                return null;
            }
            return returnIndexes;
        }
    }

    /**
     * Used by committee member class to print out all their enquiries
     * returns arraylist containing the indexes of all enquiries, this will allow committee member to edit or delete their enquiries
     * using the return arraylist during console prompt
     * Allow toggle to choose if committee member's own enquiry should be printed or not when calling this method
     * 
     * @param camp Specific camp to print all enquiries for
     * @param self User object requesting to print all enquiries
     * @param includeSelf Indication of whether enquiries printed should include enquiries from User object: self
     * @return
     */
    public ArrayList<Integer> printAllEnquiries(Camp camp, User self, boolean includeSelf){
         ArrayList<Integer> returnIndexes = new ArrayList<Integer>();
        int count = 0;
        
        if(includeSelf){
            for(int i = 0; i < enquiries.size(); i++){
                if(enquiredCamp.get(i) == camp){
                    count++;
                    System.out.print("Enquiry ID: "+ enquiryID.get(i)+ " , ");
                    System.out.println(enquiries.get(i));
                    returnIndexes.add(i);
                } 
            }
            if(count == 0){
            System.out.println("There are no enquiries for your camp!");
            return null;
            }
        }
        else{
            for(int i = 0; i < enquiries.size(); i++){
                /*
                 * This version is for when committee member wishes to reply to enquiry
                 * We do not want committee Member to farm points hence they are unable to answer their own enquiry
                 */
                if(enquiredCamp.get(i) == camp && !enquirer.get(i).equals(self) && !isAnswered.get(i)){
                    count++;
                    System.out.print("Enquiry ID: "+ enquiryID.get(i)+ " , ");
                    System.out.println(enquiries.get(i));
                    returnIndexes.add(i);
                } 
         }
            if(count == 0){
            System.out.println("There are no enquiries for your camp!");
            return null;
        }
        }

        
        return returnIndexes;
    }

    /**
     * For staff UI's use to print out enquiries that have not yet been replied
     * 
     * @param userID User identification of staff trying to print out enquiries
     */
    public void printAllEnquiries(String userID){
        for(int i = 0; i < enquiries.size(); i++){
            if(userID == enquiredCamp.get(i).getStaffID() && !isAnswered.get(i)){
                System.out.print("Enquiry for "+ enquiredCamp.get(i).getCampName() + " , ");
                System.out.println("Enquiry ID: "+ enquiryID.get(i)+ " : ");
                System.out.println(enquiries.get(i));
            }
        }
    }
}