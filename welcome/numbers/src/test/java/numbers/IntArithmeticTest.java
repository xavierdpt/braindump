package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegerArithmeticTest {
  @Test
  void test() {

    // arithmetic
    int left11 = 1;
    int right11 = 2;
    int result11 = Integer.sum(left11, right11);
    assertEquals(3, result11);

    int int12 = 1;
    int result12 = Integer.signum(int12);
    assertEquals(1, result12);

    int dividend17 = 17;
    int divisor17 = 5;
    int result17 = Integer.remainderUnsigned(dividend17, divisor17);
    assertEquals(2, result17);

    int left24 = 4;
    int right24 = 3;
    int result24 = Integer.min(left24, right24);
    assertEquals(3, result24);

    int left25 = 4;
    int right25 = 3;
    int result25 = Integer.max(left25, right25);
    assertEquals(4, result25);

    int dividend32 = 17;
    int divisor32 = 5;
    int result32 = Integer.divideUnsigned(dividend32, divisor32);
    assertEquals(3, result32);

    int left34 = 1;
    int right34 = 2;
    int result34 = Integer.compareUnsigned(left34, right34);
    assertTrue(result34 < 0);

    int left35 = 1;
    int right35 = 2;
    int result35 = Integer.compare(left35, right35);
    assertTrue(result35 < 0);

    Integer left45 = 1;
    Integer right45 = 2;
    int result45 = left45.compareTo(right45);
    assertTrue(result45 < 0);

  }
}
