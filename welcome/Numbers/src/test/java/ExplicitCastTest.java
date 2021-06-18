import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExplicitCastTest {

  @Test
  void test() {

    assertEquals(-1, (byte)Short.MAX_VALUE);
    assertEquals(-1, (short)Integer.MAX_VALUE);
    assertEquals(-1, (int)Long.MAX_VALUE);

    assertEquals(0, (byte)Short.MIN_VALUE);
    assertEquals(0, (short)Integer.MIN_VALUE);
    assertEquals(0, (int)Long.MIN_VALUE);
  }

  @Test
  void testFloatAndDouble() {

    assertEquals(0L, (long)Double.NaN);
    assertEquals(Long.MAX_VALUE, (long)Double.POSITIVE_INFINITY);
    assertEquals(Long.MIN_VALUE, (long)Double.NEGATIVE_INFINITY);

    assertEquals(0, (int)Double.NaN);
    assertEquals(Integer.MAX_VALUE, (int)Double.POSITIVE_INFINITY);
    assertEquals(Integer.MIN_VALUE, (int)Double.NEGATIVE_INFINITY);

    assertEquals((short)0, (short)Double.NaN);
    assertEquals((short)-1, (short)Double.POSITIVE_INFINITY);
    assertEquals((short)0, (short)Double.NEGATIVE_INFINITY);

    assertEquals((short)0, (byte)Double.NaN);
    assertEquals((byte)-1, (byte)Double.POSITIVE_INFINITY);
    assertEquals((byte)0, (byte)Double.NEGATIVE_INFINITY);

    assertEquals(0L, (long)Float.NaN);
    assertEquals(Long.MAX_VALUE, (long)Float.POSITIVE_INFINITY);
    assertEquals(Long.MIN_VALUE, (long)Float.NEGATIVE_INFINITY);

    assertEquals(0, (int)Float.NaN);
    assertEquals(Integer.MAX_VALUE, (int)Float.POSITIVE_INFINITY);
    assertEquals(Integer.MIN_VALUE, (int)Float.NEGATIVE_INFINITY);

    assertEquals((short)0, (short)Float.NaN);
    assertEquals((short)-1, (short)Float.POSITIVE_INFINITY);
    assertEquals((short)0, (short)Float.NEGATIVE_INFINITY);

    assertEquals((short)0, (byte)Float.NaN);
    assertEquals((byte)-1, (byte)Float.POSITIVE_INFINITY);
    assertEquals((byte)0, (byte)Float.NEGATIVE_INFINITY);

    assertEquals(Float.NaN, (float)Double.NaN);
    assertEquals(Float.POSITIVE_INFINITY, (float)Double.POSITIVE_INFINITY);
    assertEquals(Float.NEGATIVE_INFINITY, (float)Double.NEGATIVE_INFINITY);
  }

  @Test
  void testMinNormal() {
    assertEquals(0F, (float)Double.MIN_NORMAL);
    assertTrue(Double.MIN_NORMAL < (double)Float.MIN_NORMAL);
    assertEquals(Float.MIN_NORMAL, (float)(double)Float.MIN_NORMAL);
  }
}
