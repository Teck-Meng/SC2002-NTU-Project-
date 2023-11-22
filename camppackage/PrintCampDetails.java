package camppackage;

/**
 * Class responsible for printing camp details on console
 */
public class PrintCampDetails {
    /**
     * Method that prints camp details on console
     * @param camp Camp whose details are to be printed on console
     */
    public static void print(Camp camp){
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.print("Start Date: ");

        int[] dates = camp.getDates();

        PrintDate.print(dates[0]);
        System.out.print(" End Date: ");
        PrintDate.print(dates[1]);
        System.out.print(" Registration Closing Date: ");
        PrintDate.print(camp.getRegClosingDate());
        System.out.println();
        System.out.print("User Group: "+ (camp.getUserGroup()).toString()+ " ");
        System.out.println("Location: " + camp.getLocation());
        System.out.print("Total Slots: " + camp.getTotalSlots() + " ");
        System.out.println("Camp Committee Slots: " + camp.getCampCommitteeSlots());
        System.out.println("Camp Description: " + camp.getDescription() + " ");
        
    }

    /**
     * Method that prints remaining slots of a camp on console
     * @param camp Camp whose details are to be printed on console
     * @param campInfo Database of Camps
     */
    public static void printRemainingSlots(Camp camp, CampInfo campInfo){
        int attendeeSlotsLeft = campInfo.getAttendeeSlotsUsed(camp);
        int committeeSlotsLeft = campInfo.getCampCommitteeSlotsUsed(camp);
        System.out.println("Attendee Slots taken: " + attendeeSlotsLeft + " Camp Committtee Slots taken: " + committeeSlotsLeft);
        System.out.println("--------------------------------------------");
    }
}
