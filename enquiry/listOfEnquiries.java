package enquiry;

import java.util.ArrayList;
import user.User;
import camppackage.Camp;
import filehandler.Database;

public class ListOfEnquiries{
    private int id = 0;

    private ArrayList<String> enquiries = new ArrayList<String>();
    private ArrayList<User> enquirer = new ArrayList<User>();
    private ArrayList<Boolean> isAnswered = new ArrayList<Boolean>();
    private ArrayList<Camp> enquiredCamp = new ArrayList<Camp>();
    private ArrayList<Integer> enquiryID = new ArrayList<Integer>();

    protected void addEnquiry(String enquiry, String userID, Database database, Camp camp){
        /*
         * Acquire user object from database using userID
         * In main program, userID for this method is to be acquired using the getUserID method from the user class
         */
        User student = database.getUser(userID);

        enquiries.add(enquiry);
        enquirer.add(student);
        isAnswered.add(false);
        enquiredCamp.add(camp);
        /*
         * Ensure that id value is unique by always incrementing it when a new enquiry is added
         */
        enquiryID.add(id++);
        System.out.println("Successfully added enquiry!");

    }

    /*
     * Update isAnswered at the specific index that the staff or CCMember has answered the enquiry
     */
    protected void answeredEnquiry(int index){
        isAnswered.set(index, true);
        System.out.println("Enquiry successfully answered!");
    }

    /*
     * Returns string value of enquiry at a specific index
     * This version is for attendeeUI
     */
    public String getEnquiry(int index, String userID){
        if(userID != enquirer.get(index).getUserID()){
            return null;
        }
        return enquiries.get(index);
    }

    /*
     * To be used by Staff and Camp Committee Member
     */
    public String getEnquiry(int index){
        return enquiries.get(index);
    }
    /*
     * Method to edit enquiry
     * Unauthorized users will not be able to edit
     * Edits cannot be made after it has been answered
     * UI class will be responsible for system prompt, this method does not provide system prompt
     */
    protected void editEnquiry(int index, String userID, String newEnquiry){
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

    /*
     * Method to delete enquiry
     * Enquiry can only be deleted by enquirer and if the enquiry is not yet answered
     */
    protected void deleteEnquiry(int index, String userID){
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
    }
    
    protected int getSize(){
        return enquiries.size();
    }

    /*
     * Returns the status of the enquiry
     */
    protected boolean isEnquiryAnswered(int index){
        return isAnswered.get(index);
    }

    /* 
     * Returns userID of student who sent enquiry
    */
    public String getUserID(int index){
        return enquirer.get(index).getUserID();
    }

    /*
     * To be used to ensure only Camp Committee Members and staff of that specific camp can view enquiries
     * Camp Name is a unique ID and will be returned to do the check
     */
    public String getCampEnquiredID(int index){
        return enquiredCamp.get(index).getCampName();
    }

    /*
     * To be used by Replies Class as a reference id to link the replies to the enquiry
     */
    public int getEnquiryID(int index){
        return enquiryID.get(index);
    }

    /*
     * To be used by Replies Class to get the current index of an enquiry using the id as reference
     * This will allow Replies Class to call the other methods using the id
     * returns the index position of the enquiry with the corresponding userID
     */
    public int getIndexFromID(int id){
        for(int i = 0; i < enquiryID.size(); i++){
            if(getEnquiryID(i) == id){
                return i;
            }
        }
        return -1;
    }

    /*
     * Used by attendee and staff class to print out all their enquiries
     * returns arraylist containing the indexes of all enquiries, this will allow attendees to edit or delete their enquiries
     * using the return arraylist during console prompt
     * Set isStaff to be true to use staff version and false for attendee version
     */
    public ArrayList<Integer> printAllEnquiries(String userID, boolean isStaff){
        ArrayList<Integer> returnIndexes = new ArrayList<Integer>();
        /*
         * count will be used to check if student has any enquiries, if not, print out a message indicating so
         */
        int count = 0;
        if(!isStaff){
            for(int i = 0; i < enquiries.size(); i++){
            if(userID == enquirer.get(i).getUserID()){
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
                System.out.println(enquiries.get(i));
                System.out.println("Enquiry for " + enquiredCamp.get(i).getCampName());
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

    /*
     * Used by committee member class to print out all their enquiries
     * returns arraylist containing the indexes of all enquiries, this will allow committee member to edit or delete their enquiries
     * using the return arraylist during console prompt
     */
    public ArrayList<Integer> printAllEnquiries(Camp camp){
         ArrayList<Integer> returnIndexes = new ArrayList<Integer>();
         /*
         * count will be used to check if student has any enquiries, if not, print out a message indicating so
         */
        int count = 0;
         for(int i = 0; i < enquiries.size(); i++){
            if(enquiredCamp.get(i) == camp){
                count++;
                System.out.println(enquiries.get(i));
                returnIndexes.add(i);
            }
         }
         if(count == 0){
            System.out.println("There are no enquiries for your camp!");
            return null;
         }
         return returnIndexes;
    }

    
}