package filehandler;

import java.util.ArrayList;
import user.User;

/**
 * Database of users
 */
public class Database {
    // To add all users into this array list
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> passwords = new ArrayList<String>();

    /**
     * Get method for list of users
     * 
     * @return List of Users
     */
    public ArrayList<User> getUsers(){
        return users;
    }

    /**
     * Get method for filehandler package to get list of passwords for authentication
     * 
     * @return List of passwords
     */
    protected ArrayList<String> getPasswords(){
        return passwords;
    }

    /**
     * Set method to add user into database, called during intialization of main program
     * 
     * @param user User to be added into database
     */
    protected void addUser(User user){
        users.add(user);
        passwords.add("password");
    }

    /**
     * Get method to get a user object from their user ID
     * 
     * @param userID User identification
     * @return Corresponding User object
     */
    public User getUser(String userID){
        for(int i = 0; i < users.size(); i++){
            if(userID.equals(users.get(i).getUserID())){
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * Overloaded method of getUser() to get a User object from their index position within the list of users
     * 
     * @param userIDIndex Index position of User in list of Users
     * @return User object
     */
    public User getUser(int userIDIndex){
        return users.get(userIDIndex);
    }

    /**
     * Retrieve index of User object given their user identification
     * 
     * @param userID User identification
     * @return Index of User within list of Users
     */
    public int getUserIndex(String userID){
        for(int i = 0; i < users.size(); i++){
            if(userID.equals(users.get(i).getUserID())){
                return i;
            }
        }
        return -1;
    }

    /**
     * Set method to set password given a User's user identification
     * 
     * @param userID User Identification
     * @param newPassword New Password
     */
    protected void setPassword(String userID, String newPassword){
        /*
         * Find specific user id to change the password for that particular user
         */
        for(int i = 0; i < passwords.size(); i++){
            if(userID.equals(users.get(i).getUserID())){
                passwords.set(i, newPassword);
                System.out.println("Password change is successful!");
                return;
            }
        }
        System.out.println("Password change is unsuccessful!");
    }

    /**
     * To be used by writeToFile class to write into passwords.csv
     * 
     * @param index Index provided from for loop during writing
     */
    protected String getPassword(int index){
        return passwords.get(index);
    }
    
    /**
     * Method overloading of SetPassword method
     * If index already known, prompt this version to set password
     * 
     * @param index Index positioning of password to be changed
     * @param newPassword New password
     */
    protected void setPassword(int index,String newPassword){
        passwords.set(index, newPassword);
        System.out.println("Password change is successful!");
    }

    /**
     * For readFromFile to set password without system prompt
     * 
     * @param index Index parameter from For loop during reading of csv
     * @param password Password to be set at the index
     */
    protected void initializePassword(int index, String password){
        passwords.set(index, password);
    }

}
