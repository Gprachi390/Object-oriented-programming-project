# Object-oriented-programming-project
Part of 1st-semester object-oriented programming course
FOOD ORDERING

Installations required

Visual Studio Code
Apache Maven Folder or installing maven from VScode
Doxygen
MySQL
Steps for Running the Project

Open the project folder in Visual Studio code
Make sure the maven for Java is installed in Visual Studio code before running the project
Set up the MySQL workbench
Create the required tables in the database
Run food.java in Visual Studio Code from the project folder
For checking the status and change it once the food is delivered from the delivery man side, run the delivery_man.java
Steps to create a Doxygen file

Install Doxygen
Open the doxy wizard
Specify the working directory
Give the new folder name 'Doxygen' in the directory, where all the files created by doxygen will be stored
Change the project name, add the logo
In the expert tab, set Javadoc auto brief
In the build tab, set extract all and disable the used file
In the HTML tab, set the generate tree and disable the index
Steps to Create a Profiling Report

Firstly we have added the extension YOUR KIT in Ecplise
Then by running the project with the help of Your Kit Plugin, we added the following to the Profiling Report - Live Call Tree - Cpu Time - GC count (Garbage Collection Count) - Heap Memory - Non Heap Memory - Loaded Classes - Threads - Cpu Flame Graph
Steps to open Doxygen file in HTML format

Open the project directory
Go to the Doxygen folder
Select HTML folder
Open index.html in any browser
Steps to open the profiling report

Open the profiling folder in the project directory and we can find the Profiling Report
Steps to open UML class diagram

Select the UML_diagram folder in the project directory
Open the image from the folder to see the UML diagram
Concepts used in Project

Encapsulation
Inheritance
Polymorphism
Abstraction These Object-oriented principles are implemented in the project in many functionalities
About the project

Online food ordering system accepts orders from customers and delivers to customer's address. This project includes functionalities like verification of customers using a database, addition and deletion of food items from a cart, payment via different modes, adding items to a wishlist, tracking the food item, status checking and updates, etc.
Tables created in MYSQL

Customer
Registration
Restaurant
Cart
Wishlist
Payment
Net Banking
UPI
card
Tracking -Check out
Db
Delivery_man
Classes And their Respective Methods

Food – main class

Home page //This class helps display the options

Verification existing() //To verify of the existing user credentials

Registration new_user() //creating the sign-in for new user

Restaurant print_rest() //To print the restaurant names and choose the restaurant //According to the restaurant display their menu and help in adding items to the cart

Cart_wishlist // Abstract class that helps in polymorphism and Inheritance Insert_cart() view_cart()

Cart insert_cart()
//Insert the items with quantity, price corresponding to its user_id in the db view_cart() //To view all the items present in the cart

Checkout cout() //To choose the promo codes if applicable

Payment (base class)
cart_pay() //Helps in choosing types of payment

Card_payment (Child Class) card_payment_method() //Payment is done via card option

Net_Banking (child class) net_banking_method() //Payment done via net banking option

UPI upi_method () //Payment done via UPI option

Wishlist insert_wishlist() // To view items present in wishlist

Db set_user() - To set user details check_name() - To check email id Check_login() - To verify logged user choose_res() - Gives option to choose restaurant choose_menu() - Displays the menu set_insert_cart() - Adds item to cart cart_summ() - Displays cart Summary check_promo() - Displays the available promo codes reset_promo() - Reset promo code once used insert_tracking() - Method to handle tracking cart_delete() - Delete item from cart status_check() - Gives status checking option to user delete_cart_after_payment() - Deletes the items from cart

Delivery_man - Main class

Database

status_check()
change_status()
Limitations of the project:

Only a few restaurants are considered, we can add more according to the location.
GUI is not implemented.
Limited locations are given to select for the user address.
Estimated time calculation can be more accurate using live location
Further enhancements in the project:

Implementation of the front end will make it more interactive and user-friendly.
More locations can be added for the user's address.
More restaurants can be added based on the user's locations
Use of APIs for GPS
