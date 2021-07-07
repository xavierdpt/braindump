package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PrimitiveByteStringTest {
  @Test
  void test() {

    byte b1 = 5;
    assertEquals("5", Byte.toString(b1));

    byte b2 = Byte.parseByte("5");
    assertEquals((byte)5, b2);

    byte b3 = Byte.parseByte("10", 5);
    assertEquals((byte)5, b3);

  }
}
