package db61b;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class TableTest {

    @Test
    public void testTableRead() {
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        t.print();
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }

    @Test
    public void testTableColumn() {
        System.out.println(System.getProperty("user.dir"));
        String test = System.getProperty("user.dir");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.dir"));
        // Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        // t.print();
        // assertEquals(3, t.columns());
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }

    @Test
    public void testTableGetTitle() {
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        // t.print();
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }
    
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TableTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        // System.exit(junit.textui.runClasses(TestTemplate.class));
    }
}
