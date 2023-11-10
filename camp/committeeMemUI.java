import java.util.InputMismatchException;
import java.util.Scanner;
import user.*;
import report.ReportGeneration;
import filehandler.Database;
import camppackage.Camp;
import enquiry.listOfEnquiries;
import enquiry.listOfSuggestions;

public class committeeMemUI {

    public void main() {
        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
                System.out.println("Camp Committee Member Menu");
                System.out.println("1. View Camp Details");
                System.out.println("2. Modify Suggestions for Camp");
                System.out.println("3. Reply to Enquiries");
                System.out.println("4. Generate Report");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");

                int choice = sc.nextInt();
                // END: ed8c6549bwf9
                switch (choice) {
                    case 1:
                        Camp camp = new Camp(userID, database);
                        camp.print();
                        break;
                    
                    case 2:
                    boolean manage = true;
                    while(manage){
                        System.out.println("1. Submit Suggestion");
                        System.out.println("2. View Suggestions");
                        System.out.println("3. Edit Suggestion");
                        System.out.println("4. Delete Suggestion");
                        System.out.println("5. Go back to main menu");
                        System.out.println("Enter your choice: ");
                        int suggestionChoice = sc.nextInt();
                        sc.nextLine(); // Consume the newline character left by previous nextInt()
                        switch(suggestionChoice){
                            case 1:
                            sc.nextLine(); // Consume the newline character left by previous nextInt()
                            case 2:
                                listOfSuggestions.printAllSuggestions(camp, userID, database);
                                break;
                            case 3:
                                System.out.println("Enter the suggestion ID of the suggestion you want to edit: ");
                                int suggestionID = sc.nextInt();
                                sc.nextLine(); // Consume the newline character left by previous nextInt()
                                System.out.println("Enter your new suggestion: ");
                                String newSuggestion = sc.nextLine();
                                listOfSuggestions.editSuggestion(suggestionID, userID, newSuggestion, camp, database);
                                break;
                            case 4:
                                System.out.println("Enter the suggestion ID of the suggestion you want to delete: ");
                                int suggestionID2 = sc.nextInt();
                                sc.nextLine(); // Consume the newline character left by previous nextInt()
                                listOfSuggestions.deleteSuggestion(suggestionID2, userID, camp, database);
                                break;
                            case 5:
                                manage = false;                           
                                break;
                            break;
                            default:
                            System.out.println("Invalid choice");
                    }                   
                    break;
                    case 3:
                        listOfEnquiries enquiries = new listOfEnquiries();
                        enquiries.printAllEnquiries(camp);
                        System.out.println("Enter the enquiry ID of the enquiry you want to reply to: ");
                    int enquiryID = sc.nextInt();
                    sc.nextLine(); // Consume the newline character left by previous nextInt()
                    System.out.println("Enter your reply: ");
                    String reply = sc.nextLine();
                    listOfEnquiries.replyToEnquiry(enquiryID, userID, reply, database);

                    //points added to committee member

                    CommitteeMem committeeMem = new CommitteeMem(userID, database);
                    committeeMem.setPoints(committeeMem.getPoints() + 1);

                    break;
                    case 4:
                    
                    break;
                    ReportGeneration reportGeneration = new ReportGeneration();
                    reportGeneration.printReport(userID, database);
                    case 5:
                    exit = true;
                    break;
                    default:
                    System.out.println("Invalid choice");
                    }

                    }

                 } 
                    
                    }
}
