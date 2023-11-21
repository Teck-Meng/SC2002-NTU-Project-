package filehandler;

import user.Faculty;

public class ConvertString {
        protected static Faculty setFaculty(String faculty){
        Faculty returnFaculty = Faculty.ADM;
        switch(faculty){
            case "EEE":
                returnFaculty = Faculty.EEE;
                break;
            case "ADM":
                returnFaculty = Faculty.ADM;
                break;
            case "NBS":
                returnFaculty = Faculty.NBS;
                break;
            case "CCEB":
                returnFaculty = Faculty.CCEB;
                break;
            case "CEE":
                returnFaculty = Faculty.CEE;
                break;
            case "MSE":
                returnFaculty = Faculty.MSE;
                break;
            case "SCSE":
                returnFaculty = Faculty.SCSE;
                break;
            case "MAE":
                returnFaculty = Faculty.MAE;
                break;
            case "SOH":
                returnFaculty = Faculty.SOH;
                break;
            case "SSS":
                returnFaculty = Faculty.SSS;
                break;
            case "WKWSCI":
                returnFaculty = Faculty.WKWSCI;
                break;
            case "SPMS":
                returnFaculty = Faculty.SPMS;
                break;
            case "SBS":
                returnFaculty = Faculty.SBS;
                break;
            case "ASE":
                returnFaculty = Faculty.ASE;
                break;
            case "LKCMED":
                returnFaculty = Faculty.LKCMED;
                break;
            default:
                returnFaculty = Faculty.ALL;
                break;
        }
        return returnFaculty;
    }

    protected static int stringToInt(String string){
        return (int)Integer.parseInt(string);
    }
}

