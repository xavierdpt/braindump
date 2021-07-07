package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntArithmeticTest {
  @Test
  void test() {

    assertEquals(3, Integer.sum(1, 2));
    
    assertEquals(3, Integer.min(4, 3));
    assertEquals(4, Integer.max(4, 3));

    assertEquals(1, Integer.signum(1));
    assertEquals(0, Integer.signum(0));
    assertEquals(-1, Integer.signum(-1));

    assertTrue(Integer.compare(1, 2) < 0);

    assertEquals(2, Integer.remainderUnsigned(17, 5));
    assertEquals(3, Integer.divideUnsigned(17, 5));
    assertTrue(Integer.compareUnsigned(1, 2) < 0);


    Integer leftInteger = 1;
    Integer rightInteger = 2;
    assertTrue(leftInteger.compareTo(rightInteger) < 0);

  }
}
