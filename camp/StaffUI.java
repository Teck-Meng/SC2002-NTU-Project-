import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import user.*;
import report.ReportGeneration;
import filehandler.Database;
import camppackage.*;
import enquiry.*;
import report.*;
import clock.Time;



public class StaffUI implements ReportGeneration{
	
	public static void main(CampInfo campinfo, Database database, ListOfEnquiries enquiries, ListOfSuggestions suggestions,
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
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");

                choice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter an integer option!");
                sc.nextLine();
            }
            
            switch (choice) {
                case 1:
                    staffCamps(campinfo, database, time, staff);
                    break;
                case 2:
                    staffEnquiries(staff, enquiries, replyToStudent, campinfo);
                    break;
                case 3:
                    // staffSuggestions(suggestions, camp, staff.getUserID());
                    break;
                case 4:
                    staffReports(staff, campinfo, database, staff.getUserID());
                    break;
                case 5:
                    exit = true;
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
            System.out.println("3. View All your Camps");
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

    private static void staffSuggestions(ListOfSuggestions suggestions, Camp camp, String userID)
    {
        Scanner sc = new Scanner(System.in);
        boolean exitSuggestions = false;
        while(!exitSuggestions){
            System.out.println("1. View Suggestions");
            System.out.println("2. Approve Suggestions");
            System.out.println("3. Go back to main menu");
            System.out.println("Enter your choice: ");
            int suggestionChoice = sc.nextInt();
            sc.nextLine(); 
            switch(suggestionChoice){
                case 1:
                	suggestions.printAllSuggestions(camp, userID, true);
                	break;
                case 2:
                	suggestions.printAllSuggestions(camp, userID, true);
                	System.out.println("Enter the suggestion ID of the camp you want to approve: ");
                	int suggestionID = sc.nextInt();
                	ReplyToSuggestion.replyToSuggestion(suggestionID, suggestions);
                    break;
                case 3:
                	exitSuggestions = true; 
                    break;
                default:
                    System.out.println("Invalid choice");
                }                   
            }

        
    }
    
    private static void staffReports(Staff staff, CampInfo campinfo, Database database, String Userid)
    {
        Scanner sc = new Scanner(System.in);
        boolean exitReports = false;
        while(!exitReports){
            System.out.println("1. Generate Attendance Report");
            System.out.println("2. Generate Performance Report");
            System.out.println("3. Go back to main menu");
            System.out.println("Enter your choice: ");
            int reportChoice = sc.nextInt();
            sc.nextLine(); 
            switch(reportChoice){
                case 1:
                	System.out.println("1. Print for one camp");
                	System.out.println("2. Print for all camps");
                	System.out.println("3. Go back to main menu");
                	int performanceChoice = sc.nextInt();
                    sc.nextLine();
                    switch(performanceChoice){
                    	case 1:
                    		staff.getListOfCamps();
                        	System.out.println("Enter the camp ID of the camp you want to print the report for: ");
                            int campID = sc.nextInt();
                            // Generate report for 1 camp only
                    		PerformanceReport.printReport(staff.getListOfCamps().get(campID), campinfo);
                    		break;
                    	case 2:
                        	PerformanceReport.printReport(staff.getListOfCamps(), campinfo);//cant call the function
                            break;
                        case 3:
                        	exitReports = true; 
                            break;
                        default:
                            System.out.println("Invalid choice");
                    
                    }
                	break;
                case 2:
                	System.out.println("1. Print for one camp");
                	System.out.println("2. Print for all camps");
                	System.out.println("3. Go back to main menu");
                	int attendanceChoice = sc.nextInt();
                    sc.nextLine();
                    switch(attendanceChoice){
                    	case 1:
                    		staff.getListOfCamps();
                        	System.out.println("Enter the camp ID of the camp you want to print the report for: ");
                            int campID = sc.nextInt();
                    		AttendanceReport.printReport(staff.getListOfCamps(), campinfo);//how to gen for 1 camp?
                    		break;
                    	case 2:
                        	AttendanceReport.printReport(staff.getListOfCamps(), campinfo);//cant call the function
                            break;
                        case 3:
                        	exitReports = true; 
                            break;
                        default:
                            System.out.println("Invalid choice");
                    
                    }
                    break;
                case 3:
                	exitReports = true; 
                    break;
                default:
                    System.out.println("Invalid choice");
                }                   
            }

        
    }
	
    
}