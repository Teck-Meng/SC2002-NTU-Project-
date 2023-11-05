This package will contain read and write methods to file.
The file will be used to simulate memory and will also be used for report generation
All methods here are fully tested and ready to go.
# This package contains the database of users and the password manager

Database Class - Contain 2 ArrayList that contains list of user IDs and their corresponding password
                 Allows the main program to add users from external list of users
                 Provides get methods for both ArrayLists that is accessible only by password package
                 Provides methods to change the password for a user in the database, this is used by PasswordManager to update password changes.


PasswordManager Class - Provide 2 methods to verify password and to change password
                        Method to change password will update the database accordingly

Read and write to CSV is in the same package as database and password manager to allow the write CSV function to access passwords without other packages being allowed to do so.

readFromFile fully functional