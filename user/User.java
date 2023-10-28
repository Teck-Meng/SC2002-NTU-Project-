package user;

public class User {
    private String userID;
    private String password;
    private Faculty facultyInfo;

    //Use constructor when reading data from csv log for our test cases
    public User(String userID, Faculty facultyInfo){
        this.userID = userID;
        this.password = "password";
        this.facultyInfo = facultyInfo;
    }

    public Faculty getFaculty(){
        return facultyInfo;
    }

    public String getUserID(){
        return userID;
    }
}
