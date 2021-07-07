package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatTest {
  @Test
  void test() {

    assertTrue(Float.isFinite(0F));
    assertFalse(Float.isFinite(Float.NaN));
    assertFalse(Float.isFinite(Float.POSITIVE_INFINITY));
    assertFalse(Float.isFinite(Float.NEGATIVE_INFINITY));

    assertFalse(Float.isInfinite(0F));
    assertFalse(Float.isInfinite(Float.NaN));
    assertTrue(Float.isInfinite(Float.POSITIVE_INFINITY));
    assertTrue(Float.isInfinite(Float.NEGATIVE_INFINITY));

    assertFalse(Float.isNaN(0F));
    assertTrue(Float.isNaN(Float.NaN));
    assertFalse(Float.isNaN(Float.POSITIVE_INFINITY));
    assertFalse(Float.isNaN(Float.NEGATIVE_INFINITY));

    assertEquals(0F, Float.max(0F, -0F));
    assertEquals(1F,
        Float.max(0F, 1F));
    assertEquals(Float.POSITIVE_INFINITY,
        Float.max(0F, Float.POSITIVE_INFINITY));
    assertEquals(0F,
        Float.max(0F, Float.NEGATIVE_INFINITY));
    assertEquals(Float.POSITIVE_INFINITY,
        Float.max(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY));
    assertEquals(Float.NaN,
        Float.max(0F, Float.NaN));
    assertEquals(Float.NaN,
        Float.max(Float.NaN, Float.POSITIVE_INFINITY));
    assertEquals(Float.NaN,
        Float.max(Float.NaN, Float.NEGATIVE_INFINITY));

    assertEquals(0F, Float.min(0F, 1F));
    assertEquals(-0F, Float.min(0F, -0F));

    assertEquals(3F, Float.sum(1F, 2F));

    boolean incrementError = false;
    for (int i = 0; i < Integer.MAX_VALUE / 2 - 1; ++i) {
      float fi = Integer.valueOf(i).floatValue();
      float fi1 = Integer.valueOf(i + 1).floatValue();
      if (fi1 != Float.sum(fi, 1F)) {
        incrementError = true;
        System.out.println(i);
        System.out.println(Integer.MAX_VALUE);
        break;
      }
    }
    assertTrue(incrementError);

    assertEquals(0x7f800000, Float.floatToRawIntBits(Float.POSITIVE_INFINITY));
    assertEquals(0xff800000, Float.floatToRawIntBits(Float.NEGATIVE_INFINITY));
    assertEquals(0x00000000, Float.floatToRawIntBits(0F));
    assertEquals(0x80000000, Float.floatToRawIntBits(-0F));
    assertEquals(0x7fc00000, Float.floatToRawIntBits(Float.NaN));
    assertEquals(0x7f7fffff, Float.floatToRawIntBits(Float.MAX_VALUE));
    assertEquals(0x00000001, Float.floatToRawIntBits(Float.MIN_VALUE));
    assertEquals(0x00800000, Float.floatToRawIntBits(Float.MIN_NORMAL));

    assertEquals("0x0.0p0", Float.toHexString(0F));
    assertEquals("-0x0.0p0", Float.toHexString(-0F));
    assertEquals("NaN", Float.toHexString(Float.NaN));
    assertEquals("Infinity", Float.toHexString(Float.POSITIVE_INFINITY));
    assertEquals("-Infinity", Float.toHexString(Float.NEGATIVE_INFINITY));
    assertEquals("0x1.fffffep127", Float.toHexString(Float.MAX_VALUE));
    assertEquals("0x0.000002p-126", Float.toHexString(Float.MIN_VALUE));
    assertEquals("0x1.0p-126", Float.toHexString(Float.MIN_NORMAL));

    assertEquals("0.0", Float.toString(0F));
    assertEquals("-0.0", Float.toString(-0F));
    assertEquals("NaN", Float.toString(Float.NaN));
    assertEquals("Infinity", Float.toString(Float.POSITIVE_INFINITY));
    assertEquals("-Infinity", Float.toString(Float.NEGATIVE_INFINITY));
    assertEquals("3.4028235E38", Float.toString(Float.MAX_VALUE));
    assertEquals("1.4E-45", Float.toString(Float.MIN_VALUE));
    assertEquals("1.17549435E-38", Float.toString(Float.MIN_NORMAL));

    // TODO: produce non canonical NaN values
    assertEquals(0x00000000, Float.floatToIntBits(0F));
    assertEquals(0x80000000, Float.floatToIntBits(-0F));
    assertEquals(0x7fc00000, Float.floatToIntBits(Float.NaN));
    assertEquals(0x7f800000, Float.floatToIntBits(Float.POSITIVE_INFINITY));
    assertEquals(0xff800000, Float.floatToIntBits(Float.NEGATIVE_INFINITY));
    assertEquals(0x7f7fffff, Float.floatToIntBits(Float.MAX_VALUE));
    assertEquals(0x00000001, Float.floatToIntBits(Float.MIN_VALUE));
    assertEquals(0x00800000, Float.floatToIntBits(Float.MIN_NORMAL));

    assertEquals(0F, Float.intBitsToFloat(0x0));

    assertNotEquals(0F, -0F);
    assertTrue(Float.compare(Float.NEGATIVE_INFINITY, -0F) < 0);
    assertTrue(Float.compare(-0F, 0F) < 0);
    assertTrue(Float.compare(0F, Float.POSITIVE_INFINITY) < 0);
    assertTrue(Float.compare(Float.POSITIVE_INFINITY, Float.NaN) < 0);

    assertEquals(Float.valueOf(0F).hashCode(), Float.hashCode(0F));

    float r11 = Float.parseFloat("0");
    assertEquals(0F, r11);

    float r11x = Float.parseFloat("0F");
    assertEquals(0F, r11x);

    if (System.currentTimeMillis() < 0) {
      Object[] objects = new Object[] {
          Float.MAX_EXPONENT,
          Float.MIN_EXPONENT,
      };

      float x19 = 0;
      Float r15 = Float.valueOf(x19);
      assertEquals(0, r15);

      String x20 = null;
      Float r16 = Float.valueOf(x20);
      assertEquals(0, r16);

      Float f = 0F;
      byte r17 = f.byteValue();
      assertEquals(0, r17);

      Float x21 = null;
      int r18 = f.compareTo(x21);
      assertEquals(0, r18);

      double r19 = f.doubleValue();
      assertEquals(0, r19);

      Object x22 = null;
      boolean r20 = f.equals(x22);
      assertFalse(r20);

      float r21 = f.floatValue();
      assertEquals(0, r21);

      int r22 = f.hashCode();
      assertEquals(0, r22);

      int r23 = f.intValue();
      assertEquals(0, r23);

      boolean r24 = f.isInfinite();
      assertFalse(r24);

      boolean r25 = f.isNaN();
      assertFalse(r25);

      long r26 = f.longValue();
      assertEquals(0, r26);

      short r27 = f.shortValue();
      assertEquals(0, r27);

      String r28 = f.toString();
      assertEquals("", r28);

    }
  }
}
