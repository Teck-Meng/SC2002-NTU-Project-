import java.util.ArrayList;

import camppackage.Camp;

public class StudentUI {
    public static void Main(){
        ArrayList<Camp> list = campInfo.getCampList(attendee);
                    for(int i = 0; i < list.size(); i++){
                        list.get(i).print();
                    }
    }
}
