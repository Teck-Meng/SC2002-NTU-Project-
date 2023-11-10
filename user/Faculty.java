package user;

public enum Faculty {
    EEE(1, "EEE"), ADM(2, "ADM"), 
    NBS(3, "NBS"), CCEB(4, "CCEB"), CEE(5, "CEE"),
    MSE(6, "MSE"), SCSE(7, "SCSE"), MAE(8, "MAE"), 
    SOH(9, "SOH"), SSS(10, "SSS"),
    WKWSCI(11, "WKWSCI"), SPMS(12, "SPMS"), 
    SBS(13, "SBS"), ASE(14, "ASE"),
    LKCMED(15, "LKCMED"), ALL(16, "ALL"); 
    // Special Faculty ALL is for use by camppackage to allow camp to be open to all students

    private final int index;
    private final String name;
    private Faculty(int index, String name){
        this.index = index;
        this.name = name;
    }

    public int toInt(){
        return index;
    }

    
    public String toString(){
        return name;
    }
}
