package enquiry;

import java.util.ArrayList;

/**
 * <p>
 * Class handling replies to Student enquiries
 * </p>
 * <p>
 * Implements Replies interface
 * </p>
 */
public class ReplyToStudent implements Replies{
    private ArrayList<String> replies = new ArrayList<String>();
    /*
     * Points towards the index of the enquiry at question using corresponding enquiryID
     */
    private ArrayList<Integer> ptrToEnquiry = new ArrayList<Integer>();

    /**
     * Method to add reply to list of replies
     * 
     * @param reply Reply to be added
     * @param ID Unique identification of an enquiry that is being replied to
     * @param list List of enquiries
     * @Override
     */
    public void addReply(String reply, int ID, ListOfEnquiries list){
        replies.add(reply);
        ptrToEnquiry.add(ID);
        int index = list.getIndexFromID(ID);
        list.answeredEnquiry(index);
    }

    /**
     * Fetches a reply given its index position in list of replies
     * 
     * @param index Index positioning of reply
     * @return Reply at index position
     * @Override
     */
    public String getReply(int index){
        return replies.get(index);
    }

    /**
     * Fetches a reply given its corresponding unique identification number
     * 
     * @param id Unique identification of enquiry for a reply
     * @return Reply
     */
    public String getReplyFromPtr(int id){
        for(int i = 0; i < replies.size(); i++){
            if(ptrToEnquiry.get(i) == id){
                return replies.get(i);
            }
        }
        return null;
    }
}
