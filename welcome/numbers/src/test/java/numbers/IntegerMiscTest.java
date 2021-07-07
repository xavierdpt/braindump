package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IntegerMiscTest {

  @Test
  void test() {

    // misc
    int int1 = 0;
    assertEquals(((Number)int1).hashCode(),
        Integer.hashCode(int1));

    // Number interface

    Integer integer1 = 5;
    assertEquals((byte)5, integer1.byteValue());
    assertEquals((short)5, integer1.shortValue());
    assertEquals(5, integer1.intValue());
    assertEquals(5L, integer1.longValue());
    assertEquals(5F, integer1.floatValue());
    assertEquals(5D, integer1.doubleValue());

  }
}
