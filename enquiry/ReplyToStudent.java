package enquiry;

import java.util.ArrayList;

public class ReplyToStudent implements Replies{
    private ArrayList<String> replies = new ArrayList<String>();
    /*
     * Points towards the index of the enquiry at question using corresponding enquiryID
     */
    private ArrayList<Integer> ptrToEnquiry = new ArrayList<Integer>();


    public void addReply(String reply, int ID, ListOfEnquiries list){
        replies.add(reply);
        ptrToEnquiry.add(ID);
        /*
         * Update list that the enquiry has been answered
         */
        int index = list.getIndexFromID(ID);
        list.answeredEnquiry(index);
    }

    public String getReply(int index){
        return replies.get(index);
    }

    public String getReplyFromPtr(int id){
        for(int i = 0; i < replies.size(); i++){
            if(ptrToEnquiry.get(i) == id){
                return replies.get(i);
            }
        }
        return null;
    }
}
