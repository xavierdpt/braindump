package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberClassTest {

    @Test
    void test() {
        assertEquals(Byte.class, box(Byte.MAX_VALUE).getClass());
        assertEquals(Short.class, box(Short.MAX_VALUE).getClass());
        assertEquals(Integer.class, box(Integer.MAX_VALUE).getClass());
        assertEquals(Long.class, box(Long.MAX_VALUE).getClass());
        assertEquals(Float.class, box(Float.MAX_VALUE).getClass());
        assertEquals(Double.class, box(Double.MAX_VALUE).getClass());
    }

    private Number box(Number number) {
        return number;
    }
}
