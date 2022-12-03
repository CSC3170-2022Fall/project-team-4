package db61b;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.*;

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
    
    @Test
    public void testTableSimpleSelect(){
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        List<String> columnames = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        columnames.add("CCN");
        columnames.add("Grade");
        List<Condition> condition = new ArrayList<>();

        Table t2 = t.select(columnames, condition); 
        //Still error in adding the rows (most likely because rows(columns, rows..row) has not been implemented)
        t2.print();
        assertEquals(2, t2.columns());


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
