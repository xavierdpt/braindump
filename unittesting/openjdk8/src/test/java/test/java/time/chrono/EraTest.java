package test.java.time.temporal;

import org.junit.Test;

import java.time.chrono.Era;

public class EraTest {

    @Test
    public void test() {

        /* An Era is a thing that returns a value */

        int[] currentValue = new int[]{1};

        Era era = new Era() {
            @Override
            public int getValue() {
                return currentValue[0];
            }
        };
    }
}
