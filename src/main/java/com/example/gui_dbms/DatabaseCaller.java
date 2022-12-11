package com.example.gui_dbms;
import dbms.Database;
import dbms.Table;

import java.util.ArrayList;
import java.util.List;

public class DatabaseCaller {
    private Database _database;
    public DatabaseCaller() {
        _database = new Database();
    }
    public void loadTable(String name) {
        try {
            Table table = Table.readTable(name);
            _database.put(name, table);
            System.out.printf("Loaded %s.db%n", name);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<String> getTableNames() {
        return _database.getTableNames();
    }

    public String[] getTableFields(String table) {
        return _database.get(table).getTitles();
    }

    public Table selectClause(String t1, String t2, ArrayList<String> cols, String condition) {

        Table table1 = _database.get(t1);
        Table table2 = null;
        if (t2 != null)
            table2 = _database.get(t2);
        Table result;
        if (table2 == null) {
            result = table1.select(cols, null);
        }
        else {
            result = table1.select(table2, cols, null);
        }

//        if(_input.nextIf("orderby")){
//            String col_order_by = columnName();
//            String direction = name();
//            result.sort(col_order_by,direction);
//        }
        return result;
    }
}
