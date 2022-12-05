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

| Student ID | Student Name | GitHub Account (in Email) |
| ---------- | ------------ | ------------------------- |
| 119010256  | 邵佳琪 🚩    |119010256@link.cuhk.edu.cn  |
| 119010022  | 陈诺涵       |flyn_lin@163.com           |
| 119010434  | 张启航       | 2447086160@qq.com        |
| 120040023  | Jefferson    |jeffersonjtedjojuwono@gmail.com |
| 119010297  | 王景仪       | 119010297@link.cuhk.edu.cn |
| 119010442  | 张天琦       | 119010442@link.cuhk.edu.cn |


## Project Specification

<!-- You should remove the terms/sentence that is not necessary considering your option/branch/difficulty choice -->

After thorough discussion, our team made the choice and the specification information is listed below:

- Our option choice is: **Option 3**


## Project Abstract
This project involves writing a miniature relational database management system (DBMS) thatstores tables of data, where a table consists of some number of labeled columns of information. Our system will include a very simplequery language for extracting information from these tables. For the purposes of this project, we will deal only with very smalldatabases, and therefore will not consider speed and efficiency at all.
### Resource Links    
[Project Description](https://inst.eecs.berkeley.edu/~cs61b/fa14/hw/proj1.pdf)      
[Project Check List](https://inst.eecs.berkeley.edu/~cs61b/fa14/hw/project1_checklist.html)     
[Phase 1 Getting Started Video](https://www.youtube.com/watch?v=Hmkbl72YbQQ)    
[Phase 2 Getting Started Video](https://www.youtube.com/watch?v=1ZPSNH6RZr0)    
[Video Slides](https://docs.google.com/presentation/d/1f8TXMg9yU42Ik9AvVn05790otrNooq28jcAWji9GRuk/edit#slide=id.g3a514e0eb_0160)   
[Project Codes](https://inst.eecs.berkeley.edu/~cs61b/fa14/hw/code/proj1/)    
### Optional/Additional Work    

To make your work attractive, you may think about interesting things to do by yourself. Here are some examples:   
Python-based Implementation: Although Python is not a static language, it's a strongly typed language, which makes it possibleto transfer from those Java codes to Python ones. You may use the typing library for type hinting (but remember to switch the Pythonversion that is compatible to the version you are using when you are visiting this typing api webpage).     
Application with Your Implementation: You may write some small-scale application with the DBMS you just implemented. Forexample, you can try to "reimplement" those work in Assignment 2 -- there would be some expression in your queries that needed tobe changed, as your DBMS is not "as powerful as MySQL", but it's okay as long as you'd make it work.

## Tasks
- [ ] TODO ...
<!-- TODO -->


## Abstract
For this project we will create a simple Database Management System in Java. The basic structure of our database system is to store tables, each table having their own rows of data and predetermined columns. The basic functionalities of our database system would be to create a table, load a table from a file, store data into a table, insert a new row into a table, print a table, to select certain data from specific tables. 

We will use the skeleton file provided by UCB, and would have 5 classes we need to complete, which are the CommandIntrepeter, Database, Table, Row, and Condition. Other classes such as the tokenizer class which is used to read the inpupt for the database has already been given in the skeleton file provided by UCB. Our main strategy is to work our way up, starting from the most specific classes before finally integrating them all in the CommandIntrepeter class. Our order would be to impelemtn most functions for the classes row, table, database, commandintrepter, and finally work on the condition class for complex select funtionality. Generally we will follow the suggested order given in the pdf file from UCB. While working on the classes and functionality of the database we would also write test cases for easier debugging and to ensure the program is working as intended.

Once the simple database is completed and fully functional, we would redo assignment 2 using our simple database instead of SQL. (Excluding some queries that requires more complex functionalities not avaiable in our simple database system)
