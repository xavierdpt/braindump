import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NumbersTest {

  @Test
  void test1() {
    int x = 5;
    Integer[] numbers = new Integer[] { x };
    foo(x);
    foo(numbers[0]);
  }

  private void foo(int x) {
    System.out.println("I am " + x);
  }

  private void foo(Integer x) {
    System.out.println("This is " + x);
  }

  @Test
  void test() {
    Number[] numbers = new Number[] {
        Byte.MIN_VALUE,
        Byte.MAX_VALUE,
        Short.MIN_VALUE,
        Short.MAX_VALUE,
        Integer.MIN_VALUE,
        Integer.MAX_VALUE,
        Long.MIN_VALUE,
        Long.MAX_VALUE,
        Float.MIN_VALUE,
        Float.MAX_VALUE,
        Double.MIN_VALUE,
        Double.MAX_VALUE
    };
  }
}
