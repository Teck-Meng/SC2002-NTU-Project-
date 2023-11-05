# This package contains the database of users and the password manager, it also contains read and write methods to file

Database Class - Contain 2 ArrayList that contains list of user IDs and their corresponding password
                 Allows the main program to add users from external list of users
                 Provides get methods for both ArrayLists that is accessible only by password package
                 Provides methods to change the password for a user in the database, this is used by PasswordManager to update password changes.


PasswordManager Class - Provide 2 methods to verify password and to change password
                        Method to change password will update the database accordingly

Read and write to CSV is in the same package as database and password manager to allow the write CSV function to access passwords without other packages being allowed to do so.

## The following classes will simulate memory for our program

ReadFromFile Class - Provides methods to be called once for each method at the start of the main program

ClearFiles Class - To assist writeToFile class
                   Used as rewriting csv is more efficient than having to apply a check duplicate method for all rows in the csv file and all data present in the corresponding databases
                   To be called once for each method at the very end of the main program, right before the corresponding write methods are called

writeToFile - To write data from CampInfo and Database to corresponding csv files
              To be called once for each method at the very end of the main program

readFromFile fully functional
clearFile and writeToFile fully functional