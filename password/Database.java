package password;

import java.util.ArrayList;
import user.User;

public class Database {
    // To add all users into this array list
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> passwords = new ArrayList<String>();

    public ArrayList<User> getUsers(){
        return users;
    }

    protected ArrayList<String> getPasswords(){
        return passwords;
    }

    public void addUser(User user){
        /*
         * To call this method during database intialization 
         * Password assumed to be the default "password" initially
         */
        users.add(user);
        passwords.add("password");
    }

    public User getUser(String userID){
        for(int i = 0; i < users.size(); i++){
            if(userID.equals(users.get(i).getUserID())){
                return users.get(i);
            }
        }
        return null;
    }

    public int getUserIndex(String userID){
        for(int i = 0; i < users.size(); i++){
            System.out.print(userID);
            System.out.print(users.get(i).getUserID());
            System.out.println();
            if(userID.equals(users.get(i).getUserID())){
                System.out.println("no");
                return i;
            }
        }
        return -1;
    }

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

    
    /*
     * Method overloading of SetPassword method
     * If index already known, prompt this version to set password
     */
    protected void setPassword(int index,String newPassword){
        /*
         * Find specific user id to change the password for that particular user
         */
        passwords.set(index, newPassword);
        System.out.println("Password change is successful!");
    }

    /*
     * For file handler to set password without system prompt
     */
    public void initializePassword(int index, String password){
        passwords.set(index, password);
    }

    /*
     * Testing only, later delete
     */
    public void printAllUsers(){
        for(int i = 0; i <users.size();i++){
            System.out.print("user id: "+users.get(i).getUserID());
            System.out.print("faculty: "+users.get(i).getFaculty().toString());
            System.out.println("pw: "+passwords.get(i));
            System.out.println();
        }
    }
}
