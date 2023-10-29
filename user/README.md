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

UserAuthenticator Class - Acts as a middleman to verify login details

Faculty enum - To be used to indicate the faculty information of users and also the user group of a camp