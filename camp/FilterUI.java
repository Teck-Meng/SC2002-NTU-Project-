
import java.util.InputMismatchException;
import java.util.Scanner;

import camppackage.ValidateDate;
import user.Faculty;

/**
 * Class responsible for filters for report generation
 */
public class FilterUI {
    /**
     * System prompt asking user which faculty filter the user wishes to apply for report
     * @return Faculty that will be filtered
     */
    protected static Faculty askFaculty(){
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;
        while(userChoice < 1 || userChoice > 16){
            try{
                System.out.println("Enter which user group you wish to print out later in report generation: ");
                System.out.println("1. EEE, 2. ADM, 3. NBS");
                System.out.println("4. CCEB, 5. CEE, 6. MSE");
                System.out.println("7. SCSE, 8. MAE, 9. SOH");
                System.out.println("10. SSS, 11. WKWSCI, 12. SPMS");
                System.out.println("13. SBS, 14. ASE, 15. LKCMED");
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Input Mismatch! Please enter an integer value for the date!");
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
        }
        return userGroup;
    }

    /**
     * System prompt asking user which location filter the user wishes to apply for report
     * @return Location that will be filtered
     */
    protected static String askLocation(){
        Scanner sc = new Scanner(System.in);
        String location;

        System.out.println("Enter the location of camp you wish to print out in report generation later:");
        location = sc.nextLine();
        return location;
    }

    /**
     * System prompt asking user which date filter the user wishes to apply for report
     * @return Date that will be filtered
     */
    protected static int askDate(){
        Scanner sc = new Scanner(System.in);
        int date = -1;

        while(true){
            System.out.println("Enter the date for which all active camps happening on that day will be printed in report later:");
            System.out.println("Enter in the format DDMMYYYY: ");
            try{
                date = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Kindly enter a valid integer input");
            }

            if(ValidateDate.isDateValid(date)){
                break;
            }
            else{
                System.out.println("Kindly enter a valid date!");
            }
        }

        return date;
    }
}
