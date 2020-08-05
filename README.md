# MS3Project

**Purpose**
  >To create an applicaiton that adds rows of data from a file to a database. This applicaiton could be used in the cloud or within a larger application. 
  
**Neccessary Steps**
>1. Create an SQLite database. 
>2. Create a String variable that holds the csv file to be parsed. 
>3. Create a String variable to hold the name of the database. 
>4. Use DriverManager.getConnection(PATH TO YOUR DB) and save this to a variable.
>5. Use the FileWriter class to create a file and later have the ability to write to your log file and the csv file that contains the bad records. 
>6. Use the BufferedReader along with FileReader to parse the original csv file, refer to step 2. 
>7. Create a String variable to hold the SQL command for creating a table. 
>8. Create a Statement object, which will help with executing an SQL command. The Statement object should equal the path to the database along with createStatement(); refer to line 41 and line 42 in the application.
>9. Now use the execute(ENTER THE STRING VARIABLE THAT HOLDS THE SQL COMMAND) method on the Statement object, which was created on line 8. 
>10. Write logic that will help keep track of the total number of records, successful records and failed records.
>11. Use the PreparedStatement object which hold a list of pre-compiled SQL commands.   
>12. Use the .setString() method to create a list of commands in the PreparedStatement object. These commands will help insert values into the table. Execute the list of commands with executeUpdate() method. 
>13. Make sure to close any open FileWriter streams. 

**Approach / Design Choice / Assumptions**
>I initially thought about creating multiple classes for the application. After further consideration each step inherently builds upon one another and therefore, the use of classes seemed to be excessive. I knew the application needed methods to create files and to write
to them, as well as, being able to connect and write to a database. From the instructions, I knew needed variables to keep track of the total number of records, successful records and failed records. With my previous experience using file input/output methods, I knew that a try/catch block would be useful. 

>I researched the process of connecting to a database and writing to a database using Java.

>Once, I connected to the database and created the table, I decided to use a while loop and have it execute as long as there was a line to be parsed in the csv file. The goal was to store each line of the csv file into an array and split each item in the line by 
a comma; this would later be used to help insert the values for each column in the table. 

>While parsing the csv file, I used variables to keep track of the total number of records, successful records and failed records. Early in the application, I created files to store the results from these variables.

>I tried my best to create a sequence of optimized methods. My goal was to get working code that was easy to read and understand.
