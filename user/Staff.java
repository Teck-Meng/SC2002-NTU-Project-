package user;

public class Staff extends User{
    public void test(){
        System.out.println("morb");
    }

    public Staff(String UserID, Faculty facultyInfo){
        super(UserID, facultyInfo);
    }
}
