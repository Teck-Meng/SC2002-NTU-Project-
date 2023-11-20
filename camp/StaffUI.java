import java.util.InputMismatchException;
import java.util.Scanner;
import user.*;
import report.ReportGeneration;
import filehandler.Database;
import camppackage.*;
import enquiry.*;
import report.*;
import clock.Time;



public class StaffUI implements ReportGeneration{
	
	public void main(Camp camp, CampInfo campinfo, String userID, Database database, 
			ListOfEnquiries enquiries, ListOfSuggestions suggestions, ReplyToStudent replyToStudent,
			ReplyToSuggestion replytosuggestion, Time time, Staff staff,
			PerformanceReport perfreport, AttendanceReport attreport)
	{
        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
                System.out.println("Welcome to the Staff Menu");
                System.out.println("1. Create/Edit/View Camps");
                System.out.println("2. View/Reply Enquiries");
                System.out.println("3. View/Approve Suggestions");
                System.out.println("4. Generate Report");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");

                int choice = sc.nextInt();
            
                switch (choice) {
                    case 1:
                    	staffCamps(camp, campinfo, userID, database, time, staff);
                        break;
                    case 2:
                        staffEnquiries(userID, enquiries, replyToStudent);
                        break;
                    case 3:
                        staffSuggestions(suggestions, camp, userID, replytosuggestion);
                        break;
                    case 4:
                    	staffReports(perfreport, attreport, staff, campinfo, camp);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice");
                    }

                    }

	} 
	
	private void staffCamps(Camp camp, CampInfo campinfo, String userID, Database database, Time time, Staff staff)
    {
		Scanner sc = new Scanner(System.in);
        boolean exitCamp = false;
        while(!exitCamp){
            System.out.println("1. Create new Camp");
            System.out.println("2. Edit Camp");
            System.out.println("3. View All Camps");
            System.out.println("4. Go back to main menu");
            System.out.println("Enter your choice: ");
            int campChoice = sc.nextInt();
            sc.nextLine();
            switch(campChoice){
                case 1:
                	staff.createCamp(campinfo, database, time);
                	break;
                case 2:
                	staff.getListOfCamps();
                	System.out.println("Enter the camp ID of the camp you want to edit: ");
                    int campID = sc.nextInt();
                	staff.editCamp(campID, camp, campinfo, database, time);
                    break;
                case 3:
                    staff.printListOfCamps();
                    break;
                case 4:
                    exitCamp = true;                           
                    break;
                default:
                    System.out.println("Invalid choice");
                }
            }
            sc.close();
	
		
    }
	
	
    private void staffEnquiries(String userID, ListOfEnquiries enquiries, ReplyToStudent replyToStudent)
    {
        Scanner sc = new Scanner(System.in);
        boolean exitEnquiries = false;
        while(!exitEnquiries){
            System.out.println("1. View Enquiries");
            System.out.println("2. Reply Enquiries");
            System.out.println("3. Go back to main menu");
            System.out.println("Enter your choice: ");
            int enquiryChoice = sc.nextInt();
            sc.nextLine(); 
            switch(enquiryChoice){
                case 1:
                	enquiries.printAllEnquiries(userID, true);
                	break;
                case 2:
                	enquiries.printAllEnquiries(userID, true);
                	System.out.println("Enter the enquiry ID of the camp you want to reply to: ");
                	int enquiryID = sc.nextInt();
                	System.out.println("Enter your reply");
                	String reply = sc.nextLine();
                	replyToStudent.addReply(reply, enquiryID, enquiries);
                    break;
                case 3:
                	exitEnquiries = true; 
                    break;
                default:
                    System.out.println("Invalid choice");
                }                   
            }
            sc.close();
    }

    private void staffSuggestions(ListOfSuggestions suggestions, Camp camp, String userID, ReplyToSuggestion replytosuggestion)
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
                	replytosuggestion.replyToSuggestion(suggestionID, suggestions);
                    break;
                case 3:
                	exitSuggestions = true; 
                    break;
                default:
                    System.out.println("Invalid choice");
                }                   
            }
            sc.close();

        
    }
    
    private void staffReports(PerformanceReport perfreport, AttendanceReport attreport,
    		Staff staff, CampInfo campinfo, Camp camp)
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
                    		perfreport.printReport(camp, campinfo);
                    		break;
                    	case 2:
                        	perfreport.printReport(staff.getListOfCamps(), campinfo);
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
                            
                    		attreport.printReport(camp, campinfo);
                    		break;
                    	case 2:
                        	attreport.printReport(staff.getListOfCamps(), campinfo);
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
            sc.close();

        
    }
	
    
}
