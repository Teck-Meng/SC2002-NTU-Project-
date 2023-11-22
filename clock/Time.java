package clock;

/**
 * Class that instantiates current time for CAMs
 */
public class Time {
    private int currentDate;

    /**
     * To be called at the start of the program to set current date
     * 
     * @param currentDate Current Date
     */
    public Time(int currentDate){
        this.currentDate = currentDate;
    }

    /**
     * 
     * @return Current Date
     */
    public int getDate(){
        return this.currentDate;
    }
}
