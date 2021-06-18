import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongToFloatTest {
  @Test
  void test() {
    // This test will fail at some point
    long l = Long.MAX_VALUE;
    while (l > Long.MIN_VALUE) {
      toFloatAndBack(l);
      --l;
    }
  }

  private void toFloatAndBack(long value) {
    Assertions.assertEquals(value, (long)((float)value));
  }
}
