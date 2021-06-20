package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssignValuesTest {
    @Test
    void test() {

        // box takes a Number and returns that Number
        // these primitive values are implicitly boxed
        assertEquals(Byte.class, box((byte) 0).getClass());
        assertEquals(Short.class, box((short) 0).getClass());
        assertEquals(Integer.class, box(0).getClass());
        assertEquals(Long.class, box(0L).getClass());
        assertEquals(Float.class, box(0F).getClass());
        assertEquals(Double.class, box(0D).getClass());

        // floating point without F or D suffix is a double
        assertEquals(Double.class, box(0.).getClass());

        // Assignment to a byte does not require an explicit cast
        byte b = 0;
        byte s = 0;
        assertEquals(Byte.class, box(b).getClass());
        assertEquals(Short.class, box(s).getClass());

        // But calling a function that takes a byte or a short as argument requires an explicit cast
        assertEquals((byte) 0, giveMeAByte((byte) 0));
        assertEquals((short) 0, giveMeAShort((short) 0));
    }

    private byte giveMeAByte(byte b) {
        // But returning a constant byte or short does not require an explicit cast
        return 0;
    }

    private short giveMeAShort(short s) {
        return 0;
    }

    private Number box(Number number) {
        return number;
    }
}
