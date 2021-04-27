package test.java.time.temporal;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ValueRangeTest {
    @Test
    public void test() throws IOException, ClassNotFoundException {
        ValueRange vr01 = ValueRange.of(0L, 1L);
        ValueRange valueRange = vr01;
        assertNotNull(valueRange);

        assertThrows(IllegalArgumentException.class, () -> ValueRange.of(1, 0));

        assertNotNull(ValueRange.of(0L, 1L, 2L));

        ValueRange vr0123 = ValueRange.of(0L, 1L, 2L, 3L);
        assertNotNull(vr0123);
        assertThrows(IllegalArgumentException.class,
                () -> ValueRange.of(1L, 0L, 2L, 3L)
        );
        assertThrows(IllegalArgumentException.class,
                () -> ValueRange.of(0L, 1L, 3L, 2L)
        );
        assertThrows(IllegalArgumentException.class,
                () -> ValueRange.of(2L, 3L, 0L, 1L)
        );

        assertTrue(ValueRange.of(0L, 0L, 3L, 3L).isFixed());
        assertFalse(ValueRange.of(0L, 1L, 3L, 3L).isFixed());
        assertFalse(vr0123.isFixed());

        assertEquals(0L, vr0123.getMinimum());
        assertEquals(1L, vr0123.getLargestMinimum());
        assertEquals(2L, vr0123.getSmallestMaximum());
        assertEquals(3L, vr0123.getMaximum());

        assertTrue(vr01.isIntValue());
        assertFalse(ValueRange.of(Long.MIN_VALUE, 1L).isIntValue());
        assertFalse(ValueRange.of(0L, Long.MAX_VALUE).isIntValue());

        assertTrue(vr01.isValidValue(1L));
        assertFalse(vr01.isValidValue(-1L));
        assertFalse(vr01.isValidValue(2L));

        assertTrue(vr01.isValidIntValue(0L));
        assertFalse(vr01.isValidIntValue(Long.MAX_VALUE));
        assertFalse(vr01.isValidIntValue(2L));

        TemporalField temporalField = new TemporalFieldBuilder().build();
        assertEquals(0L, vr01.checkValidValue(0L, temporalField));
        assertThrows(DateTimeException.class,
                () -> vr01.checkValidValue(2L, temporalField));

        assertEquals(0L, vr01.checkValidIntValue(0L, temporalField));
        assertThrows(DateTimeException.class,
                () -> vr01.checkValidIntValue(2L, temporalField));

        assertEquals(0L, vr01.checkValidValue(0L, null));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(vr01);
        assertNotNull(new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject());

        assertEquals("0 - 1", vr01.toString());
        assertEquals("0/1 - 2/3", vr0123.toString());
    }
}
