package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntBitsTest {
  @Test
  void test() {

    assertEquals(0xF, Integer.rotateRight(0xF0, 4));
    assertEquals(0xF0, Integer.rotateLeft(0xF, 4));

    assertEquals(0x80808080, Integer.reverse(0x01010101));
    assertEquals(0x33221100, Integer.reverseBytes(0x00112233));

    assertEquals(2, Integer.bitCount(0x8080));
    assertEquals(7, Integer.numberOfTrailingZeros(0x80));
    assertEquals(24, Integer.numberOfLeadingZeros(0x80));

    assertEquals(0x80, Integer.lowestOneBit(0x8080));
    assertEquals(0x8000, Integer.highestOneBit(0x8080));


  }
}
