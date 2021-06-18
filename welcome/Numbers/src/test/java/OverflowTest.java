import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OverflowTest {
  @Test
  void test() {

    byte b = Byte.MAX_VALUE;
    short s = Short.MAX_VALUE;
    int i = Integer.MAX_VALUE;
    long l = Long.MAX_VALUE;
    float f = Float.MAX_VALUE;
    double d = Double.MAX_VALUE;

    assertTrue(true);
    assertEquals(Byte.MIN_VALUE, ++b);
    assertEquals(Short.MIN_VALUE, ++s);
    assertEquals(Integer.MIN_VALUE, ++i);
    assertEquals(Long.MIN_VALUE, ++l);
    assertEquals(Float.MAX_VALUE, ++f);
    assertEquals(Double.MAX_VALUE, ++d);
  }
}
