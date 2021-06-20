package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTest {
    @Test
    void test() {
        Number number = 0;

        assertEquals(Byte.class, getClass(number.byteValue()));
        assertEquals(Short.class, getClass(number.shortValue()));
        assertEquals(Integer.class, getClass(number.intValue()));
        assertEquals(Long.class, getClass(number.longValue()));
        assertEquals(Float.class, getClass(number.floatValue()));
        assertEquals(Double.class, getClass(number.doubleValue()));
    }

    private Class<?> getClass(Number number) {
        return number.getClass();
    }
}
