package numbers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NextTest {
  @Test
  void test() {



    byte b1 = 0;
    Byte b2 = b1;

    int compare = Byte.compare(b1, b1);
    int i2 = b2.compareTo(b2);

    int i = Byte.hashCode(b1);
    int i3 = b2.hashCode();

    int i1 = Byte.toUnsignedInt(b1);
    long l = Byte.toUnsignedLong(b1);





    assertTrue(true);
  }
}
