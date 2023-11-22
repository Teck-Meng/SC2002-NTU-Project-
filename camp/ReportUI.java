
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

import camppackage.Camp;
import camppackage.CampInfo;
import user.Faculty;
import user.Staff;
import report.AttendanceReport;
import report.Filter;
import report.PerformanceReport;

/**
 * Class responsible for handling report generation on main program
 */
public class ReportUI {
    /**
     * Method that opens up report generation interface
     * 
     * @param staff Staff that is requesting to generate report
     * @param campInfo Database of Camps
     */
    protected static void report(Staff staff, CampInfo campInfo){
        Scanner sc = new Scanner(System.in);
        int userChoice = -1;
        boolean filterAdded = false;
        ArrayList<Camp> listOfCamps = new ArrayList<Camp>();
        boolean exit = false;

        while(exit == false){
            System.out.println("---  Welcome to the report generation interface ---");
            System.out.println("Note: Filters will be applied to both reports if you add them");
            System.out.println("To reset filter, exit this interface and re-enter it");
            System.out.println("1. Generate Attendance Report");
            System.out.println("2. Generate Performance Report");
            System.out.println("3. Add filter");
            System.out.println("4. Exit");
            System.out.println("Enter you choice here:");
            try{
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer choice!");
            }

            switch(userChoice){
                case 1:
                    if(filterAdded){
                        attendanceReportGen(listOfCamps, staff, campInfo);
                    }
                    else{
                        attendanceReportGen(staff.getListOfCamps(), staff, campInfo);
                    }
                    break;
                case 2:
                    if(filterAdded){
                        performanceReportGen(listOfCamps, staff, campInfo);
                    }
                    else{
                        performanceReportGen(staff.getListOfCamps(), staff, campInfo);
                    }
                    break;
                case 3:
                    listOfCamps = filterUI(listOfCamps, staff.getListOfCamps(), filterAdded);
                    if(listOfCamps.size() != 0){
                        filterAdded = true;
                    }
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting report generation interface . . .");
                    break;
                default:
                    System.out.println("Invalid input!");
            }

        }
    }

    /**
     * Attendance Report generation interface
     * 
     * @param list List of Camps to generate report for
     * @param staff Staff that requested for report generation
     * @param campInfo Database of Camps
     */
    private static void attendanceReportGen(ArrayList<Camp> list, Staff staff, CampInfo campInfo){
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        int userChoice = -1;

        while(exit == false){
            System.out.println("--- How would you like to generate your attendance report ---");
            System.out.println("1. For all my camps");
            System.out.println("2. For only a specific camp(Filters will not be applied)");
            System.out.println("3. Exit");

            try{
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer option! ");
            }

            switch(userChoice){
                case 1:
                    AttendanceReport.printReport(list, campInfo);
                    break;
                case 2:
                    printForOneCamp(campInfo, staff, true);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
    }

    /**
     * Prints out attendance report or performance report for only one camp
     * 
     * @param campInfo Database of Camps
     * @param staff Staff requesting to print attendance report
     * @param isAttdReport Boolean value denoting if the report to print is an attendance report or performance report
     */
    private static void printForOneCamp(CampInfo campInfo, Staff staff, boolean isAttdReport){
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        String campName;

        while(!exit){
            staff.printListOfCamps();
            System.out.println("Enter the camp name that you wish to print report for: ");
            campName = sc.nextLine();
            Camp camp = campInfo.getCamp(campName);
            if(camp == null){
                System.out.println("Camp not found in database! Please try again!");
            }
            else if(isAttdReport){
                AttendanceReport.printReport(camp, campInfo);
                exit = true;
            }
            else{
                PerformanceReport.printReport(camp, campInfo);
                exit = true;
            }
        }
    }

    /**
     * Generates performance report for all camps created by the staff
     * 
     * @param list List of camps that will be included in the performance report
     * @param staff Staff requesting to generate performance report
     * @param campInfo Database of Camps
     */
    private static void performanceReportGen(ArrayList<Camp> list, Staff staff, CampInfo campInfo){
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        int userChoice = -1;

        while(exit == false){
            System.out.println("--- How would you like to generate your attendance report ---");
            System.out.println("1. For all my camps");
            System.out.println("2. For only a specific camp(Filters will not be applied)");
            System.out.println("3. Exit");

            try{
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer option! ");
            }

            switch(userChoice){
                case 1:
                    PerformanceReport.printReport(list, campInfo);
                    break;
                case 2:
                    printForOneCamp(campInfo, staff, false);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
    }

    protected static ArrayList<Camp> filterUI(ArrayList<Camp> filteredList, ArrayList<Camp> originalList, boolean filtered){
        int userChoice = -1;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        while(exit == false){
            System.out.println("--- Choose the Filter you wish to apply ---");
            System.out.println("1. Faculty");
            System.out.println("2. Location");
            System.out.println("3. Date");
            System.out.println("4. Exit");
            try{
                userChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter a valid integer choice!");
            }

            switch(userChoice){
                case 1:
                    Faculty faculty = FilterUI.askFaculty();
                    if(filtered){
                        filteredList = Filter.filterByCampFaculty(filteredList, faculty);
                    }
                    else{
                        filteredList = Filter.filterByCampFaculty(originalList, faculty);
                        filtered = true;
                    }
                    break;
                case 2:
                    String location = FilterUI.askLocation();
                    if(filtered){
                        filteredList = Filter.filterByLocation(filteredList, location);
                        
                    }
                    else{
                        filteredList = Filter.filterByLocation(originalList, location);
                        System.out.println(filteredList.size());
                        filtered = true;
                    }
                    break;
                case 3:
                    int date = FilterUI.askDate();
                    if(filtered){
                        filteredList = Filter.filterByDate(filteredList, date);
                    }
                    else{
                        filteredList = Filter.filterByDate(originalList, date);
                        filtered = true;
                    }
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting filter interface . . .");
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }

        return filteredList;
        
    }

    
}
