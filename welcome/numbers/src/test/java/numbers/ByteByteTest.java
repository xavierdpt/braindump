package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByteByteTest {
    @Test
    void test() {

        byte b = 0;
        Byte b1 = b;
        Byte b2 = new Byte(b);
        Byte b3 = Byte.valueOf(b);

        assertTrue(b1 == b3);
        assertFalse(b1 == b2);

        byte b4 = b1;
        byte b5 = b1.byteValue();
    }
}
