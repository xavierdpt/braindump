package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntToStringTest {
  @Test
  void test() {

    // int to String

    assertEquals("8", Integer.toString(8));
    assertEquals("-8", Integer.toString(-8));

    assertEquals("1000", Integer.toString(8, 2));
    assertEquals("-1000", Integer.toString(-8, 2));

    assertEquals("11111111", Integer.toBinaryString(255));
    assertEquals("377", Integer.toOctalString(255));
    assertEquals("ff", Integer.toHexString(255));

    assertEquals("5", Integer.toUnsignedString(5));
    assertEquals("4294967291", Integer.toUnsignedString(-5));

    assertEquals("1000", Integer.toUnsignedString(8, 2));
    assertEquals("11111111111111111111111111111000", Integer.toUnsignedString(-8, 2));

    // String to int

    assertEquals(10, Integer.parseInt("10"));
    assertEquals(-10, Integer.parseInt("-10"));

    assertEquals(0x10, Integer.parseInt("10", 16));
    assertEquals(-0x10, Integer.parseInt("-10", 16));

    assertEquals(10, Integer.parseUnsignedInt("10"));
    assertEquals(-1, Integer.parseUnsignedInt("FFFFFFFF", 16));

  }
}
