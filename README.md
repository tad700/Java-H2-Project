# Java-H2-Project
Java Inventory Management System with H2 Database
This is a Java-based Inventory Management System that uses the H2 database.
The system provides an easy-to-use graphical interface for managing an inventory of clothes, people, and orders.

# Features
Add, update, and delete people from the inventory
Add, update, and delete clothes from the inventory
Add, update, and delete orders
View a summary of the orders made
Search and filter data by different parameters
Backup and restore the database

# Technologies Used
Java Swing for the GUI
H2 database for data storage
JDBC for database connectivity
Installation and Setup
Clone this repository to your local machine
Ensure that Java and H2 database are installed on your system
Change the connection path in 'DbConnection' class 
### conn=DriverManager.getConnection("jdbc:h2:tcp://localhost/"path","username of the database","password of the database");
Open the project in your preferred Java IDE
Build and run the project
# Usage
The application has a simple and intuitive graphical interface that enables users to easily add, update, and delete data in the inventory.
Users can view a summary of the orders made and search and filter data by different parameters.
