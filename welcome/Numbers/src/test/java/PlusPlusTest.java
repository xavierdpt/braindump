import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PlusPlusTest {
  @Test
  void test() {
    assertEquals(Byte.class, bytePlusPlus((byte)0).getClass());
    assertEquals(Short.class, shortPlusPlus((short)0).getClass());
    assertEquals(Integer.class, intPlusPlus(0).getClass());
    assertEquals(Long.class, longPlusPlus(0L).getClass());
    assertEquals(Float.class, floatPlusPlus(0F).getClass());
    assertEquals(Double.class, doublePlusPlus(0D).getClass());
  }

  private Number bytePlusPlus(byte b) {
    return ++b;
  }

  private Number shortPlusPlus(short s) {
    return ++s;
  }

  private Number intPlusPlus(int i) {
    return ++i;
  }

  private Number longPlusPlus(long l) {
    return ++l;
  }

  private Number floatPlusPlus(float f) {
    return ++f;
  }

  private Number doublePlusPlus(double d) {
    return ++d;
  }
}
