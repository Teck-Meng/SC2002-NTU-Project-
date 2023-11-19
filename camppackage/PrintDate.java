package camppackage;

public class PrintDate {
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
