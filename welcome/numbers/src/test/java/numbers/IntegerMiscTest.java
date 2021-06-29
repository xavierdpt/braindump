package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IntegerTest {

  @Test
  void test() {

    // misc
    int int28 = 0;
    int result28 = Integer.hashCode(int28);
    assertEquals(((Number)int28).hashCode(), result28);

    // Number interface

    Integer int38 = 5;
    short result38 = int38.shortValue();
    assertEquals((short)5, result38);

    Integer int39 = 5;
    long result39 = int39.longValue();
    assertEquals(5L, result39);

    Integer int40 = 5;
    int result40 = int40.intValue();
    assertEquals(5, result40);

    Integer int42 = 5;
    float result42 = int42.floatValue();
    assertEquals(5F, result42);

    Integer int44 = 5;
    double result44 = int44.doubleValue();
    assertEquals(5D, result44);

    Integer int46 = 5;
    byte result46 = int46.byteValue();
    assertEquals((byte)5, result46);

  }
}
