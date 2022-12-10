// This is a SUGGESTED skeleton for a class that represents a single
// Table.  You can throw this away if you want, but it is a good
// idea to try to understand it first.  Our solution changes or adds
// about 100 lines in this skeleton.

// Comments that start with "//" are intended to be removed from your
// solutions.
// TODO(12Func): Table(String[] columnTitles, columns(), getTitle(int k), findColumn(String title), size(), add(Row row), readTable(String name), writeTable(String name), print(), gett*2, equijoin.
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
import java.util.Collections;

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

    public String[] getTitles() {
        return _columTitles;
    }

    /** Return the number of the column whose title is TITLE, or -1 if
     *  there isn't one. */
    public int findColumn(String title) {
        String name = this.getName();
        for(int i = _columTitles.length - 1; i >= 0; i -= 1) {
            if (_columTitles[i].equals(title)){
                // System.out.printf("%s is in %s %n", title, name);
                return i;
            }
        }
        // get table name;

        // System.out.printf("%s is not a valid column title in %s %n", title, name);
        return -1;
    }

    /** Return the number of Rows in this table. */
    public int size() {
        return this._rows.size();
        // TODO: 这里算上了标题,是不是需要减一?
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
        if (row.size() != columns()) {
            throw error("row size mismatch");
        }
        Iterator <Row> rows_in_table = this._rows.iterator();
        while (rows_in_table.hasNext()) {
            if (rows_in_table.next().equals(row)) {
                // System.out.println("Warning: Have same row already in the Table!");
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

        // current dir
        String dir = System.getProperty("user.dir");
        // //System.out.println("this is the dir" + dir);
        // // join dir with /testing
        String path = dir + "/inst.eecs.berkeley.edu/testing/" + name + ".db";

        //String path = name + ".db";
        try {
            input = new BufferedReader(new FileReader(path));
            String header = input.readLine();
            // System.out.println(header);
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
                    throw error("data with wrong format in the %s.db",name);
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
            Row title = new Row(_columTitles);
            int title_size = title.size();
            // output.printf(title.get(0));
            // for (int i = 1; i < title_size; i++) {
            //     output.printf(",");
            //     output.printf(title.get(i));
            // }
            // output.println("");
            // If title only be stored in the _columnTitles, we will need these commented lines.

            for (Row r : this._rows) {
                output.printf(r.get(0));
                for (int i = 1; i < title_size; i++) {
                    output.printf(",");
                    output.printf(r.get(i));
                }
                output.println("");
            }
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
        Iterator<Row> print_iterator = _rows.iterator();
        print_iterator.next();
        Row row_to_print;
        while (print_iterator.hasNext()) {
            row_to_print = print_iterator.next();
            int row_size = row_to_print.size();
            for (int i = 0; i < row_size; i = i + 1) {
                System.out.printf(" ");
                System.out.printf(row_to_print.get(i));
            }
            System.out.println("");
        }
    }

    void sort(String column,String direction){
        System.out.println("Sort is called");
        int original_size = this.size();
        if(direction.equals("asc")){
            for (int i = 1;i < original_size - 1; i = i + 1){
                for(int j = i + 1; j < original_size; j = j + 1){
                    if(Double.parseDouble((this._rows.get(i)).get(this.findColumn(column))) > Double.parseDouble((this._rows.get(j)).get(this.findColumn(column)))){
                        Collections.swap(this._rows,i,j);
                    }
                }
            }
        }
        else{
            for (int i = 1;i < original_size - 1; i = i + 1){
                for(int j = i + 1; j < original_size; j = j + 1){
                    if(Double.parseDouble((this._rows.get(i)).get(this.findColumn(column))) < Double.parseDouble((this._rows.get(j)).get(this.findColumn(column)))){
                        Collections.swap(this._rows,i,j);
                    }
                }
            }

        }
    }

    /** Return a new Table whose columns are COLUMNNAMES, selected from
     *  rows of this table that satisfy CONDITIONS. */
    Table select(List<String> columnNames, List<Condition> conditions) {
        Table result = new Table(columnNames);
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
                    ArrayList<Column> newColumns = new ArrayList<Column>(); // join columns
                    ArrayList<Column> commonColumns1 = new ArrayList<Column>();
                    ArrayList<Column> commonColumns2 = new ArrayList<Column>();

                    for (String name : columnNames) {
                        newColumns.add(new Column(name, this, table2));
                    }
                    for (String colTitle : this._columTitles){
                        if (contain(table2.getTitles(),colTitle)){
                            commonColumns1.add(new Column(colTitle, this));
                            commonColumns2.add(new Column(colTitle, table2));
                        }
                    }
                    for (Row row1 : this) {
                        for (Row row2 : table2) {
                            if (equijoin(commonColumns1, commonColumns2, row1, row2)
                                && Condition.test(conditions, row1, row2)) {
                                result.add(new Row(newColumns, row1, row2));
                            }
                        }
                    }
                    return result;
    }

    Table innerjoin(Table table2){
        ArrayList<String> all_columns = new ArrayList<String>();
        ArrayList<Column> newColumns = new ArrayList<Column>(); // join columns
        ArrayList<Column> commonColumns1 = new ArrayList<Column>();
        ArrayList<Column> commonColumns2 = new ArrayList<Column>();

        for (String colTitle : this._columTitles){
            if (contain(table2.getTitles(),colTitle)){
                commonColumns1.add(new Column(colTitle, this));
                commonColumns2.add(new Column(colTitle, table2));
            }
        }
        for(String column_titles:this._columTitles){
            all_columns.add(column_titles);
        }
        for(String column_title:table2.getTitles()){
            if(!all_columns.contains(column_title)){
                all_columns.add(column_title);
            }
        }

        Table result = new Table(all_columns);

        for (String name : all_columns) {
            newColumns.add(new Column(name, this, table2));
        }
        for (Row row1 : this) {
            for (Row row2 : table2) {
                if (equijoin(commonColumns1, commonColumns2, row1, row2)) {
                    result.add(new Row(newColumns, row1, row2));
                }
            }
        }
        return result;
    }

    Table multiply(Table table2){
        ArrayList<String> all_columns = new ArrayList<String>();
        ArrayList<Column> newColumns = new ArrayList<Column>(); // join columns

        for(String column_titles:this._columTitles){
            all_columns.add(column_titles);
        }
        for(String column_title:table2.getTitles()){
            if(!all_columns.contains(column_title)){
                all_columns.add(column_title);
            }
        }

        Table result = new Table(all_columns);

        for (String name : all_columns) {
            newColumns.add(new Column(name, this, table2));
        }
        int i = 0;
        
        for (Row row1 : this) {
            int j = 0;
            if (i == 0 ){
                i+= 1;
                continue;
            }
            for (Row row2 : table2) {
                if(j == 0){
                    j+= 1;
                    continue;
                }
                result.add(new Row(newColumns, row1, row2)); 
            }
        }
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
        for (int i = common1.size()-1; i >= 0; i--) {
            if(!common1.get(i).getFrom(row1).equals(common2.get(i).getFrom(row2))){
                return false;
            }
        }
        return true; // REPLACE WITH SOLUTION
    }

    /** My rows. */
    private List<Row> _rows = new ArrayList<Row>();
    // FILL IN
    private String[] _columTitles;

// contain method of String[]
    public boolean contain(String[] array, String target){
        for (String s : array){
            if (s.equals(target)){
                return true;
            }
        }
        return false;
    }


}





