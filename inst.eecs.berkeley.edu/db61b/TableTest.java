package db61b;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class TableTest {

    @Test
    public void testTableRead() {
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/enrolled");
        t.print();
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
