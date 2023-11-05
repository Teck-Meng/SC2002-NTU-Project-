package report;

import java.util.ArrayList;

import camppackage.CampInfo;
import camppackage.Camp;

public interface ReportGeneration {
    public static void printReport(ArrayList<Camp> listOfCamps, CampInfo campInfo){};
    public static void printReport(Camp committeeCamp, CampInfo campInfo){};
}
