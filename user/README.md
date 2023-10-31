# This package contains the user classes and also manages login and changing of passwords.

# ASSUMPTIONS: 
    1. Students are not able to change faculty, hence there would not be a set method for faculty information
    2. Passwords are set to "password" by default
    3. User ID and Passwords are case-sensitive
    4. Passwords have no set length

User Class - Contains userID, password and faculty information
             Allows users to retrieve faculty information through a get method
             Allow classes to verify student identity using get method for user ID
             To be inherited by Staff and Student classes

Student Class - Contains information about which camps each student as registered for
                Contains information about the camp that a student is registered for to be a Committee Member
                Boolean Method to check if a student has a Camp Commmittee Position
                Contains number of points for a student if they are a committee member
                Contains method to be called by Staff that allows them to add points if a student is a committee member

Staff Class - Can add, edit and delete camps
              Contains method to add points for a committee member in their camp, to be used in enquiry package

UserAuthenticator Class - Acts as a middleman to verify login details

Faculty enum - To be used to indicate the faculty information of users and also the user group of a camp