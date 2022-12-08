package db61b;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.*;

public class TestTable {

    @Test
    public void testTableRead() {
        System.out.println("testTableRead():");
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        t.print();
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }

    @Test
    public void testTableWrite() {
        System.out.println("testTableRead():");
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        t.print();
        t.writeTable("writeTest");
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }

    @Test
    public void testTableColumn() {
        // System.out.println(System.getProperty("user.dir"));
        System.out.println("testTableColumn():");
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        assertEquals(3, t.columns());
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }

    @Test
    public void testTableGetTitle() {
        System.out.println("testTableGetTitle()");
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        assertEquals("SID", t.getTitle(0));
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }
    
    @Test
    public void testTableFindColumn() {
        System.out.println("testTableFindColumn()");
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        assertEquals(0, t.findColumn("SID"));
        assertEquals(2, t.findColumn("Grade"));
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }

    @Test
    public void testTableSize() {
        System.out.println("testTableSize()");
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        assertEquals(7, t.size());
        // TODO: title need to be counted in the size or not?
        // Make sure you terminal start from "/project-team-4"
        // Or you will can not find the file from relative path.
    }

    // @Test
    // public void testTableSimpleSelect(){
    //     System.out.println("testTableSimpleSelect()");
    //     Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
    //     List<String> columnames = new ArrayList<>();
    //     List<String> temp = new ArrayList<>();
    //     columnames.add("CCN");
    //     columnames.add("Grade");
    //     List<Condition> condition = new ArrayList<>();

    //     Table t2 = t.select(columnames, condition); 
    //     //Still error in adding the rows (most likely because rows(columns, rows..row) has not been implemented)
    //     t2.print();
    //     assertEquals(2, t2.columns());
    // }

    @Test
    public void testTableSelect_1Table(){
        System.out.println("testTableSelect_1Table()");
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");

        Column c1 = new Column("SID", t);
        Column c2 = new Column("Grade", t);
        Condition con1 = new Condition(c1, ">=", "333");
        Condition con2 = new Condition(c1, "<", "555");
        Condition con3 = new Condition(c2, "<=", "8");
        List<Condition> cons = new ArrayList<Condition>();
        cons.add(con1);
        cons.add(con2);
        cons.add(con3);

        List<String> columnames = new ArrayList<>();
        columnames.add("CCN");
        columnames.add("Grade");

        Table t2 = t.select(columnames, cons);
        t2.print();
    }

    @Test
    public void testTableSelect_Multi_Table(){
        // Test not pass.
        System.out.println("testTableSelect_Multi_Table()");
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        Table t2 = Table.readTable("inst.eecs.berkeley.edu/testing/schedule");

        Column c1 = new Column("SID", t);
        Column c2 = new Column("Grade", t);
        Condition con1 = new Condition(c1, ">=", "333");
        Condition con2 = new Condition(c1, "<", "555");
        Condition con3 = new Condition(c2, "<=", "8");
        List<Condition> cons = new ArrayList<Condition>();
        cons.add(con1);
        cons.add(con2);
        cons.add(con3);

        List<String> columnames = new ArrayList<>();
        columnames.add("CCN");
        columnames.add("Grade");

        Table t_out = t.select(t2, columnames, cons);
        t_out.print();
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestTable.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        // System.exit(junit.textui.runClasses(TestTemplate.class));
    }
}
