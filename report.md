# Project Report


## 5. class `Database` with `put` and `get` operations
It can be found in Database.java
- The data structure of Database is a Hashmap\<String, Table\>
- The `put` operation inserts a new pair of (table name, table) into the database.   
- The `get` operation returns the table with a designated name.

## 6. `insert` and `load` operations
This part implements the `insert` and `load` operations.
- The `insert` operation inserts a new record into the database.  The `insertStatement` operation is implemented in the `insert` function in `/db61b/CommandInterpreter.java`. 
- The `load` operation loads a record from the database.The `load` operation is implemented in the `loadStatement` function in `/db61b/CommandInterpreter.java`.

> TODO: TEST, AFTER STEP 5 COMPLETE

