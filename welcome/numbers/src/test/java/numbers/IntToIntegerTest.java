package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntToIntegerTest {
  @Test
  void test() {

    int int1 = 5;

    Integer integer1 = int1;
    Integer integer2 = Integer.valueOf(int1);
    Integer integer3 = new Integer(int1);

    assertEquals(int1, integer1);
    assertEquals(int1, integer2);
    assertEquals(int1, integer3);

    int int2 = integer1;
    int int3 = integer1.intValue();

    assertEquals(integer1, int2);
    assertEquals(integer1, int3);

  }
}
