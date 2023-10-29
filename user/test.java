package user;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Exception;

//to be deleted later, this is a test file
public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        while(x == 0){
            try{
            System.out.println("Enter");
            x = sc.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("bad");
            sc.nextLine();
        }
        
        }



    }
}
