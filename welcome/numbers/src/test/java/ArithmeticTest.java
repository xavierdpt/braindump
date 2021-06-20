import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ArithmeticTest {

  @Test
  void test() {
    assertEquals(Integer.class, getClassOfSum((byte)0).getClass());
    assertEquals(Integer.class, getClassOfSum((short)0).getClass());
    assertEquals(Integer.class, getClassOfSum(0).getClass());
    assertEquals(Long.class, getClassOfSum(0L).getClass());
    assertEquals(Float.class, getClassOfSum(0F).getClass());
    assertEquals(Double.class, getClassOfSum(0D).getClass());
  }

  private Number getClassOfSum(byte number) {
    return number + number;
  }

  private Number getClassOfSum(short number) {
    return number + number;
  }

  private Number getClassOfSum(int number) {
    return number + number;
  }

  private Number getClassOfSum(long number) {
    return number + number;
  }

  private Number getClassOfSum(float number) {
    return number + number;
  }

  private Number getClassOfSum(double number) {
    return number + number;
  }
}
