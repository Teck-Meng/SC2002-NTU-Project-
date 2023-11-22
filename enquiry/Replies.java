package enquiry;
/**
 * Interface for replies to enquiries/suggestions
 */
public interface Replies {
    /**
     * Method to add reply
     * 
     * @param reply Reply
     * @param index Index positioning of reply
     * @param list List of enquiries
     */
    public void addReply(String reply, int index, ListOfEnquiries list);

    /**
     * Get method for a reply given its index positioning
     * 
     * @param index Index positioning of a specific reply in list of replies
     * @return Reply
     */
    public String getReply(int index);
}
