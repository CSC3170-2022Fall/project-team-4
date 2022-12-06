// This is a SUGGESTED skeleton for a class that describes a single
// Condition (such as CCN = '99776').  You can throw this away if you
// want,  but it is a good idea to try to understand it first.
// Our solution changes or adds about 30 lines in this skeleton.

// Comments that start with "//" are intended to be removed from your
// solutions.
// TODO(2Func): Condition, test.
package db61b;

import java.util.List;

/** Represents a single 'where' condition in a 'select' command.
 *  @author */
class Condition {

    /** A Condition representing COL1 RELATION COL2, where COL1 and COL2
     *  are column designators. and RELATION is one of the
     *  strings "<", ">", "<=", ">=", "=", or "!=". */
    Condition(Column col1, String relation, Column col2) {
        _relation = relation;
        _col1 = col1;
        _col2 = col2;
        // YOUR CODE HERE
    }

    /** A Condition representing COL1 RELATION 'VAL2', where COL1 is
     *  a column designator, VAL2 is a literal value (without the
     *  quotes), and RELATION is one of the strings "<", ">", "<=",
     *  ">=", "=", or "!=".
     */
    Condition(Column col1, String relation, String val2) {
        this(col1, relation, (Column) null);
        _val2 = val2;
        _relation = relation;
    }

    /** Assuming that ROWS are rows from the respective tables from which
     *  my columns are selected, returns the result of performing the test I
     *  denote. */
    boolean test(Row... rows) {
        Double value_left, value_right;
        for (Row singlr_row : rows) {
            switch (_relation) {
                case "<":
                    // String1: singlr_row.get(_col1.getColumn())
                    // String2: singlr_row.get(_col2.getColumn())
                    value_left = Double.parseDouble(singlr_row.get(_col1.getColumn()));
                    if (_col2 == null) {
                        value_right = Double.parseDouble(_val2);
                    } else {
                        value_right = Double.parseDouble(singlr_row.get(_col2.getColumn()));
                    }
                    if (value_left < value_right) {
                        return true;
                    }
                    break;
                case ">":
                    value_left = Double.parseDouble(singlr_row.get(_col1.getColumn()));
                    if (_col2 == null) {
                        value_right = Double.parseDouble(_val2);
                    } else {
                        value_right = Double.parseDouble(singlr_row.get(_col2.getColumn()));
                    }
                    if (value_left > value_right) {
                        return true;
                    }
                    break;
                case "<=":
                    value_left = Double.parseDouble(singlr_row.get(_col1.getColumn()));
                    if (_col2 == null) {
                        value_right = Double.parseDouble(_val2);
                    } else {
                        value_right = Double.parseDouble(singlr_row.get(_col2.getColumn()));
                    }
                    if (value_left <= value_right) {
                        return true;
                    }
                    break;
                case ">=":
                    value_left = Double.parseDouble(singlr_row.get(_col1.getColumn()));
                    if (_col2 == null) {
                        value_right = Double.parseDouble(_val2);
                    } else {
                        value_right = Double.parseDouble(singlr_row.get(_col2.getColumn()));
                    }
                    if (value_left >= value_right) {
                        return true;
                    }
                    break;
                case "=":
                    if (_col2 == null) {
                        if (singlr_row.get(_col1.getColumn()).equals(_val2)) {
                            return true;
                        }
                    } else {
                        if (singlr_row.get(_col1.getColumn()).equals(singlr_row.get(_col2.getColumn()))) {
                            return true;
                        }
                    }
                    break;
                case "!=":
                    if (_col2 == null) {
                        if (!singlr_row.get(_col1.getColumn()).equals(_val2)) {
                            return true;
                        }
                    } else {
                        if (!singlr_row.get(_col1.getColumn()).equals(singlr_row.get(_col2.getColumn()))) {
                            return true;
                        }
                    }
                    break;
            }
        }
        return false;
        // // REPLACE WITH SOLUTION
        // return false;
    }

    /** Return true iff ROWS satisfies all CONDITIONS. */
    static boolean test(List<Condition> conditions, Row... rows) {
        for (Condition cond : conditions) {
            if (!cond.test(rows)) {
                return false;
            }
        }
        return true;
    }

    /** The operands of this condition.  _col2 is null if the second operand
     *  is a literal. */
    private Column _col1, _col2;
    /** Second operand, if literal (otherwise null). */
    private String _val2;
    // ADD ADDITIONAL FIELDS HERE
    private String _relation;
}
