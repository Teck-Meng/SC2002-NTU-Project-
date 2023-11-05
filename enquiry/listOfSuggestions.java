package enquiry;

import java.util.ArrayList;

import camppackage.Camp;
import filehandler.Database;
import user.User;

public class listOfSuggestions {
    private int id = 0;

    ArrayList<String> suggestions = new ArrayList<String>();
    ArrayList<User> suggestor = new ArrayList<User>();
    ArrayList<Boolean> isApproved = new ArrayList<Boolean>();
    /*
     * isApproved is to check for approval while isAnswered is used to check if a staff has done administrative action on a suggestion
     */
    ArrayList<Boolean> isAnswered = new ArrayList<Boolean>();
    ArrayList<Camp> suggestedCamp = new ArrayList<Camp>();
    ArrayList<Integer> suggestionID = new ArrayList<Integer>();

    public void addSuggestion(String suggestion, String userID, Database database, Camp camp){
        /*
         * Acquire user object from database using userID
         * In main program, userID for this method is to be acquired using the getUserID method from the user class
         */
        User student = database.getUser(userID);

        suggestions.add(suggestion);
        suggestor.add(student);
        isApproved.add(false);
        isAnswered.add(false);
        suggestedCamp.add(camp);
        /*
         * Ensure that id value is unique by always incrementing it when a new suggestion is added
         */
        suggestionID.add(id++);
        System.out.println("Successfully added suggestion!");
    }
    /*
     * Method to edit suggestion
     * Suggestion can only be editted if the enquiry is not yet answered
     */
    public void editSuggestion(int index, String userID, String newSuggestion){
        if(userID != suggestor.get(index).getUserID()){
            System.out.println("Unauthorized personnel, you do not have permission to edit this suggestion");
            return;
        }
        if(isAnswered.get(index) == true){
            System.out.println("Suggestion already approved or denied, unable to edit suggestion");
            return;
        }
        suggestions.set(index, newSuggestion);
        System.out.println("Suggestion edited successfully!");
    }

    /*
     * Method to delete suggestion
     * Suggestion can only be deleted by enquirer and if the enquiry is not yet answered
     */
    public void deleteEnquiry(int index, String userID){
        if(userID != suggestor.get(index).getUserID()){
            System.out.println("Unauthorized personnel, you do not have permission to delete this enquiry");
            return;
        }
        if(isAnswered.get(index) == true){
            System.out.println("Enquiry already answered, unable to delete enquiry");
            return;
        }
        suggestions.remove(index);
        suggestor.remove(index);
        isApproved.remove(index);
        isAnswered.remove(index);
        suggestionID.remove(index);
        suggestedCamp.remove(index);
    }

    /*
     * Update isAnswered when a staff has approved or deny a suggestion
     */
    public void staffActionUpdate(int index){
        isAnswered.set(index, true);
    }

    /*
     * Update the isApproved data structure with staff's response to a specific suggestion
     */
    public void staffAction(int index, boolean isItApproved){
        isApproved.set(index, isItApproved);
        staffActionUpdate(index);
    }

    public String getUserID(int index){
        return suggestor.get(index).getUserID();
    }

    /*
     * To be used to ensure only staff of that specific camp can view suggestions
     * Camp Name is a unique ID and will be returned to do the check
     */
    public String getCampEnquiredID(int index){
        return suggestedCamp.get(index).getCampName();
    }

    /*
     * To be used by Replies to Committee Class as a reference id to link the replies to the enquiry
     */
    public int getEnquiryID(int index){
        return suggestionID.get(index);
    }

    /*
     * To be used by Replies Class to get the current index of an suggestion using the id as reference
     * This will allow Replies Class to call the other methods using the id
     * returns the index position of the enquiry with the corresponding userID
     */
    public int getIndexFromID(int id){
        for(int i = 0; i < suggestionID.size(); i++){
            if(getEnquiryID(i) == id){
                return i;
            }
        }
        return -1;
    }

    /*
     * Used by committee member class to print out all their suggestions
     * Used by staff class to print out all corresponding suggestions
     * returns arraylist containing the indexes of all enquiries, this will allow committee member to edit or delete their suggestions
     * This will also allow staff to see suggestions that they can approve or deny
     * boolean param isStaff to differentiate staff and committee member version of printAllSuggestions
     * using the return arraylist during console prompt
     */
    public ArrayList<Integer> printAllSuggestions(Camp camp, String userID, boolean isStaff){
        ArrayList<Integer> returnIndexes = new ArrayList<Integer>();
        /*
        * count will be used to check if staff or committee member has any suggestions, if not, print out a message indicating so
        */
       int count = 0;
        if(isStaff){
            for(int i = 0; i < suggestions.size(); i++){
                if(userID == suggestedCamp.get(i).getStaffID()){
                    count++;
                    System.out.println(suggestions.get(i));
                    returnIndexes.add(i);
                }
            }
        }
        else{
            for(int i = 0; i < suggestions.size(); i++){
                if(userID == suggestor.get(i).getUserID()){
                    count++;
                    System.out.println(suggestions.get(i));
                    returnIndexes.add(i);
           }
        }
        }
        if(count == 0){
           System.out.println("You do not have any suggestions!");
           return null;
        }
        return returnIndexes;
   }

   
}
