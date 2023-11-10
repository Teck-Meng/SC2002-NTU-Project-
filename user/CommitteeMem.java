package user;

import enquiry.*;
import camppackage.*;
import java.util.ArrayList;

import camppackage.Camp;

public class CommitteeMem extends User {

    private static ArrayList<Camp> registeredCamps;
    private ArrayList<String> suggestions;
    private int points;

    public CommitteeMem(String userID, Faculty facultyInfo) {
        super(userID, facultyInfo);

        registeredCamps = new ArrayList<Camp>();
        suggestions = new ArrayList<String>();
        points = 0;
    }

    public static void viewCampDetails(Camp camp) {

        // View details of the specific camp the committee member is registered for
        if (registeredCamps.contains(camp)) {
            // Print or display the details of the specific camp
            System.out.println("Camp Name: " + camp.getCampName());
            System.out.println("Dates: " + camp.getDates()[0] + " to " + camp.getDates()[1]);
            System.out.println("Registration Closing Date: " + camp.getRegClosingDate());
            System.out.println("User Group: " + camp.getUserGroup());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Total Slots: " + camp.getTotalSlots());
            System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
            System.out.println("Description: " + camp.getDescription());

            // Additionally, you might display more details as required
            // Access attendee list, committee list, etc. depending on permissions
        } else {
            System.out.println("You are not registered for this camp or do not have permission to view details.");
        }

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
