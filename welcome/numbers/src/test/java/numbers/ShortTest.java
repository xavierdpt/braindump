package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ShortTest {
  @Test
  void test() {
    assertEquals((short)0xFF00, Short.reverseBytes((short)0x00FF));
    assertEquals((short)0x00FF, Short.reverseBytes((short)0xFF00));

    assertEquals((short)0x8000, Short.MIN_VALUE);
    assertEquals((short)0x0080, Short.reverseBytes(Short.MIN_VALUE));

    assertEquals((short)0x7FFF, Short.MAX_VALUE);
    assertEquals((short)0xFF7F, Short.reverseBytes(Short.MAX_VALUE));
  }
}
