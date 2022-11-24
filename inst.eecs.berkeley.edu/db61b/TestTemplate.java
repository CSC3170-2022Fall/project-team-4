package db61b;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class TestTemplate {

    @Test
    public void testRow() {
        Row r = new Row(new String[]{"This", "is", "a", "unit", "test!"});
        assertEquals(5, r.size());
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
