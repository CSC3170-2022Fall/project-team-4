package com.example.gui_dbms;
import dbms.Database;
import dbms.Row;
import dbms.Table;

import java.util.ArrayList;
import java.util.List;

import static dbms.Utils.error;

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

        return result;
    }

    public void saveTable(String name, Table table) {

        _database.put(name, table);

    }



    public void createTable(String name, ArrayList<String> fields) {
        Table newTable = new Table(fields);
        _database.put(name, newTable);
    }

    public void innerJoin(String t1, String t2, String newTable) {
        Table table1 = _database.get(t1);
        Table table2 = _database.get(t2);
        Table t3 = table1.innerjoin(table2);
        _database.put(newTable, t3);
    }

    public void multiply(String t1, String t2, String newTable) {
        Table table1 = _database.get(t1);
        Table table2 = _database.get(t2);
        Table t3 = table1.multiply(table2);
        _database.put(newTable, t3);
    }

    public void insert(String[] values, String tableName) {
        Row row = new Row(values);
        // System.out.printf("inserting %s into %s%n", values, table.getName());
        Table table = _database.get(tableName);
        boolean success = table.add(row);
        if (!success) {
            throw error("insertion failed");
        }
    }
}
