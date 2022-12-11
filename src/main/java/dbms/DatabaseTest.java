package dbms;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class DatabaseTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DatabaseTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

    private String[] table1 = {"This", "is", "for", "testing", "tabel 1"};
    private String[] table2 = {"This", "is", "for", "testing", "tabel 2"};
    private String name1 = "table1";
    private String name2 = "table2";

    @Test
    public void testPut() throws Throwable {
        Database testDB = new Database();
        Table t1 = new Table(table1);
        Table t2 = new Table(table2);
        testDB.put(name1, t1);
        testDB.put(name2, t2);
        assertEquals(t1, testDB.get(name1));
        assertEquals(t2, testDB.get(name2));
    }

}




