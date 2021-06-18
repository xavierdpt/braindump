import org.junit.jupiter.api.Test;

public class UpDownCastingTest {
  @Test
  void test() {

    byte b = 0;
    short s = 0;
    int i = 0;
    long l = 0L;
    float f = 0F;
    double d = 0D;

    fByte(b);
    fShort(b);
    fInt(b);
    fLong(b);
    fFloat(b);
    fDouble(b);

    fByte((byte) s);
    fShort(s);
    fInt(s);
    fLong(s);
    fFloat(s);
    fDouble(s);

    fByte((byte) i);
    fShort((short) i);
    fInt(i);
    fLong(i);
    fFloat(i);
    fDouble(i);

    fByte((byte) l);
    fShort((short) l);
    fInt((int) l);
    fLong(l);
    fFloat(l);
    fDouble(l);

    fByte((byte) f);
    fShort((short) f);
    fInt((int) f);
    fLong((long) f);
    fFloat(f);
    fDouble(f);

    fByte((byte) d);
    fShort((short) d);
    fInt((int) d);
    fLong((long) d);
    fFloat((float) d);
    fDouble(d);
  }

  private void fByte(byte b) {
  }

  private void fShort(short s) {
  }

  private void fInt(int b) {
  }

  private void fLong(long l) {
  }

  private void fFloat(float f) {
  }

  private void fDouble(double d) {
  }
}
