import org.junit.jupiter.api.Test;

class ArithmeticCastTest {
  @Test
  void test() {
    byte b = 0;
    short s = 0;
    int i = 0;
    long l = 0L;
    float f = 0F;
    double d = 0D;

    byteMinus1((byte)(b + b));
    shortMinus1((short)(s + s));
    intMinus1(i + i);
    longMinus1(l + l);
    floatMinus1(f + f);
    doubleMinus1(d + d);
  }

  private byte byteMinus1(byte b) {
    return (byte)(b - 1);
  }

  private short shortMinus1(short s) {
    return (short)(s - 1);
  }

  private int intMinus1(int i) {
    return i - 1;
  }

  private long longMinus1(long l) {
    return l - 1;
  }

  private float floatMinus1(float f) {
    return f - 1F;
  }

  private double doubleMinus1(double d) {
    return d - 1D;
  }
}
