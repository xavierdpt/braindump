import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

class GoodEqualityTest {
  @Test
  void test() {

    int x = 5;
    int y = 6;
    Integer boxedX = x;
    Integer boxedY = y;
    Integer nullZ = null;

    // int, int, returns true
    assertTrue(goodEquals(x, x));
    assertTrue(goodEquals(y, y));

    // int, int, returns false
    assertFalse(goodEquals(x, y));
    assertFalse(goodEquals(y, x));

    // Integer, Integer, returns true
    assertTrue(goodEquals(boxedX, boxedX));
    assertTrue(goodEquals(boxedY, boxedY));

    // Integer, Integer, returns false
    assertFalse(goodEquals(boxedX, boxedY));
    assertFalse(goodEquals(boxedY, boxedX));

    // Integer, Integer, one of them null
    // returns false
    assertFalse(goodEquals(boxedX, nullZ));
    assertFalse(goodEquals(boxedY, nullZ));
    assertFalse(goodEquals(nullZ, boxedX));
    assertFalse(goodEquals(nullZ, boxedY));

    // Integer, Integer, both null
    // return true
    assertTrue(goodEquals(nullZ, nullZ));

    // int, Integer, returns true
    assertTrue(goodEquals(x, boxedX));
    assertTrue(goodEquals(y, boxedY));

    // Integer, int, returns true
    assertTrue(goodEquals(boxedX, x));
    assertTrue(goodEquals(boxedY, y));

    // int, Integer, returns false
    assertFalse(goodEquals(x, boxedY));
    assertFalse(goodEquals(y, boxedX));

    // Integer, int, returns false
    assertFalse(goodEquals(boxedX, y));
    assertFalse(goodEquals(boxedY, x));

    // int, Integer, one of them null
    // returns false
    assertFalse(goodEquals(x, nullZ));
    assertFalse(goodEquals(y, nullZ));

    // Integer, int, one of them null
    // returns false
    assertFalse(goodEquals(nullZ, x));
    assertFalse(goodEquals(nullZ, y));

  }

  // this variant of the function may only be interesting for performance reason
  private boolean goodEquals(int x, int y) {
    return x == y;
  }

  // In practice, you could keep only this variant, and the primitive types will always be automatically boxed and
  // passed to Object::equals
  private boolean goodEquals(Integer x, Integer y) {
    return Objects.equals(x, y);
  }

  // these two variations are necessary to disambiguate calls with int and Integer or Integer and int
  private boolean goodEquals(int x, Integer y) {
    return goodEquals((Integer)x, y);
  }

  private boolean goodEquals(Integer x, int y) {
    return goodEquals(x, (Integer)y);
  }
}
