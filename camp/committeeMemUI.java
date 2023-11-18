import java.util.InputMismatchException;
import java.util.Scanner;
import user.*;
import report.ReportGeneration;
import filehandler.Database;
import camppackage.Camp;
import enquiry.ListOfEnquiries;
import enquiry.ListOfSuggestions;
import enquiry.ReplyToStudent;
import enquiry.ReplyToSuggestion;

public class committeeMemUI {

    public void main(Camp camp, String userID, Database database, ListOfEnquiries enquiries, ListOfSuggestions suggestions, ReplyToStudent replyToStudent) {
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
                        /*
                        * Print all the camp details
                        */
                        camp.print();
                        break;
                    case 2:
                        modifySuggestion(camp, userID, database, suggestions);
                        break;
                    case 3:
                        replyToEnquiries(enquiries, camp, userID, replyToStudent, database);
                        break;
                    case 4:
                        ReportGeneration reportGeneration = new ReportGeneration();
                        reportGeneration.printReport(userID, database);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice");
                    }

                    }

                 } 
                    
        /*
         * Call this method when committee member wishes to modify suggestion 
         */
        private void modifySuggestion(Camp camp, String userID, Database database, ListOfSuggestions suggestions){
            Scanner sc = new Scanner(System.in);
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
                        suggestions.printAllSuggestions(camp, userID, false);
                        break;
                    case 3:
                        System.out.println("Enter the suggestion ID of the suggestion you want to edit: ");
                        int suggestionID = sc.nextInt();
                        sc.nextLine(); // Consume the newline character left by previous nextInt()
                        System.out.println("Enter your new suggestion: ");
                        String newSuggestion = sc.nextLine();
                        suggestions.editSuggestion(suggestionID, userID, newSuggestion);
                        break;
                    case 4:
                        System.out.println("Enter the suggestion ID of the suggestion you want to delete: ");
                        int suggestionID2 = sc.nextInt();
                        sc.nextLine(); // Consume the newline character left by previous nextInt()
                        suggestions.deleteSuggestion(suggestionID2, userID);
                        break;
                    case 5:
                        manage = false;                           
                        break;
                    default:
                        System.out.println("Invalid choice");
                    }                   
                }
                sc.close();
        }

        private void replyToEnquiries(ListOfEnquiries enquiries, Camp camp, String userID, ReplyToStudent replyToStudent, Database database){
            Scanner sc = new Scanner(System.in);

            enquiries.printAllEnquiries(camp);
            System.out.println("Enter the enquiry ID of the enquiry you want to reply to: ");
            int enquiryID = sc.nextInt();
            sc.nextLine(); // Consume the newline character left by previous nextInt()
            System.out.println("Enter your reply: ");
            String reply = sc.nextLine();
            replyToStudent.addReply(reply, enquiryID, enquiries);

            //points added to committee member

            Student currentUser = (Student)database.getUser(userID);
            currentUser.addCommitteePoints(1);

            sc.close();
        }
}
