package password;

import java.util.Scanner;
import java.util.ArrayList;
import user.User;

public class PasswordManager {
    public static void changePassword(int index, Database database){
        /*
         * After receiving index position of user, attempt to change password
         * Password is assummed to not have any requirements for this app
         * 
         */
        Scanner sc = new Scanner(System.in);
        String newPassword, confirmPassword;
        boolean passwordChanged = false;
        do{
            System.out.println("Enter your new password: ");
            newPassword = sc.nextLine();
            System.out.println("Enter your new password again: ");
            confirmPassword = sc.nextLine();
            if(newPassword.equals(confirmPassword)){
                database.setPassword(index, newPassword);
                passwordChanged = true;
            }
            else{
                int userChoice;
                System.out.println("Password change failed, would you like to try again?: ");
                System.out.println("Enter 1 if you would like to try again");
                System.out.println("Enter any other number if you would like to quit");
                userChoice = sc.nextInt();
                if(userChoice != 1){
                    System.out.println("Quitting password changing...");
                    sc.close();
                    return;
                }
            }
        }while(passwordChanged == false);
        /*
         * Invoke database method to save password into database
         */
        sc.close();
        System.out.println("Password change is successful!");
    }

    public static boolean checkPassword(String userID, String enteredPassword, Database database){
        /*
         * Retrieve passwords and users from database
         */
        ArrayList<User> users = database.getUsers();
        ArrayList<String> passwords = database.getPasswords();
        /*
         * Iterate through users to find the index that the user is in 
         */
        int index = 0;
        for(int i = 0; i < users.size(); i++){
            if(userID.equals(users.get(i).getUserID())){
                index = i;
                break;
            }
        }
        /*
         * Verify password and return true if passwords match
         */
        return (enteredPassword.equals(passwords.get(index)));
    }


    /*
     * Overloaded checkPassword method that checks password if index of user is already known
     */
    public static boolean checkPassword(int index, String enteredPassword, Database database){
        /*
         * Retrieve passwords and users from database
         */
        ArrayList<String> passwords = database.getPasswords();
        
        /*
         * Verify password and return true if passwords match
         */
        return (enteredPassword.equals(passwords.get(index)));
    }
}
