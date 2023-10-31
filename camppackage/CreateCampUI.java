package camppackage;
import java.util.InputMismatchException;
import java.util.Scanner;
import user.Faculty;
import clock.Time;

public class CreateCampUI {

    /*
     * Check if certain features of a camp can be edited
     * Criteria for allowing edit when allowEdit is called: no person has registered for the camp yet.
     */
    private static boolean allowEdit(Camp camp, CampInfo campInfo){
        if(campInfo.getAttendeeSlotUsed(camp) != 0){
            return false;
        }
        if(campInfo.getCampCommitteeSlotsUsed(camp) != 0){
            return false;
        }
        return true;
    }
    /*
     * Prompt staff to enter camp name to create a brand new camp
     */
    public static String askCampName(){
        String CampName;
        Scanner sc = new Scanner(System.in);
        CampName = sc.nextLine();
        sc.close();
        System.out.println("Camp Name successfully added/changed!");
        return CampName;
    }
    /*
     * Prompt staff to enter camp name and allow editing of camp name
     * Assume that camp name cannot be edited if at least 1 person has registered for the camp
     * return empty string if unable to edit camp name
     */
    public static String askCampName(Camp camp, CampInfo campInfo){
        /*
         * Ensure that camp name can be edi
         */
        if(!allowEdit(camp, campInfo)){
            System.out.println("Unable to edit camp name as camp has at least 1 person registered!");
            return "";
        }
        String CampName;
        Scanner sc = new Scanner(System.in);
        CampName = sc.nextLine();
        sc.close();
        System.out.println("Camp Name successfully added/changed!");
        return CampName;
    }

    /*
     * Prompt staff to enter the dates for the camp in the format DD/MM/YYYY
     */
    public static int[] askDates(Time time){
        Scanner sc = new Scanner(System.in);
        int startDate = 0;
        int endDate = 0;
        int[] dates = new int[2];
        boolean validDate = false;
        while(validDate == false){
            /*
             * Prompt staff to input start date of camp and validate if staff inputted a valid date
             */
            while(startDate <= time.getDate()){
                /*
                 * Prompt user to enter date till they enter a valid date
                 */
                try{
                    System.out.println("Enter starting date of camp: ");
                    startDate = sc.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("Input Mismatch! Please enter an integer value for the date!");
                    /*
                    * Use of sc.nextLine() to prevent infinite looping
                    */
                    sc.nextLine();
                }
                /*
                 * Staff must enter a date in the future, send a warning message to staff if they fail to do so
                 */
                if(startDate <= time.getDate()){
                    System.out.println("Invalid date input! Enter a date from tomorrow onwards!");
                }
            }
            validDate = ValidateDate.isDateValid(startDate);
            dates[0] = startDate;
        }
        validDate = false;
        while(validDate == false){
            /*
             * Prompt staff to input end date of camp and validate if staff inputted a valid end date
             */
            while(endDate <= time.getDate()){
                try{
                    System.out.println("Enter end date of camp: ");
                    endDate = sc.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("Input Mismatch! Please enter an integer value for the date!");
                    /*
                    * Use of sc.nextLine() to prevent infinite looping
                    */
                    sc.nextLine();
                }
                /*
                 * Staff must enter a date in the future, send a warning message to staff if they fail to do so
                 */
                if(startDate <= time.getDate()){
                    System.out.println("Invalid date input! Enter a date from tomorrow onwards!");
                }
            }
            if(ValidateDate.isDateValid(endDate)&&endDate>=startDate){
                dates[1] = endDate;
                break;
            }
        }

        sc.close();
        return dates;

    }

    public static int askRegClosingDate(Time time){
        Scanner sc = new Scanner(System.in);
        boolean validDate = false;
        int regClosingDate = 0;
        while(validDate == false){
            /*
             * Prompt user to input registration closing date and validate the date
             * if it is invalid, prompt user to enter date again
             */
            while(regClosingDate <= time.getDate()){
                /*
                 * Use of try-catch block to continuously prompt for input till the input is valid
                 */
                try{
                    System.out.println("Enter registration closing date of camp: ");
                    regClosingDate = sc.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("Input Mismatch! Please enter an integer value for the date!");
                    /*
                    * Use of sc.nextLine() to prevent infinite looping
                    */
                    sc.nextLine();
                }
                /*
                 * Prompt user to enter a date in the future, send warning message if staff fails to do so
                 */
                if(regClosingDate <= time.getDate()){
                    System.out.println("Invalid date input! Enter a date from tomorrow onwards!");
                }
            }
            validDate = ValidateDate.isDateValid(regClosingDate);
        }
        sc.close();
        return regClosingDate;
    }

    public static Faculty askUserGroup(){
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;
        while(userChoice<1&&userChoice>16){
            /*
             * Prompt staff to input the user group of the camp
             */
            try{
                System.out.println("Enter your choice of user group this camp is open to: ");
                System.out.println("1. EEE, 2. ADM, 3. NBS");
                System.out.println("4. CCEB, 5. CEE, 6. MSE");
                System.out.println("7. SCSE, 8. MAE, 9. SOH");
                System.out.println("10. SSS, 11. WKWSCI, 12. SPMS");
                System.out.println("13. SBS, 14. ASE, 15. LKCMED, 16. ALL");
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Input Mismatch! Please enter an integer value for the date!");
                /*
                 * Use of sc.nextLine() to prevent infinite looping
                 */
                sc.nextLine();
            }

        }
        Faculty userGroup = Faculty.ALL;
        switch(userChoice){
            case 1:
                userGroup  = Faculty.EEE;
                break;
            case 2:
                userGroup  = Faculty.ADM;
                break;
            case 3:
                userGroup  = Faculty.NBS;
                break;
            case 4:
                userGroup  = Faculty.CCEB;
                break;
            case 5:
                userGroup  = Faculty.CEE;
                break;
            case 6:
                userGroup  = Faculty.MSE;
                break;
            case 7:
                userGroup  = Faculty.SCSE;
                break;
            case 8:
                userGroup  = Faculty.MAE;
                break;
            case 9:
                userGroup  = Faculty.SOH;
                break;
            case 10:
                userGroup  = Faculty.SSS;
                break;
            case 11:
                userGroup  = Faculty.WKWSCI;
                break;
            case 12:
                userGroup  = Faculty.SPMS;
                break;
            case 13:
                userGroup  = Faculty.SBS;
                break;
            case 14:
                userGroup  = Faculty.ASE;
                break;
            case 15:
                userGroup  = Faculty.LKCMED;
                break;
            case 16:
                userGroup  = Faculty.ALL;
                break;
        }
        sc.close();
        return userGroup;
    }

    /*
     * Prompt staff to set location of camp
     */
    public static String askLocation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter location name of camp: ");
        String location = sc.nextLine();
        sc.close();
        return location;
    }

    /*
     * Prompt staff to indicate the total number of slots available for the camp
     */
    public static int askTotalSlots(){
        Scanner sc = new Scanner(System.in);
        int totalSlots = 0;
        /*
         * Camp total slots minimum is 2 as there are at least 1 attendee and 1 committee member in a camp
         */
        while(totalSlots < 2){
            System.out.println("Enter the number of total slots for this camp(Number to be above 1): ");
            totalSlots = sc.nextInt();
        }
        sc.close();
        return totalSlots;
    }

    public static int askAttendeeSlots(Camp camp, CampInfo campInfo){
        Scanner sc = new Scanner(System.in);
        int attendeeSlots = 0;
        // ensure that the edited amount of slots can accomodate the current registered attendees
        int minSlots = campInfo.getAttendeeSlotUsed(camp);

        while(attendeeSlots < minSlots){
            try{
                System.out.println("There are currently " + minSlots +"attendees in this camp");
                System.out.println("Kindly enter the number of attendee slots you want as of now(At least " + minSlots + "): ");
                System.out.println("Select -1 if you wish to quit editing");
                attendeeSlots = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid integer input!");
                sc.nextLine();
            }
            if(attendeeSlots == -1){
                /*
                 * Exit method if staff wishes to quit editing
                 */
                return -1;
            }
        }
        return attendeeSlots;
    }

    /*
     * Prompt Staff to enter the number of slots for camp committee
     */
    public static int askCampCommitteeSlots(){
        Scanner sc = new Scanner(System.in);
        int campCommitteeSlots = 0;
        do{
            try{
                
                System.out.println("Enter the number of slots for the camp committee(Number to be between 1 to 10): ");
                campCommitteeSlots = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Input Mismatch! Please enter an integer value for the date!");
                /*
                 * Use of sc.nextLine() to prevent infinite looping
                 */
                sc.nextLine();
            }
        }while(campCommitteeSlots < 1&&campCommitteeSlots > 10);

        sc.close();
        return campCommitteeSlots;
    }

    /*
     * Edit version of askCommitteeListCamp()
     */
    public static int askCampCommitteeSlots(Camp camp, CampInfo campInfo){
        Scanner sc = new Scanner(System.in);
        int campCommitteeSlots = 0;
        int minSlots = campInfo.getCampCommitteeSlotsUsed(camp);
        if(minSlots == camp.getCampCommitteeSlots()){
            System.out.println("Unable to process editing as capacity minimum is capacity maximum");
            return -1;
        }

        do{
            try{
                System.out.println("There are currently " + minSlots +"attendees in this camp");
                System.out.println("Kindly enter the number of attendee slots you want as of now(At least " + minSlots + "): ");
                System.out.println("Select -1 if you wish to quit editing");
                campCommitteeSlots = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Input Mismatch! Please enter an integer value for the date!");
                /*
                 * Use of sc.nextLine() to prevent infinite looping
                 */
                sc.nextLine();
            }
            if(campCommitteeSlots == -1){
                /*
                 * Exit method if user wishes to quit
                 */
                return -1;
            }
        }while(campCommitteeSlots < minSlots&&campCommitteeSlots > 10);

        sc.close();
        return campCommitteeSlots;
    }
    /*
     * Prompt staff to enter description of camp
     */
    public static String askDescription(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter description of camp: ");
        String description = sc.nextLine();
        sc.close();
        return description;
    }


    /*
     * Prompt Staff to set visiblity of the camp
     */
    public static boolean askVisibility(){
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;
        while(userChoice != 1&&userChoice != 2){
            try{
                System.out.println("Choose visibility of camp(1- Visible, 2- Not visible): ");
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Input Mismatch! Please enter an integer value for the date!");
                /*
                 * Use of sc.nextLine() to prevent infinite looping
                 */
                sc.nextLine();
            }
        }
        
        sc.close();
        if(userChoice == 1){
            return true;
        }
        return false;
    }

}
