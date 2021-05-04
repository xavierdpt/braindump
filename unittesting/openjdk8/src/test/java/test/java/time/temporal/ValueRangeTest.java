package test.java.time.temporal;

import org.junit.Test;
import test.TestUtil;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.temporal.ValueRange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ValueRangeTest {
    @Test
    public void test() throws IOException, ClassNotFoundException {

        /* A ValueRange is defined by a smallest minimum, a largest minimum, a smallest maximum and a largest maximum */

        ValueRange vr0123 = ValueRange.of(0L, 1L, 2L, 3L);
        assertEquals(0L, vr0123.getMinimum());
        assertEquals(1L, vr0123.getLargestMinimum());
        assertEquals(2L, vr0123.getSmallestMaximum());
        assertEquals(3L, vr0123.getMaximum());

        /* A value is valid if it is between the smallest minimum and the largest maximum */
        assertTrue(vr0123.isValidValue(0L));
        assertFalse(vr0123.isValidValue(4L));

        /* checkValidValue throws or returns the value, the temporal field is used only for the error message in the exception, and omitted otherwise */
        assertEquals(0L, vr0123.checkValidValue(0L, null));
        assertThrows(DateTimeException.class, () -> vr0123.checkValidValue(4L, null));

        /* If the largest minimum and smallest maximum are not specified, they are set to the min and max */

        ValueRange vr01 = ValueRange.of(0L, 1L);
        assertEquals(0L, vr01.getMinimum());
        assertEquals(0L, vr01.getLargestMinimum());
        assertEquals(1L, vr01.getSmallestMaximum());
        assertEquals(1L, vr01.getMaximum());

        /* A value range is fixed when there is a single min and a single max */
        assertTrue(vr01.isFixed());
        assertFalse(vr0123.isFixed());

        /* With three arguments, the minimum is fixed, and the maximum is variable */
        /* This exists because it is more common for the minimum to be fixed and the maximum to be variable */

        ValueRange vr012 = ValueRange.of(0L, 1L, 2L);
        assertEquals(0L, vr012.getMinimum());
        assertEquals(0L, vr012.getLargestMinimum());
        assertEquals(1L, vr012.getSmallestMaximum());
        assertEquals(2L, vr012.getMaximum());

        /* Here are ranges that cover Integer and Long */
        ValueRange vrInteger = ValueRange.of(Integer.MIN_VALUE, Integer.MAX_VALUE);
        ValueRange vrLong = ValueRange.of(Long.MIN_VALUE, Long.MAX_VALUE);

        /* A range that covers more than the integer answer false to isIntValue() */

        assertTrue(vrInteger.isIntValue());
        assertFalse(vrLong.isIntValue());

        /* isValidIntValue() always return false for range that covers more than the integers */
        assertTrue(vrInteger.isValidIntValue(0L));
        assertFalse(vrLong.isValidIntValue(0L));

        /* and checkValidIntValue() throws for ranges that cover more than the integers */
        assertEquals(0L, vrInteger.checkValidIntValue(0L, null));
        assertThrows(DateTimeException.class,
                () -> vrLong.checkValidIntValue(0L, null));

        /* ValueRange defines equals, hashCode and methods for serialization */
        assertEquals(vr01, vr01);
        assertNotEquals(vr01, vr0123);
        assertTrue(vr01.hashCode() > 0);
        TestUtil.serDeser(vr01);

        /* Invalid arguments combination generate an exception */
        assertThrows(IllegalArgumentException.class, () -> ValueRange.of(1L, 0L));
    }
}
