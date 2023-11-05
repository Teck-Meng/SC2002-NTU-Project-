package enquiry;

import java.util.ArrayList;

public class ReplyToStudent implements Replies{
    ArrayList<String> replies = new ArrayList<String>();
    /*
     * Points towards the index of the enquiry at question using corresponding enquiryID
     */
    ArrayList<Integer> ptrToEnquiry = new ArrayList<Integer>();


    public void addReply(String reply, int index, listOfEnquiries list){
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
