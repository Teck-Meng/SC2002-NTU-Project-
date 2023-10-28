# camppackage contains all the classes regarding the creation, deletion, viewing and managing of camps

# ASSUMPTIONS:
    1. Students who withdraw from a camp are assumed to be permenantly blacklisted from that particular camp
    2. Only the staff who created the camp can delete and edit that particular camp
    3. All staff classes can view all camps regardless of visibility
    4. Each camp can only have a maximum of 10 Camp Committee Members

Camp Class - Added get and set methods for all attributes except for StaffIC.
             Added bool verification method for attribute StaffIC.
             Creation of camp by Staff to be done using constructor Camp(StaffIC) and 
             set methods.(Console prompt for details to add and then set using set methods)

CampInfo Class - A data structure containing information such as the list of camps, visibility modifiers,
                 and number of attendees and Committee Members for each camp.
                 Get methods for all 4 data structures are added.
                 Added the ability to intialize a camp with all 4 data structures for staff.
                 For all data structures barring list of camps, the get methods only retrieve data of a 
                 specific camp.
                 Added the ability for staff to delete a camp that they have created.
                 Added private method to see if user's faculty matches with that of the camp.
                 Allow other classes to update the number of slots in attendance list and committee list caused by registering or withdrawal.

CampUtility Class - Contains method that returns index position if camp exist, else return -1.
                    Contains method that returns index position if student exist in a given list, else return -1.
                    Contains method to check if attendee slot is full for a specific camp

AttendeeList Class - Contain list of attendees for a specific camp
                     Added standard get method to extract list
                     Added methods to allow students to be added or remove from list
                     deleteAttendee is a boolean method that allows us to check if deletion is successful

CommitteeList Class - Contain list of Camp Committee Members for a specific camp
                      Added standard get method to extract list
                      Added method to add Committee Members and update points
                      updatePoints is a boolean method that allows us to check if point addition is successful

BlackList Class - Contains list of withdrawees who are not allowed to re-register for a particular camp
                  Added standard get method to extract list
                  To be updated everytime a student withdraws from a camp
                  Allows classes to check if student is blacklisted

CampRegister Interface - To be implemented by AttendeeRegister and CommitteeRegister
                         Requires a method for students to register for camp
                         Requires a method for students to view camps

AttendeeRegister Class - Allows students to register and withdraw from camps.
                         Allows students to view camps for which they have the rights to view
                      


