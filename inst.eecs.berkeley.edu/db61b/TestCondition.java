package db61b;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestCondition {

    @Test
    public void testNewCondition() {
        Table t = Table.readTable("inst.eecs.berkeley.edu/testing/testinput");
        Column c = new Column("SID", t);
        Condition con1 = new Condition(c, "=", "444");
        List<Condition> cons = new ArrayList<Condition>();
        cons.add(con1);
        Row[] rs = new Row[5];
        rs[0] = new Row(new String[]{"111", "11111", "1"});
        rs[1] = new Row(new String[]{"222", "22222", "2"});
        rs[2] = new Row(new String[]{"333", "33333", "3"});
        rs[3] = new Row(new String[]{"444", "44444", "4"});
        rs[4] = new Row(new String[]{"555", "55555", "5"});
        assertEquals(true, Condition.test(cons, rs));
        assertEquals(true, con1.test(rs));
        Condition con2 = new Condition(c, ">", "555");
        assertEquals(false, con2.test(rs));
        Condition con3 = new Condition(c, ">=", "555");
        assertEquals(true, con3.test(rs));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCondition.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        // System.exit(junit.textui.runClasses(TestTemplate.class));
    }

}