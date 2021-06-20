package numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplicitCastTest {
    @Test
    void test() {

        // byte min value in decimal
        assertEquals((byte) 0x80, Byte.MIN_VALUE);
        assertEquals((byte) -128, Byte.MIN_VALUE);

        // byte min value sign-extended to an integer
        // note: these call the int version of assertEquals because assertEquals is explicitly defined for byte,byte
        // and int,int, and the first argument is an int (no explicit cast), so the second byte argument is implicitly
        // promoted to an int
        assertEquals(0xFFFFFF80, Byte.MIN_VALUE);
        assertEquals(-128, Byte.MIN_VALUE);

        // Down casting to byte ignores the 24 most significant bytes and keep only the 8 least significant bytes
        assertEquals((byte) 0xFFFFFF80, Byte.MIN_VALUE);
        assertEquals((byte) 0x00000080, Byte.MIN_VALUE);
        assertEquals((byte) 0x12345680, Byte.MIN_VALUE);
        assertEquals((byte) 0x00000580, Byte.MIN_VALUE);

        // In particular, since MIN_VALUE as all bits set to 0 except the most significant bit,
        // down casting that value to a smaller value gives 0
        // and, since MAX_VALUE as all bits set to 1 except the most significatn bit, down casting that value to a
        // smaller value gives 0
        assertEquals((byte) 0, (byte) Integer.MIN_VALUE);
        assertEquals((byte) -1, (byte) Integer.MAX_VALUE);

        // For conversion between float and integral, it is a bit more complicated
        assertEquals(Long.MAX_VALUE, (long) Float.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, (long) Double.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, (int) Float.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, (int) Double.MAX_VALUE);

        assertEquals(0, (long) Float.MIN_VALUE);
        assertEquals(0, (long) Double.MIN_VALUE);

        assertEquals(0, (int) Float.MIN_VALUE);
        assertEquals(0, (int) Double.MIN_VALUE);

        assertEquals(5, (int) 5F);
        assertEquals(5, (int) 5D);
        assertEquals(5L, (long) 5F);
        assertEquals(5L, (long) 5D);

        assertEquals(50, (int) 50F);
        assertEquals(50, (int) 50D);
        assertEquals(50L, (long) 50F);
        assertEquals(50L, (long) 50D);

        assertEquals(500000000, (int) 500000000F);
        assertEquals(500000000, (int) 500000000D);
        assertEquals(500000000L, (long) 500000000F);
        assertEquals(500000000L, (long) 500000000D);

        assertEquals(49999998976L, (long) 50000000000F);
        assertEquals(50000000000L, (long) 50000000000D);

    }
}
