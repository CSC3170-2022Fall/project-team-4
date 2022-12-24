[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=9431822&assignment_repo_type=AssignmentRepo)
# CSC3170 Course Project

## Project Overall Description

This is our implementation for the course project of CSC3170, 2022 Fall, CUHK(SZ). For details of the project, you can refer to [project-description.md](project-description.md). In this project, we will utilize what we learned in the lectures and tutorials in the course, and implement either one of the following major jobs:

<!-- Please fill in "x" to replace the blank space between "[]" to tick the todo item; it's ticked on the first one by default. -->

- [ ] **Application with Database System(s)**
- [x] **Implementation of a Database System**

## Team Members

Our team consists of the following members, listed in the table below (the team leader is shown in the first row, and is marked with 🚩 behind his/her name):

<!-- change the info below to be the real case -->

| Student ID | Student Name | GitHub Account (in Email) | GitHub Username |
| ---------- | ------------ | ------------------------- | ------------------------- |
| 119010256  | 邵佳琪 🚩    |119010256@link.cuhk.edu.cn  |     [luuvy757](https://github.com/luuvy757)             |
| 119010022  | 陈诺涵       |flyn_lin@163.com           |      [flyn-linda](https://github.com/flyn-linda)            |
| 119010434  | 张启航       | 2447086160@qq.com        |  [Zhang-Setsail](https://github.com/Zhang-Setsail)                |
| 120040023  | Jefferson    |jeffersonjtedjojuwono@gmail.com | [jeffersonjt](https://github.com/jeffersonjt)               |
| 119010297  | 王景仪       | 119010297@link.cuhk.edu.cn | [pastyy](https://github.com/pastyy)           |
| 119010442  | 张天琦       | 1157956961@qq.com |  [tqZzzz](https://github.com/ZhangTianqi-Yuki)              |


## Project Specification

<!-- You should remove the terms/sentence that is not necessary considering your option/branch/difficulty choice -->

After thorough discussion, our team made the choice and the specification information is listed below:

- Our option choice is: **Option 3**

## Abstract
For this project we will create a simple Database Management System in Java. The basic structure of our database system is to store tables, each table having their own rows of data and predetermined columns. The basic functionalities of our database system would be to create a table, load a table from a file, store data into a table, insert a new row into a table, print a table, to select certain data from specific tables. 

We will use the skeleton file provided by UCB, and would have 5 classes we need to complete, which are the CommandIntrepeter, Database, Table, Row, and Condition. Other classes such as the tokenizer class which is used to read the inpupt for the database has already been given in the skeleton file provided by UCB. Our main strategy is to work our way up, starting from the most specific classes before finally integrating them all in the CommandIntrepeter class. Our order would be to impelemtn most functions for the classes row, table, database, commandintrepter, and finally work on the condition class for complex select funtionality. Generally we will follow the suggested order given in the pdf file from UCB. While working on the classes and functionality of the database we would also write test cases for easier debugging and to ensure the program is working as intended.

Once the simple database is completed and fully functional, we would redo assignment 2 using our simple database instead of SQL. (Excluding some queries that requires more complex functionalities not avaiable in our simple database system)


# Program Design 
<!-- Give explanation for the repository structure (may use an independent paragraph in the profile and/or in each sub-directories, or describe it in some section like Program Design)
Provide brief and explicit explanation and hyper link for any important and descriptive file (e.g. your TODO list).**** -->
## Directory Structure
The skeleton code provided by UCB is in the `inst.eecs.berkeley.edu
` folder, the **DIRECTORY STRUCTURE** and **TODO LIST** is described in the [Project](https://github.com/CSC3170-2022Fall/project-team-4/tree/main/inst.eecs.berkeley.edu). 
- `db61b` is the directory that our team works on. It contains all the files that we use to implement the database managemnet system. 
- `testing` is the directory that includes testing files for the database system.
## Design Overview
The data management system is designed with mainly following steps. 
- First, command interpreter will read the input from the user and tokenize it. The intruction format of the DBMS refers to the [project website](https://inst.eecs.berkeley.edu/~cs61b/fa14/hw/proj1.pdf).
    - `CommendInterpreter.java`: reading the input from the user and calling the corresponding functions in the database class. 
    The basic commends including: `load`, `store`, `create`, `insert`, `print`, `select`, `help`, `exit`. 

- Then, the command interpreter will call the database to execute the command, and the database will call the corresponding functions in the table class. 
    - `Database.java`: represents a database, which is a collection of tables.
    - `Table.java`: calling the corresponding functions in the row class. 
    - `Row.java`: storing the data in the row.
    - `Condition.java`:  represents a condition that can be used to filter rows from a table.
    - `Column.java`: storing the data in the column.

In the next section, we will present functions' implementation in details.

# Functionality Implementation


**Database**: The "Database" initializr a HashMap object to store the tables. The `keys` in the HashMap are the *names* of the tables and the *values* are the *Table objects*.
- `get` returns the *Table object* stored in the database with given `name`. 
- `put` adds the given `table` to the database with given `name`.

**Load Table**  
Test Example `load students;`
`Table.readTable (name)` reads a table file `students.db` with the given filename and returns a  `Table` object. Then, the database uses `put` to add the table to the database with the given name.

**Print Table**     
Test Example: `print students;`

The `Database.get(name)` first retrieves the table specified by the name given in the input. Then `Table.print()` iterates through the rows in the table and prints each element in the row on a separate line.

**Select**  
Test Example: `select SID, Firstname from students
     where Lastname = 'Chan';`

The `CommandInterpreter.selectClause()` firstly parse and execute a `SELECT` statement in a database. It takes in a token stream and returns a Table object for query result. This method can be appiled to one or two tables (joint selection).

**Insert**  
<!-- TODO: -->
Test Example: `insert into enrolled values '102', '22100', 'B+';`

**Creat Table**  
<!-- TODO -->
 `<create statement> ::= create table <table name> <table definition> ;`
Test Example: `create table enrolled2 as
  select SID
     from enrolled, schedule 
     where Dept = 'EECS' and Num = '61A';`

**Store Database**      
<!-- TODO -->


## Extra Works

### **Partial Commends Support of Assignment 2**

For the demonstration of the implementation of our database system we will use our previous assignment 2 that we have done 
on SQL and try to run those queries here on our databse system instead. We have made the required databases and chose a few
of the queries to test on our database system. We cannot implement all the 15 queries from assignment 2 because it would take
too long but also mainly because some of those queries requires complex functionalities that our database system simply dont 
have yet.

To implement the queries we added some additional functionalities to our database systems which are:
- order by
- inner join (natural join)
- table multiplication


### **GUI Implementation**

# Testing Results (Optional)
# Difficulty Encountered & Solutions (Optional)


# Conclusion
Add a paragraph about the actual contribution (not the originally designed one) of each teammate. May add one-sentence personal sentiment in this table by each member.