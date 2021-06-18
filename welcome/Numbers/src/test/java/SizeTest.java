import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SizeTest {

  @Test
  void test() {

    assertEquals(8, Byte.SIZE);
    assertEquals(1, Byte.BYTES);

    assertEquals(16, Short.SIZE);
    assertEquals(2, Short.BYTES);

    assertEquals(32, Integer.SIZE);
    assertEquals(4, Integer.BYTES);

    assertEquals(64, Long.SIZE);
    assertEquals(8, Long.BYTES);

    assertEquals(32, Float.SIZE);
    assertEquals(4, Float.BYTES);

    assertEquals(64, Double.SIZE);
    assertEquals(8, Double.BYTES);
  }
}
