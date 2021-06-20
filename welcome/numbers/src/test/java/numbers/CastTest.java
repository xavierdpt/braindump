package numbers;


import org.junit.jupiter.api.Test;

class CastTest {
    @Test
    void test() {

        byte b = 0;
        short s = 0;
        int i = 0;
        long l = 0L;
        float f = 0F;
        double d = 0D;

        giveMeAByte(b);
        giveMeAByte((byte) s);
        giveMeAByte((byte) i);
        giveMeAByte((byte) l);
        giveMeAByte((byte) f);
        giveMeAByte((byte) d);

        giveMeAShort(b);
        giveMeAShort(s);
        giveMeAShort((short) i);
        giveMeAShort((short) l);
        giveMeAShort((short) f);
        giveMeAShort((short) d);

        giveMeAnInt(b);
        giveMeAnInt(s);
        giveMeAnInt(i);
        giveMeAnInt((int) l);
        giveMeAnInt((int) f);
        giveMeAnInt((int) d);

        giveMeALong(b);
        giveMeALong(s);
        giveMeALong(i);
        giveMeALong(l);
        giveMeALong((long) f);
        giveMeALong((long) d);

        giveMeAFloat(b);
        giveMeAFloat(s);
        giveMeAFloat(i);
        giveMeAFloat(l);
        giveMeAFloat(f);
        giveMeAFloat((float) d);

        giveMeADouble(b);
        giveMeADouble(s);
        giveMeADouble(i);
        giveMeADouble(l);
        giveMeADouble(f);
        giveMeADouble(d);
    }

    private void giveMeAByte(byte b) {

    }

    private void giveMeAShort(short s) {

    }

    private void giveMeALong(long l) {

    }

    private void giveMeAnInt(int i) {

    }

    private void giveMeAFloat(float f) {

    }

    private void giveMeADouble(double d) {

    }


}
