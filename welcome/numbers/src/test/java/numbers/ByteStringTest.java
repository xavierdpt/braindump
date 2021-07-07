package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ByteStringTest {

  @Test
  void test() {

    Byte b1 = 0;
    assertEquals("0", b1.toString());

    assertEquals((byte)0, new Byte("0"));

    assertEquals((byte)0, Byte.valueOf("0"));
    assertThrows(NumberFormatException.class, () -> Byte.valueOf("0x0"));

    assertEquals((byte)0, Byte.valueOf("0", 2));
    assertEquals((byte)1, Byte.valueOf("1", 2));
    assertEquals((byte)2, Byte.valueOf("10", 2));
    assertEquals((byte)3, Byte.valueOf("11", 2));
    assertEquals((byte)4, Byte.valueOf("100", 2));
    assertEquals(Byte.MAX_VALUE, Byte.valueOf("1111111", 2));
    assertEquals(Byte.MIN_VALUE, Byte.valueOf("-10000000", 2));

    assertEquals((byte)0, Byte.valueOf("0", 3));
    assertEquals((byte)1, Byte.valueOf("1", 3));
    assertEquals((byte)2, Byte.valueOf("2", 3));
    assertEquals((byte)3, Byte.valueOf("10", 3));
    assertEquals((byte)4, Byte.valueOf("11", 3));
    assertEquals((byte)5, Byte.valueOf("12", 3));
    assertEquals((byte)6, Byte.valueOf("20", 3));
    assertEquals((byte)7, Byte.valueOf("21", 3));
    assertEquals((byte)8, Byte.valueOf("22", 3));
    assertEquals((byte)9, Byte.valueOf("100", 3));
    assertEquals(Byte.MAX_VALUE, Byte.valueOf("11201", 3));

    assertEquals((byte)0, Byte.valueOf("0", 16));
    assertEquals((byte)10, Byte.valueOf("A", 16));
    assertEquals((byte)15, Byte.valueOf("F", 16));
    assertEquals(Byte.MAX_VALUE, Byte.valueOf("7F", 16));

    assertEquals((byte)0, Byte.decode("0"));
    assertEquals((byte)15, Byte.decode("0xF"));
    assertEquals((byte)15, Byte.decode("0XF"));
    assertEquals((byte)15, Byte.decode("#F"));
    assertEquals((byte)64, Byte.decode("0100"));

  }
}
