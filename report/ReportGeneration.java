package report;

import java.util.ArrayList;

import camppackage.CampInfo;
import camppackage.Camp;

/**
 * Interface for report generation
 */
public interface ReportGeneration {
    /**
     * Print report for a list of camps
     * @param listOfCamps List of Camps
     * @param campInfo Database of Camps
     */
    public static void printReport(ArrayList<Camp> listOfCamps, CampInfo campInfo){};

    /**
     * Print report for a specific camp
     * 
     * @param camp Specific Camp
     * @param campInfo Database of Camps
     */
    public static void printReport(Camp camp, CampInfo campInfo){};
}
