import org.junit.jupiter.api.Test;

class AnotherTest {
    @Test
    void test() {
        /*
        for (byte b = Byte.MAX_VALUE; b > Byte.MIN_VALUE; --b) {
            System.out.println(toBinary(b) + " -- " + b);
        }*/
        /*-
        0000
        0001
        0010
        0011
        0100
        0101
        0110
        0111
        1000

         */
        //iandb(0x00000080);

        System.out.println(toBinary(-128));
    }

    private void iandb(int i) {
        System.out.println(i + " -- " + (byte) i);
    }

    private String toBinary(byte b) {
        StringBuilder sb = new StringBuilder();
        for (int i = Byte.SIZE - 1; i >= 0; --i) {
            sb.append((b & 1 << i) == 0 ? "0" : "1");
        }
        return sb.toString();
    }

    private String toBinary(int i) {
        StringBuilder sb = new StringBuilder();
        for (int s = Integer.SIZE - 1; s >= 0; --s) {
            sb.append((i & 1 << s) == 0 ? "0" : "1");
        }
        return sb.toString();
    }
}
