package camppackage;

/**
 * Class responsible for printing date in a beautiful manner on console
 */
public class PrintDate {
    /**
     * Method takes a date of DDMMYYYY and converts it to a string in the format DD/MM/YYYY
     * 
     * @param date Date to print
     */
    public static void print(int date){
        int year = date % 10000;
        date /= 10000;
        int month = date % 100;
        date /= 100;
        int day = date;
        if(day < 10){
            System.out.print("0" + day + "/" + month + "/" + year);
        }
        else{
            System.out.print(day + "/" + month + "/" + year);
        }
    }
}
