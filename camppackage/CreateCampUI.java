package camppackage;
import java.util.Scanner;
import user.Faculty;

public class CreateCampUI {
    /*
     * Prompt staff to enter camp name
     */
    public static String askCampName(){
        String CampName;
        Scanner sc = new Scanner(System.in);
        CampName = sc.nextLine();
        sc.close();
        return CampName;
    }

    /*
     * Prompt staff to enter the dates for the camp in the format DD/MM/YYYY
     */
    public static int[] askDates(){
        Scanner sc = new Scanner(System.in);
        int startDate = 0;
        int endDate = 0;
        int[] dates = new int[2];
        boolean validDate = false;
        while(validDate == false){
            /*
             * Prompt staff to input start date of camp and validate if staff inputted a valid date
             */
            System.out.println("Enter starting date of camp: ");
            startDate = sc.nextInt();
            validDate = ValidateDate.isDateValid(startDate);
            dates[0] = startDate;
        }
        validDate = false;
        while(validDate == false){
            /*
             * Prompt staff to input end date of camp and validate if staff inputted a valid end date
             */
            System.out.println("Enter end date of camp: ");
            endDate = sc.nextInt();
            if(ValidateDate.isDateValid(endDate)&&endDate>=startDate){
                dates[1] = endDate;
                break;
            }
        }

        sc.close();
        return dates;

    }

    public static int askRegClosingDate(){
        Scanner sc = new Scanner(System.in);
        boolean validDate = false;
        int regClosingDate = 0;
        while(validDate == false){
            /*
             * Prompt user to input registration closing date and validate the date
             * if it is invalid, prompt user to enter date again
             */
            System.out.println("Enter registration closing date of camp: ");
            regClosingDate = sc.nextInt();
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
            System.out.println("Enter your choice of user group this camp is open to: ");
            System.out.println("1. EEE, 2. ADM, 3. NBS");
            System.out.println("4. CCEB, 5. CEE, 6. MSE");
            System.out.println("7. SCSE, 8. MAE, 9. SOH");
            System.out.println("10. SSS, 11. WKWSCI, 12. SPMS");
            System.out.println("13. SBS, 14. ASE, 15. LKCMED, 16. ALL");
            userChoice = sc.nextInt();

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

    /*
     * Prompt Staff to enter the number of slots for camp committee
     */
    public static int askCampCommitteeSlots(){
        Scanner sc = new Scanner(System.in);
        int campCommitteeSlots;
        do{
            System.out.println("Enter the number of slots for the camp committee(Number to be between 1 to 10): ");
            campCommitteeSlots = sc.nextInt();
        }while(campCommitteeSlots < 1&&campCommitteeSlots > 10);

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
            System.out.println("Choose visibility of camp(1- Visible, 2- Not visible): ");
            userChoice = sc.nextInt();
        }
        
        sc.close();
        if(userChoice == 1){
            return true;
        }
        return false;
    }

}
