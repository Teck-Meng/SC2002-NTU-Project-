package user;
/**
 * Super class of staff and student
 * For login purpose
 */
public class User {
    private String userID;
    private Faculty facultyInfo;

    /**
     * Standard constructor for User object
     * 
     * @param userID User identification
     * @param facultyInfo faculty of a user
     */
    public User(String userID, Faculty facultyInfo){
        this.userID = userID;
        this.facultyInfo = facultyInfo;
    }

    /**
     * 
     * @return Faculty of a user
     */
    public Faculty getFaculty(){
        return facultyInfo;
    }

    /**
     * 
     * @return User identification
     */
    public String getUserID(){
        return userID;
    }

    
}
