package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloatBitsTest {
  @Test
  void test() {

    assertEquals(0x7fc00000, Float.floatToRawIntBits(Float.NaN));

    assertEquals(0x00000000, Float.floatToRawIntBits(0F));
    assertEquals(0x3f800000, Float.floatToRawIntBits(1F));
    assertEquals(0x00000001, Float.floatToRawIntBits(Float.MIN_VALUE));
    assertEquals(0x00800000, Float.floatToRawIntBits(Float.MIN_NORMAL));
    assertEquals(0x7f7fffff, Float.floatToRawIntBits(Float.MAX_VALUE));
    assertEquals(0x7f800000, Float.floatToRawIntBits(Float.POSITIVE_INFINITY));

    assertEquals(0x80000000, Float.floatToRawIntBits(-0F));
    assertEquals(0xbf800000, Float.floatToRawIntBits(-1F));
    assertEquals(0xff800000, Float.floatToRawIntBits(Float.NEGATIVE_INFINITY));



  }
}
