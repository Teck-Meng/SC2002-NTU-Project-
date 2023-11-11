package enquiry;

import java.util.ArrayList;

public class ReplyToStudent implements Replies{
    private ArrayList<String> replies = new ArrayList<String>();
    /*
     * Points towards the index of the enquiry at question using corresponding enquiryID
     */
    private ArrayList<Integer> ptrToEnquiry = new ArrayList<Integer>();


    public void addReply(String reply, int index, ListOfEnquiries list){
        replies.add(reply);
        ptrToEnquiry.add(list.getEnquiryID(index));
        /*
         * Update list that the enquiry has been answered
         */
        list.answeredEnquiry(index);
        System.out.println("Reply added successfully!");
    }

    public String getReply(int index){
        return replies.get(index);
    }
}
