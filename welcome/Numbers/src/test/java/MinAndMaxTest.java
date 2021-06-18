import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MinAndMaxTest {
  @Test
  void test() {

    assertEquals("-128", Byte.toString(Byte.MIN_VALUE));
    assertEquals("127", Byte.toString(Byte.MAX_VALUE));
    assertEquals("-32768", Short.toString(Short.MIN_VALUE));
    assertEquals("32767", Short.toString(Short.MAX_VALUE));
    assertEquals("-2147483648", Integer.toString(Integer.MIN_VALUE));
    assertEquals("2147483647", Integer.toString(Integer.MAX_VALUE));
    assertEquals("-9223372036854775808", Long.toString(Long.MIN_VALUE));
    assertEquals("9223372036854775807", Long.toString(Long.MAX_VALUE));
    assertEquals("1.4E-45", Float.toString(Float.MIN_VALUE));
    assertEquals("3.4028235E38", Float.toString(Float.MAX_VALUE));
    assertEquals("4.9E-324", Double.toString(Double.MIN_VALUE));
    assertEquals("1.7976931348623157E308", Double.toString(Double.MAX_VALUE));

    assertTrue(true);
  }
}
