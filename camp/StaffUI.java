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



public class StaffUI implements ReportGeneration{
	
	public static void main(CampInfo campInfo, Database database, ListOfEnquiries enquiries, ListOfSuggestions suggestions,
                             ReplyToStudent replyToStudent,Time time, Staff staff)
	{
        boolean exit = false;
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            try{
                System.out.println("--- Staff Menu ---");
                System.out.println("1. Create/Edit/View Camps");
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
                    /*
                    * Allow staff to view, create or edit camps
                    */
                    staffCamps(campInfo, database, time, staff);
                    break;
                case 2:
                    /*
                     * Call enquiries UI
                     */
                    staffEnquiries(staff, enquiries, replyToStudent, campInfo);
                    break;
                case 3:
                    /*
                     * Call suggestion UI
                     */
                    SuggestionsUI.staffSuggestions(suggestions, staff.getUserID(), database, campInfo, time);
                    break;
                case 4:
                    ReportUI.report(staff, campInfo);
                    break;
                case 5:
                    /*
                    * Change password
                    */
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
            System.out.println("5. Go back to main menu");
            System.out.println("Enter your choice: ");
            int campChoice = sc.nextInt();
            switch(campChoice){
                case 1:
                    /*
                     * Prompt staff to create camp
                     */
                	staff.createCamp(campInfo, database, time);
                	break;
                case 2:
                    System.out.println("Your choice of camps are: ");
                	staff.printListOfCamps();
                	System.out.println("Enter the numerical index of the camp you want to edit: ");
                    int index = sc.nextInt();
                	staff.editCamp((index - 1), staff.getListOfCamps().get(index - 1) , campInfo, database, time);
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
                    exitCamp = true;                           
                    break;
                default:
                    System.out.println("Invalid choice");
                }
            }
	
		
    }
	
	
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
                    /*
                     * Print all replies with corresponding enquiries
                     */
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

    
}