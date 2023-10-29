package user;

public enum Faculty {
    EEE(1), ADM(2), NBS(3), CCEB(4), CEE(5), MSE(6), SCSE(7),
    MAE(8), SOH(9), SSS(10), WKWSCI(11), SPMS(12), SBS(13), ASE(14),
    LKCMED(15), 
    ALL(16); // Special Faculty ALL is for use by camppackage to allow camp to be open to all students

    private final int index;
    private Faculty(int index){
        this.index = index;
    }

    public int toInt(){
        return index;
    }
}
