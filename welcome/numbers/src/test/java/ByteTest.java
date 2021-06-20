import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteTest {
  @Test
  void test() {

    byte myByte = Byte.MAX_VALUE;

    // byte to Byte
    Byte byte1 = myByte;
    Byte byte2 = new Byte(myByte);
    Byte byte3 = Byte.valueOf(myByte);

    // Byte to byte
    byte bbyte1 = byte1.byteValue();

    // String to Byte
    Byte sb1 = new Byte("1");
    Byte sb2 = Byte.decode("1");
    byte sb3 = Byte.parseByte("1");
    byte sb4 = Byte.parseByte("1", 10);
    Byte sb5 = Byte.valueOf("1");
    Byte sb6 = Byte.valueOf("1", 10);

    // Byte to String
    String bs1 = byte1.toString();

    // byte to String
    Byte.toString(myByte);

    // Byte to short
    byte1.shortValue();

    // Byte to int
    byte1.intValue();

    // Byte to long
    byte1.longValue();

    // Byte to float
    byte1.floatValue();

    // Byte to double
    byte1.doubleValue();

    // byte to int
    Byte.toUnsignedInt(myByte);

    // byte to long
    Byte.toUnsignedLong(myByte);

    // Object functions
    byte1.hashCode();
    byte1.equals(byte1);

    // hashcode for byte
    Byte.hashCode(myByte);

    // comparison of Byte
    byte1.compareTo(byte1);

    // comparison of byte
    Byte.compare(myByte, myByte);

  }
}
