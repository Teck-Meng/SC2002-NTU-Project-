package filehandler;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import user.Faculty;
import user.Student;
import user.User;
import user.Staff;
import camppackage.CampInfo;
import camppackage.Camp;

/*
 * Class will be contain all methods to read from csv
 */
public class readFromFile extends convertToString {
    public static void main(String args[]){
        /*
         * Testing environment for read methods
         */

        Database db = readUserList();
        db = readPasswords(db);
        

        CampInfo test = readListOfCamps(db);

        readAttendeeList(test, db);
        readCommitteeList(test, db);
        readBlacklist(test, db);
       

        System.out.println("Sep");
        test.getCamp(0).getBlacklist().print();
        System.out.println();
        test.getCamp(1).getBlacklist().print();
    }



    /*
     * To read staff list and student list and translate into database
     */
    public static Database readUserList(){
        Database database = new Database();
        final int NUMBEROFLISTS = 2;
        File file = new File("./data/student_list.csv");;
        /*
         * when i == 0, student will be added into database
         * when i == 1, staff will be added into database
         */
        for(int i = 0;i < NUMBEROFLISTS; i++){
            if(i == 1){
                file = new File("./data/staff_list.csv");
            }
            try{
            
            Scanner sc = new Scanner(file);
            sc.useDelimiter(",");
            sc.nextLine();
            while(sc.hasNext()){
                
                sc.next();
                String userID = sc.next();
                int index = userID.indexOf('@');
                userID = userID.substring(0, index);
                Faculty faculty = setFaculty(sc.next());
                User user;
                if(i == 0){
                    user = new Student(userID, faculty);
                }
                else{
                    user = new Staff(userID, faculty);
                }
                database.addUser(user);
            }
            sc.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("Error! File not found! ");
        }
        catch(NoSuchElementException n){
            // Make program continue even if got this error
        }
        }
        return database;
    }

    /*
     * To be called right after calling readUserList()
     */
    public static Database readPasswords(Database database){
        File file = new File("./data/passwords.csv");
        try{
            
            Scanner sc = new Scanner(file);
            sc.useDelimiter(",");
            sc.nextLine();
            while(sc.hasNext()){
                /*
                 * Extract the index that the password is at to edit passwords database using index
                 */
                String userID = sc.next();
                int pos = database.getUserIndex(userID);
                database.initializePassword(pos, sc.next());
            }
            sc.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("Error! File not found! ");
        }
        catch(NoSuchElementException n){
            // Make program continue even if got this error
        }
        return database;
    }
    
    /*
     * To intialize campInfo object, to be called after using readPassWords()
     * Will also update staff's list of camp list
     */
    public static CampInfo readListOfCamps(Database database){
        CampInfo campInfo = new CampInfo();
        File listOfCamps = new File("./data/List_Of_Camp_Info.csv");
        File campInfoAttributes = new File("./data/CampInfo_Attrib.csv");

        try{
            Scanner listScanner = new Scanner(listOfCamps);
            Scanner attributeScanner = new Scanner(campInfoAttributes);
            listScanner.useDelimiter(",");
            attributeScanner.useDelimiter(",");
            listScanner.nextLine();
            attributeScanner.nextLine();

            /*
             * Create camp object and add to campInfo
             */
            while(listScanner.hasNext()){
                String userID = listScanner.next();
                Camp newCamp = new Camp(userID, database);

                newCamp.setCampName(listScanner.next());
                /*
                 * date init
                 */
                
                int[] dates = new int[2];
                dates[0] = stringToInt(listScanner.next());
                dates[1] = stringToInt(listScanner.next());
                newCamp.setDates(dates);
                /*
                 * regClosingDate init
                 */
                newCamp.setRegClosingDate(stringToInt(listScanner.next()));
                /*
                 * Faculty
                 */
                newCamp.setUserGroup(setFaculty(listScanner.next()));
                
                /*
                 * location init
                 */
                newCamp.setLocation(listScanner.next());
                /*
                 * totalSlots init
                 */
                newCamp.setTotalSlots(stringToInt(listScanner.next()));
                
                /*
                 * committeeSlots init
                 */
                newCamp.setCampCommitteeSlots(stringToInt(listScanner.next()));
                /*
                 * description init
                 */
                newCamp.setDescription(listScanner.next());
                campInfo.addCamp(newCamp, true);

                Staff staff = (Staff)database.getUser(userID);
                staff.addCamp(newCamp);
                listScanner.nextLine();
            }
            /*
             * Add the 3 attributes to campInfo(attendeeSlotsUsed, CommitteeSlotsUsed, Visibility)
             * attribute list syncs with list of camps so we will use indexing to update the attributes
             */
            int index = 0;
            while(attributeScanner.hasNext()){
                attributeScanner.next();
                Camp currentCamp = campInfo.getCamp(index);
                campInfo.updateAttendeeSlotUsed(true, currentCamp, stringToInt(attributeScanner.next()));
                campInfo.updateCommitteeSlotUsed(currentCamp, stringToInt(attributeScanner.next()));
                campInfo.setVisiblity(currentCamp, Boolean.parseBoolean(attributeScanner.next()));
                attributeScanner.nextLine();
                index++;
            }
            listScanner.close();
            attributeScanner.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error! File not found! ");
        }
        catch(NoSuchElementException n){
            // Make program continue even if got this error
        }
        
        return campInfo;
    }
    
    /*
     * To update attendeeList
     * Simultaneously update myCamps in student class and attendee list in camp object
     */
    public static void readAttendeeList(CampInfo campInfo, Database database){
        File attendeeList = new File("./data/Attendee_List.csv");
        try{
            
            Scanner sc = new Scanner(attendeeList);
            sc.useDelimiter(",");
            sc.nextLine();
            while(sc.hasNext()){
                /*
                 * Update attendee list and student object's myCamps simultaneously
                 */
                String userID = sc.next();
                Student attendee = (Student)database.getUser(userID);
                Camp correspondingCamp = campInfo.getCamp(sc.next());
                correspondingCamp.getAttendeeList().addAttendee(attendee);
                attendee.addCamp(correspondingCamp, false);
                sc.nextLine();
            }
            sc.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("Error! File not found! ");
        }
        catch(NoSuchElementException n){
            // Make program continue even if got this error
        }
    }
    
    /*
     * To update committeeList
     * Simultaneously update student's committee camp attribute and committee member list in camp object
     */
    public static void readCommitteeList(CampInfo campInfo, Database database){
        File committeeList = new File("./data/Committee_Member_Camp_List.csv");
        try{
            
            Scanner sc = new Scanner(committeeList);
            sc.useDelimiter(",");
            sc.nextLine();
            while(sc.hasNext()){
                /*
                 * Update committee list and student object's committee camp simultaneously
                 */
                String userID = sc.next();
                Student CCMember = (Student)database.getUser(userID);
                Camp correspondingCamp = campInfo.getCamp(sc.next());
                correspondingCamp.getCommitteeList().addCommitteeMember(CCMember);
                CCMember.addCamp(correspondingCamp, true);
                CCMember.addCommitteePoints(Integer.parseInt(sc.next()));
                sc.nextLine();
            }
            sc.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("Error! File not found! ");
        }
        catch(NoSuchElementException n){
            // Make program continue even if got this error
        }
    }
    
    /*
     * To update blacklist
     */
    public static void readBlacklist(CampInfo campInfo, Database database){
        File blacklist = new File("./data/BlackList.csv");
        try{
            
            Scanner sc = new Scanner(blacklist);
            sc.useDelimiter(",");
            sc.nextLine();
            while(sc.hasNext()){
                /*
                 * Update blacklist
                 */
                String userID = sc.next();
                Student student = (Student)database.getUser(userID);

                Camp correspondingCamp = campInfo.getCamp(sc.next());
                correspondingCamp.getBlacklist().addStudent(student);
                sc.nextLine();
            }
            sc.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("Error! File not found! ");
        }
        catch(NoSuchElementException n){
            // Make program continue even if got this error
        }
    }
}
    /*
     * To be used by readUserList to set faculty information for database
     */

