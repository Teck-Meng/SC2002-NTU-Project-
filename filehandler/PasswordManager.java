package filehandler;

import java.util.Scanner;
import java.util.ArrayList;
import user.User;

/**
 * Class responsible for handling passwords
 */
public class PasswordManager {
    /**
     * System prompt to change password
     * 
     * @param index Index positioning of user trying to change their password
     * @param database Database of Users
     */
    public static void changePassword(int index, Database database){
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
                sc.nextLine();
                if(userChoice != 1){
                    System.out.println("Quitting password changing...");
                    return;
                }
            }
        }while(passwordChanged == false);
        
    }

    /**
     * Method to verify if user inputted password matches the password in the database
     * 
     * @param userID User identification for 
     * @param enteredPassword User inputted password
     * @param database Database of User
     * @return Boolean value of whether an inputted password is correct(true) or not(false)
     */
    public static boolean checkPassword(String userID, String enteredPassword, Database database){
        ArrayList<User> users = database.getUsers();
        ArrayList<String> passwords = database.getPasswords();
        int index = 0;
        for(int i = 0; i < users.size(); i++){
            if(userID.equals(users.get(i).getUserID())){
                index = i;
                break;
            }
        }
        return (enteredPassword.equals(passwords.get(index)));
    }


    /**
     * Overloaded checkPassword method that checks password if index of user is already known
     * 
     * @param index Index positioning of a User
     * @param enteredPassword User inputted password
     * @param database Database of Users
     * @return Boolean value of whether an inputted password is correct(true) or not(false)
     */
    public static boolean checkPassword(int index, String enteredPassword, Database database){
        ArrayList<String> passwords = database.getPasswords();
        
        return (enteredPassword.equals(passwords.get(index)));
    }
}
