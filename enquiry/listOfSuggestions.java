package enquiry;

import java.util.ArrayList;

import camppackage.Camp;
import filehandler.Database;
import user.User;

/**
 * Class acting as a database of suggestions
 */
public class ListOfSuggestions {
    // Unique identification number for a given suggestionh
    private static int id = 0;

    private ArrayList<String> suggestions = new ArrayList<String>();
    private ArrayList<User> suggestor = new ArrayList<User>();
    private ArrayList<Boolean> isApproved = new ArrayList<Boolean>();
    /*
     * isApproved is to check for approval while isAnswered is used to check if a staff has done administrative action on a suggestion
     */
    private ArrayList<Boolean> isAnswered = new ArrayList<Boolean>();
    private ArrayList<Camp> suggestedCamp = new ArrayList<Camp>();
    private ArrayList<Integer> suggestionID = new ArrayList<Integer>();
    /**
     * Method to add suggestion during main program run
     * 
     * @param suggestion Suggestion to be added
     * @param userID User identification of user submitting suggestion
     * @param database Database of Users
     * @param camp Camp that received the suggestion
     */
    public void addSuggestion(String suggestion, String userID, Database database, Camp camp){
        User student = database.getUser(userID);

        suggestions.add(suggestion);
        suggestor.add(student);
        isApproved.add(false);
        isAnswered.add(false);
        suggestedCamp.add(camp);

        suggestionID.add(id++);
        System.out.println("Successfully added suggestion!");
    }
    
    /**
     * Method to add suggestion during the reading of csv file before the start of the main program
     * 
     * @param suggestion Suggestion to be added
     * @param userID User identification of the User who submitted the suggestion
     * @param camp Camp that received the suggestion
     * @param suggestionIndex Unique identification number for a given suggestion
     * @param isItApproved Status of whether a suggestion is approved
     * @param database Database of Users
     */
    public void addSuggestion(String suggestion, String userID, Camp camp, int suggestionIndex, boolean isItApproved, Database database){
        suggestions.add(suggestion);
        suggestor.add(database.getUser(userID));
        suggestedCamp.add(camp);
        suggestionID.add(suggestionIndex);
        isAnswered.add(isItApproved);
        isApproved.add(isItApproved);
        id = ++suggestionIndex;
    }

    /**
     * Method fetches suggestion given its index positioning in list of suggestions
     * 
     * @param index Index positioning of suggestion in list of suggestions
     * @return Suggestion at a given index
     */
    public String getSuggestion(int index){
        return suggestions.get(index);
    }

    /**
     * Standard get method for list of suggestions
     * 
     * @return List of suggestions
     */
    public ArrayList<String> getListOfSuggestions(){
        return suggestions;
    }
    
    /**
     * Method to edit suggestion
     * Suggestion can only be editted if the enquiry is not yet answered
     * index does not refer to suggestionID here
     * 
     * @param index Index positioning of suggestion in list of suggestion
     * @param userID User identification of user who wishers to edit suggestion
     * @param newSuggestion New suggestion to replace the current suggestion
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

    /**
     * Method to delete suggestion
     * Suggestion can only be deleted by enquirer and if the enquiry is not yet answered
     * index does not refer to suggestionID here
     * Make the necessary conversion first before passing index
     * 
     * @param index Index positioning of suggestion in list of suggestion
     * @param userID User identification of User trying to delete suggestion
     */
    public void deleteSuggestion(int index, String userID){
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

    /**
     * Indicates that a suggestion has received a response from Staff 
     * 
     * @param index Index positioning of suggestion
     */
    public void staffActionUpdate(int index){
        isAnswered.set(index, true);
    }

    /**
     * Updates Staff approval status of a suggestion
     * 
     * @param index Index positioning of a suggestion in list of suggestion
     * @param isItApproved Staff Approval for a Suggestion
     */
    public void staffAction(int index, boolean isItApproved){
        isApproved.set(index, isItApproved);
        staffActionUpdate(index);
    }

    /**
     * Get user identification for the committee member who submitted the suggestion
     * 
     * @param index Index positioning of a suggestion in list of suggestion
     * @return User identification of Suggestor
     */
    public String getUserID(int index){
        return suggestor.get(index).getUserID();
    }

    /**
     * Get approval status of a given suggestion
     * 
     * @param index Index position of suggestion in list of suggestions
     * @return Approval status of a suggestion
     */
    public boolean isItApproved(int index){
        return isAnswered.get(index);
    }

    /**
     * To be used to ensure only staff of that specific camp can view suggestions
     * Camp Name is a unique ID and will be returned to do the check
     * 
     * @param index Index positioning of a suggestion in list of suggestions
     * @return Camp name of Camp that received the specific suggestion
     */
    public String getCampEnquiredID(int index){
        return suggestedCamp.get(index).getCampName();
    }

    /**
     * Fetches unique identification number of suggestion given its current index
     * 
     * @param index Index positioning of suggestion in list of suggestions
     * @return Unique identification number of suggestion
     */
    public int getSuggestionID(int index){
        return suggestionID.get(index);
    }

    /**
     * To be used by Replies Class to get the current index of an suggestion using the id as reference
     * This will allow Replies Class to call the other methods using the id
     * returns the index position of the enquiry with the corresponding userID
     * 
     * @param id Unique identification number of a suggestion
     * @return Current index position of a suggestion
     */
    public int getIndexFromID(int id){
        for(int i = 0; i < suggestionID.size(); i++){
            if(getSuggestionID(i) == id){
                return i;
            }
        }
        return -1;
    }

    /**
     * Used by committee member class to print out all their suggestions
     * Used by staff class to print out all corresponding suggestions
     * returns arraylist containing the indexes of all enquiries, this will allow committee member to edit or delete their suggestions
     * This will also allow staff to see suggestions that they can approve or deny
     * boolean param isStaff to differentiate staff and committee member version of printAllSuggestions
     * using the return arraylist during console prompt
     * Return list of suggestionIDs
     * 
     * @param camp Specific camp to print suggestions for
     * @param userID User identification of User who is trying to print all suggestions
     * @param isStaff Status of user as Staff member
     * @return List of Unique identification number of suggestions
     */
    public ArrayList<Integer> printAllSuggestions(Camp camp, String userID, boolean isStaff){
        ArrayList<Integer> returnIndexes = new ArrayList<Integer>();
       int count = 0;
        if(isStaff){
            for(int i = 0; i < suggestions.size(); i++){
                if(userID == suggestedCamp.get(i).getStaffID()){
                    System.out.print("Camp: " + camp.getCampName());
                    System.out.print("Suggestion ID: " + suggestionID.get(i)+ " : ");
                    count++;
                    System.out.println(suggestions.get(i));
                    returnIndexes.add(i);
                }
            }
        }
        else{
            for(int i = 0; i < suggestions.size(); i++){
                if(userID == suggestor.get(i).getUserID()){
                    System.out.print("Suggestion ID: " + suggestionID.get(i)+ " : ");
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

   /**
    * Prints all suggestions for a Staff for all their camps
    * @param userID User identification of Staff object
    * @param database Database of Users
    * @param forApprovalUse Whether the method is called for approval of suggestions or only viewing
    * @return Presence of suggestion for a staff
    */
    public boolean printAllSuggestions(String userID, Database database, boolean forApprovalUse){
        int count = 0;
        User staff = database.getUser(userID);
        if(forApprovalUse){
            /*
             * For staff use when trying to approve suggestions
             * Only print out suggestions that are not yet approved
             */
            for(int i = 0; i < suggestions.size(); i++){
            Camp currentCamp = suggestedCamp.get(i);
            if(currentCamp.verifyStaff(staff) && !isItApproved(i)){
                System.out.print("Camp: " + currentCamp.getCampName());
                System.out.println(", Suggestion ID: " + suggestionID.get(i)+ " : ");
                System.out.println(suggestions.get(i));
                count++;
            }
        }
    }
        else{
            /*
             * For staff viewing purpose only
             */
            for(int i = 0; i < suggestions.size(); i++){
            Camp currentCamp = suggestedCamp.get(i);
            if(currentCamp.verifyStaff(staff)){
                System.out.print("Camp: " + currentCamp.getCampName());
                System.out.println(", Suggestion ID: " + suggestionID.get(i)+ " : ");
                System.out.println(suggestions.get(i));
                count++;
            }
        }
        }

        if(count == 0){
            System.out.println("There are no suggestions for you to view!");
            return false;
        }
        return true;

    }   
}
