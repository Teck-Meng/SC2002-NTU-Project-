
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import user.*;
import filehandler.Database;
import filehandler.PasswordManager;
import camppackage.*;
import enquiry.*;
import report.*;
import clock.Time;

/**
 * Staff interface
 */
public class StaffUI implements ReportGeneration{
	/**
     * Method that opens up staff interface
     * 
     * @param campInfo Database of camps
     * @param database Database of users
     * @param enquiries List of enquiries
     * @param suggestions List of suggestions
     * @param replyToStudent List of replies
     * @param time Current date
     * @param staff Staff object that opened the staff interface
     */
	protected static void main(CampInfo campInfo, Database database, ListOfEnquiries enquiries, ListOfSuggestions suggestions,
                             ReplyToStudent replyToStudent,Time time, Staff staff)
	{
        boolean exit = false;
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            try{
                System.out.println("--- Staff Menu ---");
                System.out.println("1. Create/Edit/View/Delete Camps");
                System.out.println("2. View/Reply Enquiries");
                System.out.println("3. View/Approve Suggestions");
                System.out.println("4. Generate Report");
                System.out.println("5. Change Password");
                System.out.println("6. Exit");
                System.out.println("Enter your choice: ");

                choice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter an integer option!");
                sc.nextLine();
            }
            
            switch (choice) {
                case 1:
                    staffCamps(campInfo, database, time, staff);
                    break;
                case 2:
                    staffEnquiries(staff, enquiries, replyToStudent, campInfo);
                    break;
                case 3:
                    SuggestionsUI.staffSuggestions(suggestions, staff.getUserID(), database, campInfo, time);
                    break;
                case 4:
                    ReportUI.report(staff, campInfo);
                    break;
                case 5:
                    int index = database.getUserIndex(staff.getUserID());
                    PasswordManager.changePassword(index, database);
                    while(true){
                        int id = UserAuthenticator.verifyLogin(database);
                        if(id > -1){
                            break;
                        }
                    }
                    break;
                case 6:
                    int choiceToExit = -1;
                    while(choiceToExit == -1){
                        try{
                            System.out.println("Are you sure you want to exit from CAM?:");
                            System.out.println("Enter 1 if you wish to exit CAM");
                            System.out.println("Enter any other number if you do not wish to exit CAM");
                            System.out.println("Enter your choice: ");
                            choiceToExit = sc.nextInt();
                            sc.nextLine();
                        }
                        catch(InputMismatchException e){
                            sc.nextLine();
                            System.out.println("Please enter a valid integer value!");
                        }
                    }
                    if(choiceToExit == 1){
                        exit = true;
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
                }
            

        }
	} 
	
    /**
     * Camp Management interface
     * 
     * @param campInfo Database of camps
     * @param database Database of users
     * @param time Current date 
     * @param staff Staff that opened the camp management interface
     */
	private static void staffCamps(CampInfo campInfo, Database database, Time time, Staff staff)
    {
		Scanner sc = new Scanner(System.in);
        boolean exitCamp = false;
        while(!exitCamp){
            System.out.println("--- Camp Management Interface ---");
            System.out.println("1. Create new Camp");
            System.out.println("2. Edit Camp");
            System.out.println("3. View all your Camps");
            System.out.println("4. View all camps in system");
            System.out.println("5. Delete Camp");
            System.out.println("6. Go back to main menu");
            System.out.println("Enter your choice: ");
            int campChoice = sc.nextInt();
            switch(campChoice){
                case 1:
                	staff.createCamp(campInfo, database, time);
                	break;
                case 2:
                    int index;
                    while(true){
                        try{
                            System.out.println("Your choice of camps are: ");
                            staff.printListOfCamps();
                            System.out.println("Enter the numerical index of the camp you want to edit: ");
                            index = sc.nextInt();
                            break;
                        }
                        catch(InputMismatchException e){
                            sc.nextLine();
                            System.out.println("Please enter a valid integer choice");
                        }
                    }
                	staff.editCamp(staff.getListOfCamps().get(index - 1) , campInfo, database, time);
                    break;
                case 3:
                    ArrayList<Camp> listOfCamps = staff.getListOfCamps();
                    for(int i = 0; i < listOfCamps.size(); i++){
                        System.out.print((i + 1) + ": ");
                        PrintCampDetails.print(listOfCamps.get(i));
                    }
                    break;
                case 4:
                    ArrayList<Camp> list = campInfo.getFullList();
                    for(int i = 0; i < list.size(); i++){
                        System.out.print((i + 1) + ": ");
                        PrintCampDetails.print(list.get(i));
                    }
                    break;
                case 5:
                    int index1 = promptDeleteCamp(staff);
                    staff.deleteCamp(campInfo.getCamp(index1), campInfo);
                case 6:
                    exitCamp = true;                           
                    break;
                default:
                    System.out.println("Invalid choice");
                }
            }
	
		
    }
	
    /**
     * Staff enquiry interface
     * 
     * @param staff Staff that opened the staff enquiry inerface
     * @param enquiries List of enquiries
     * @param replyToStudent List of replies
     * @param campInfo Database of camps
     */
    private static void staffEnquiries(Staff staff, ListOfEnquiries enquiries, ReplyToStudent replyToStudent, CampInfo campInfo)
    {
        Scanner sc = new Scanner(System.in);
        boolean exitEnquiries = false;
        while(!exitEnquiries){
            System.out.println("--- Enquiry Handling Interface ---");
            System.out.println("1. View Enquiries");
            System.out.println("2. Reply Enquiries");
            System.out.println("3. View all Replies");
            System.out.println("4. Go back to main menu");
            System.out.println("Enter your choice: ");
            int enquiryChoice = sc.nextInt();
            sc.nextLine(); 
            switch(enquiryChoice){
                case 1:
                	enquiries.printAllEnquiries(staff.getUserID(), true);
                	break;
                case 2:
                    System.out.println("The following are all your enquiries:");
                	enquiries.printAllEnquiries(staff.getUserID());
                    int enquiryID = -1;
                	while(enquiryID == -1){
                        try{
                            System.out.println("Enter the enquiry ID of the camp you want to reply to: ");
                	        enquiryID = sc.nextInt();
                        }
                        catch(InputMismatchException e){
                            sc.nextLine();
                            System.out.println("Please enter valid integer choice!");
                        }
                    }
                    sc.nextLine();
                	System.out.println("Enter your reply: ");
                	String reply = sc.nextLine();
                	replyToStudent.addReply(reply, enquiryID, enquiries);
                    break;
                case 3:
                    EnquiriesUI.printReplies(enquiries, campInfo, replyToStudent, staff);
                    break;
                case 4:
                	exitEnquiries = true; 
                    break;
                default:
                    System.out.println("Invalid choice");
                }                   
            }
    }

    /**
     * Prompts Staff to choose which camp to delete
     * @param staff Staff user trying to delete camp
     * @return Numerical choice of camp that the Staff is trying to delete
     */
    private static int promptDeleteCamp(Staff staff){
        Scanner sc = new Scanner(System.in);
        int index;
        while(true){
            try{
                System.out.println("Your choice of camps are: ");
                staff.printListOfCamps();
                System.out.println("Enter the numerical index of the camp you want to edit: ");
                index = sc.nextInt();
                break;
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer choice");
            }
        }
        return index;
    }
    
}