// This is a SUGGESTED skeleton for a class that represents a single
// Table.  You can throw this away if you want, but it is a good
// idea to try to understand it first.  Our solution changes or adds
// about 100 lines in this skeleton.

// Comments that start with "//" are intended to be removed from your
// solutions.
// TODO(12Func): Table(String[] columnTitles, columns(), getTitle(int k), findColumn(String title), size(), add(Row row), readTable(String name), writeTable(String name), print(), select*2, equijoin.
package db61b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static db61b.Utils.*;

/** A single table in a database.
 *  @author P. N. Hilfinger
 */
class Table implements Iterable<Row> {
    private String _name;
    /** A new Table whose columns are given by COLUMNTITLES, which may
     *  not contain dupliace names. */
    Table(String[] columnTitles) {
        for (int i = columnTitles.length - 1; i >= 1; i -= 1) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (columnTitles[i].equals(columnTitles[j])) {
                    throw error("duplicate column name: %s",
                                columnTitles[i]);
                }
            }
        }
        _columTitles = columnTitles;
        _rows.add(new Row(columnTitles));
        // FILL IN
    }

    /** A new Table whose columns are give by COLUMNTITLES. */
    Table(List<String> columnTitles) {
        this(columnTitles.toArray(new String[columnTitles.size()]));
    }

    /** Return the number of columns in this table. */
    public int columns() {
        //return this._rows.iterator().next().size();
        return  _columTitles.length;
        // return 0;  // REPLACE WITH SOLUTION
    }

    // Set table name 
    public void setName(String name) {
        _name = name + ".db";
    }
    // get table name
    public String getName() {
        return _name;
    }

    /** Return the title of the Kth column.  Requires 0 <= K < columns(). */
    public String getTitle(int k) {
        //return this._rows.iterator().next().get(k);
        try{
            return _columTitles[k];
        } 
        catch(Exception e){
            throw error("Inproper column length:" + k);
        }
        // return null;  // REPLACE WITH SOLUTION
    }

    /** Return the number of the column whose title is TITLE, or -1 if
     *  there isn't one. */
    public int findColumn(String title) {
        for(int i = _columTitles.length - 1; i >= 1; i -= 1) {
            if (_columTitles[i].equals(title)){
                return i;
            }
        }
        return -1;
        //return -1;  // REPLACE WITH SOLUTION
    }

    /** Return the number of Rows in this table. */
    public int size() {
        return this._rows.size();
        //return 0;  // REPLACE WITH SOLUTION
    }

    /** Returns an iterator that returns my rows in an unspecfied order. */
    @Override
    public Iterator<Row> iterator() {
        return _rows.iterator();
    }

    /** Add ROW to THIS if no equal row already exists.  Return true if anything
     *  was added, false otherwise. */
    public boolean add(Row row) {
        Iterator <Row> rows_in_table = this._rows.iterator();
        while (rows_in_table.hasNext()) {
            if (rows_in_table.next().equals(row)) {
                System.out.println("Warning: Have same row already in the Table!");
                return false;
            }
        }
        this._rows.add(row);    // _rows is hashset, maybe we can only use this line.
        // updata 2022.12.03: Change _rows to ArrayList to having a order output. So we need to check if have the same input.
        return true;
        // return false;   // REPLACE WITH SOLUTION
    }

    /** Read the contents of the file NAME.db, and return as a Table.
     *  Format errors in the .db file cause a DBException. */
    static Table readTable(String name) {
        BufferedReader input;
        Table table;
        input = null;
        table = null;
        int col_num;
        try {
            input = new BufferedReader(new FileReader(name + ".db"));
            String header = input.readLine();
            System.out.println(header);
            if (header == null) {
                throw error("missing header in DB file");
            }
            String[] columnNames = header.split(",");
            col_num = columnNames.length;
            // System.out.printf("%d", col_num);
            table = new Table(columnNames);
            String new_row;
            while ((new_row = input.readLine()) != null) {
                String[] new_row_split = new_row.split(",");
                if (new_row_split.length != col_num) {
                    throw error("data with wrong format in the %s.db", name);
                }
                Row new_row_add = new Row(new_row_split);
                // table._rows.add(new_row_add);    // This line can't delete same row in .db file
                table.add(new_row_add);
            }
            // FILL IN
        } catch (FileNotFoundException e) {
            throw error("could not find %s.db", name);
        } catch (IOException e) {
            throw error("problem reading from %s.db", name);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    /* Ignore IOException */
                }
            }
        }
        return table;
    }

    /** Write the contents of TABLE into the file NAME.db. Any I/O errors
     *  cause a DBException. */
    void writeTable(String name) {
        PrintStream output;
        output = null;
        try {
            String sep;
            sep = "";
            output = new PrintStream(name + ".db");
            // FILL THIS IN
        } catch (IOException e) {
            throw error("trouble writing to %s.db", name);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /** Print my contents on the standard output. */
    void print() {
        // Iterator<Row> print_iterator = _rows.iterator();
        // Row row_to_print;
        // // int index = 0;
        // while (print_iterator.hasNext()) {
        //     // index = index + 1;
        //     // System.out.printf("%d",index);
        //     row_to_print = print_iterator.next();
        //     int row_size = row_to_print.size();
        //     System.out.printf(" ");
        //     for (int i = 0; i < row_size; i = i + 1) {
        //         System.out.printf(" ");
        //         System.out.printf(row_to_print.get(i));
        //     }
        //     System.out.println("");
        // }
        // FILL IN
        for(Row row : this._rows){
            System.out.println(row.toString());
        }
    }

    /** Return a new Table whose columns are COLUMNNAMES, selected from
     *  rows of this table that satisfy CONDITIONS. */
    Table select(List<String> columnNames, List<Condition> conditions) {
        Table result = new Table(columnNames);
        // FILL IN
        List <Column> newColumns = new ArrayList<Column>();
        for (String col : columnNames){
            newColumns.add(new Column(col, this));
        }
        for (Row originRow : this){
            if(Condition.test(conditions, originRow)){
                result.add(new Row(newColumns, originRow));
            }
        }
        return result;
    }

    /** Return a new Table whose columns are COLUMNNAMES, selected
     *  from pairs of rows from this table and from TABLE2 that match
     *  on all columns with identical names and satisfy CONDITIONS. */
    Table select(Table table2, List<String> columnNames,
                 List<Condition> conditions) {
        Table result = new Table(columnNames);
        List <Column> newColumns = new ArrayList<Column>();
        List <Column> commonColumns1 = new ArrayList<Column>();
        List <Column> commonColumns2 = new ArrayList<Column>();
        for (String col : columnNames){
            newColumns.add(new Column(col, this));
        }
        for (String colName1 : _columTitles){
            for (String colName2 : table2._columTitles){
                if (colName1.equals(colName2)){
                    commonColumns1.add(new Column(colName1, this));
                    commonColumns2.add(new Column(colName2, table2));
                }
            }
        }
        for (Row originRow : this){
            for (Row secondRow : table2){
                if(Condition.test(conditions, originRow, secondRow)){
                    if (equijoin(commonColumns1, commonColumns2, secondRow, originRow)){
                        result.add(new Row(newColumns, originRow, secondRow));
                    }
                }
            }
        }
        // FILL IN
        return result;
    }

    /** Return true if the columns COMMON1 from ROW1 and COMMON2 from
     *  ROW2 all have identical values.  Assumes that COMMON1 and
     *  COMMON2 have the same number of elements and the same names,
     *  that the columns in COMMON1 apply to this table, those in
     *  COMMON2 to another, and that ROW1 and ROW2 come, respectively,
     *  from those tables. */
    private static boolean equijoin(List<Column> common1, List<Column> common2,
                                    Row row1, Row row2) {
        return true; // REPLACE WITH SOLUTION
    }

    /** My rows. */
    private List<Row> _rows = new ArrayList<Row>();
    // FILL IN
    private String[] _columTitles;
}

