// This is a SUGGESTED skeleton for a class that describes a single Row of a
// Table. You can throw this away if you want, but it is a good idea to try to
// understand it first.  Our solution changes or adds about 10 lines in this
// skeleton.

// Comments that start with "//" are intended to be removed from your
// solutions.
// TODO(4Func): Row, size()"Done", get(int k)"Done", equals(Object obj)"Done".
package db61b;

import java.util.Arrays;
import java.util.List;

import static db61b.Utils.*;

/** A single row of a database.
 *  @author
 */
class Row {
    /** A Row whose column values are DATA.  The array DATA must not be altered
     *  subsequently. */
    Row(String[] data) {
        _data = data;
    }

    /** Given M COLUMNS that were created from a sequence of Tables
     *  [t0,...,tn] as well as ROWS [r0,...,rn] that were drawn from those
     *  same tables [t0,...,tn], constructs a new Row containing M values,
     *  where the ith value of this new Row is taken from the location given
     *  by the ith COLUMN (for each i from 0 to M-1).
     *
     *  More specifically, if _table is the Table number corresponding to
     *  COLUMN i, then the ith value of the newly created Row should come from
     *  ROWS[_table].
     *
     *  Even more specifically, the ith value of the newly created Row should
     *  be equal to ROWS[_table].get(_column), where _column is the column
     *  number in ROWS[_table] corresponding to COLUMN i.
     *
     *  There is a method in the Column class that you'll need to use, see
     *  {@link db61b.Column#getFrom}).  you're wondering why this looks like a
     *  potentially clickable link it is! Just not in source. You might
     *  consider converting this spec to HTML using the Javadoc command.
     */
    Row(List<Column> columns, Row... rows) {
        // FILL IN
        _data = new String[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            _data[i] = columns.get(i).getFrom(rows);
        }
    }

    /** Return my number of columns. */
    int size() {
        if(this._data == null){
            return 0;
        }
        return _data.length;
        // return 0;  // REPLACE WITH SOLUTION
    }

    /** Return the value of my Kth column.  Requires that 0 <= K < size(). */
    String get(int k) {
        if (k >= 0 && k < _data.length) {
            return _data[k];
        }
        else {
            return null;    // 在别的Class请求Get时看一下是需要return null还是需要throw error, 测试里需要返回null.
            // throw error("unexpected input");
        }
        // return null; // REPLACE WITH SOLUTION
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Row other = (Row) obj;
        if (other.hashCode() == this.hashCode()) {
            return true;
        }
        return false;
        // return false; // REPLACE WITH SOLUTION
    }

    /* NOTE: Whenever you override the .equals() method for a class, you
     * should also override hashCode so as to insure that if
     * two objects are supposed to be equal, they also return the same
     * .hashCode() value (the converse need not be true: unequal objects MAY
     * also return the same .hashCode()).  The hash code is used by certain
     * Java library classes to expedite searches (see Chapter 7 of Data
     * Structures (Into Java)). */

    @Override
    public int hashCode() {
        return Arrays.hashCode(_data);
    }

    /** Contents of this row. */
    private String[] _data;
}
