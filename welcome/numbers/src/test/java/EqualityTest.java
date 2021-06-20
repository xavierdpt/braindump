import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EqualityTest {

  @Test
  void test() {

    int small = 5;
    assertTrue(box(small) == box(small));

    int big = Integer.MAX_VALUE - 5;
    assertFalse(box(big) == box(big));
  }

  Integer box(int x) {
    return x;
  }
}
