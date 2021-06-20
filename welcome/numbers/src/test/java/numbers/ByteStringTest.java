package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteStringTest {
    @Test
    void test() {

        Byte b1 = 0;
        assertEquals("0", b1.toString());



        String s2 = "";
        Byte b2 = new Byte(s2);
/*
        String s3 = "";
        Byte b3 = Byte.parseByte(s3);

        String s4 = "";
        int r4 = 2;
        Byte b4 = Byte.parseByte(s4, r4);

 */

        String s5 = "";
        Byte b5 = Byte.decode(s5);

        String s6 = "";
        Byte b6 = Byte.valueOf(s6);

        String s7 = "";
        int r7 = 0;
        Byte b7 = Byte.valueOf(s7, r7);



    }
}
