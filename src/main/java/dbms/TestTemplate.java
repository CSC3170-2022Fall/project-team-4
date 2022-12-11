package dbms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class TestTemplate {

    @Test
    public void testRowSize() {
        Row r = new Row(new String[]{"This", "is", "a", "unit", "test!"});
        assertEquals(5, r.size());
    }

    @Test
    public void testRowGet() {
        Row r = new Row(new String[]{"This", "is", "a", "unit", "test!"});
        assertEquals("This", r.get(0));
        assertEquals("a", r.get(2));
        assertEquals(null, r.get(-1));
        assertEquals(null, r.get(5));
    }

    @Test
    public void testRowEqual() {
        Row r1 = new Row(new String[]{"This", "is", "a", "unit", "test!"});
        Row r2 = new Row(new String[]{"This", "is", "a", "unit", "test!"});
        Row r3 = new Row(new String[]{"is", "This", "a", "unit", "test!"});
        Row r4 = new Row(new String[]{"What", "is", "a", "unit", "test!"});
        assertEquals(true, r1.equals(r2));
        assertEquals(false, r1.equals(r3));
        assertEquals(false, r1.equals(r4));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestTemplate.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        // System.exit(junit.textui.runClasses(TestTemplate.class));
    }

}
