package user;

import java.util.InputMismatchException;
import java.util.Scanner;

//to be deleted later, this is a test file
public class test {
    public static void main(String[] args) {
        User u = new User("Bob", Faculty.ADM);
        User a = new Student("Dick", Faculty.ADM);
        User b = new Staff("Harry", Faculty.ADM);


        System.out.println(u instanceof Student);
        System.out.println(a instanceof Student);
        System.out.println(b instanceof Student);

    }
}
