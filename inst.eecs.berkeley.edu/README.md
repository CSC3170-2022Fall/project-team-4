# Skeleton
This directory contains a skeleton for CS61B Project 1.

CONTENTS:

ReadMe                  This file.
        
Makefile                A makefile (for the 'make' program) that will compile
                        your files and run tests.  You must turn in a Makefile,
                        'make' must compile all your files, and 
                        'make check' must perform all your tests.  Currently,
                        this makefile is set up to do just that with our
                        skeleton files.  Be sure to keep it up to date.

staff-version           If we update the skeleton, this file will contain a
                        unique version id indicating what version of the
                        skeleton is currently in use.


db61b                   A subdirectory containing skeletons for the 
                        db61b package:

  Main.java             The main program---entry point to the db61b system.
  Database.java         Abstraction for an entire collection of tables.  
  Table.java            Abstraction for one table.
  Row.java              Abstraction for one row of a table.

testing                 Subdirectory holding files for integration testing:

  Makefile              A makefile containing instructions for performing
                        tests on your project.

  students.db, enrolled.db, courses.db
                        Sample database tables from the project handout.

  test1.in, test2.in    Input files for testing.  The makefile will respond
                        to 'make check' by running these files through your
                        program, filtering the output through 
                        testing/test-filter, and comparing the results with 
                        the corresponding .out files.  You should add more 
                        files to the list in Makefile.
                        REMINDER: These are samples only.  They DON'T 
                        constitute adequate tests.

  test1.out, test2.out  Output that is supposed to result from test1.in
                        and test2.in, with the first line, all prompts,
                        and all blank lines removed (which is what 
                        test-filter does).

  testing.py            A Python 3 module containing a framework for integration
                        testing.   Used by tester.py.

  tester.py             A Python 3 program that tests your project.  It runs
                        your program with each .in file, comparing the output
                        with the corresponding .out file and producing a report
                        of the result.


# TODO LIST

- [x] Throughout the project, write test cases (in fact, do this every chance you get). Among other things, writing test cases gets you to understand the specification better. When- ever you find an input that breaks your program, make sure you capture it in a test case.

- [x] Get the printing of prompts, handling of comments, and the ‘quit’ and exit’ commands, and the end of input to work. If you’re using our skeleton, this step has already been completed for you.

- [x] Implement the Row class, except for the Row(List<Column> columns, Row... rows) constructor, which you should save for later. Make sure to write tests for the Row class. See lab5 for examples of how to test components of a package. Consider adding a command to your makefile that runs your unit tests, using the optional part of lab5 as a guide.

- [x] Implement the parts of the Table class needed to create a new Table, add a Row to it, and print an entire Table.

- [x] Implement the Database class.

- [x] Implementinsertandload.

- [x] Implement the kind of select that takes a single table and has no conditions.

- [x] Implement the Row(List<Column> columns, Row... rows) constructor.

- [x] Now get single-table select with conditions to work.

- [x] Finally, work on the two-table variety of select.

- [x] In the above steps, as you decide on implementation strategies and the data repre- sentations you will use, write them down in your internal documentation. When you introduce new methods or classes, write the comments first.