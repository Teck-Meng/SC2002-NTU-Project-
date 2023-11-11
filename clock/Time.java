package clock;


public class Time {
    private int currentDate;

    /*
     * Only admin of program can set this time, admin is expected to set a proper time
     */
    public Time(int currentDate){
        this.currentDate = currentDate;
    }

    public int getDate(){
        return this.currentDate;
    }
}
