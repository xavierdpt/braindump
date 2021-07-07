package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OtherByteMethodsTest {
  @Test
  void test() {

    byte b1 = 0;
    byte b2 = 1;

    Byte b3 = b1;
    Byte b4 = b2;

    assertTrue(Byte.compare(b1, b2) < 0);
    assertTrue(Byte.compare(b2, b1) > 0);

    assertTrue(b3.compareTo(b4) < 0);
    assertTrue(b4.compareTo(b3) > 0);

    assertEquals(Byte.hashCode(b1), b3.hashCode());

    assertEquals(0x80, Byte.toUnsignedInt(Byte.MIN_VALUE));
    assertEquals(0x80L, Byte.toUnsignedLong(Byte.MIN_VALUE));

    assertEquals(-0x80, Byte.MIN_VALUE);
    assertEquals(-0x80L, Byte.MIN_VALUE);

  }
}
