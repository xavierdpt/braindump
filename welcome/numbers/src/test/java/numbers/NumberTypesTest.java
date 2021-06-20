package numbers;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTypesTest {
    @Test
    void test() {
        assertEquals("-128", Objects.toString(Byte.MIN_VALUE));
        assertEquals("127", Objects.toString(Byte.MAX_VALUE));
        assertEquals(8, Byte.SIZE);
        assertEquals(1, Byte.BYTES);

        assertEquals("-32768", Objects.toString(Short.MIN_VALUE));
        assertEquals("32767", Objects.toString(Short.MAX_VALUE));
        assertEquals(16, Short.SIZE);
        assertEquals(2, Short.BYTES);

        assertEquals("-2147483648", Objects.toString(Integer.MIN_VALUE));
        assertEquals("2147483647", Objects.toString(Integer.MAX_VALUE));
        assertEquals(32, Integer.SIZE);
        assertEquals(4, Integer.BYTES);

        assertEquals("-9223372036854775808", Objects.toString(Long.MIN_VALUE));
        assertEquals("9223372036854775807", Objects.toString(Long.MAX_VALUE));
        assertEquals(64, Long.SIZE);
        assertEquals(8, Long.BYTES);

        assertEquals("1.4E-45", Objects.toString(Float.MIN_VALUE));
        assertEquals("3.4028235E38", Objects.toString(Float.MAX_VALUE));
        assertEquals(32, Float.SIZE);
        assertEquals(4, Float.BYTES);

        assertEquals("4.9E-324", Objects.toString(Double.MIN_VALUE));
        assertEquals("1.7976931348623157E308", Objects.toString(Double.MAX_VALUE));
        assertEquals(64, Double.SIZE);
        assertEquals(8, Double.BYTES);
    }
}
