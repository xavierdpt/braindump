package numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoxingTest {
    @Test
    void test() {
        byte primitive = Byte.MAX_VALUE;
        Byte boxed = primitive;

        assertEquals("onlyPrimitive", onlyPrimitive(primitive));
        assertEquals("onlyPrimitive", onlyPrimitive(boxed));

        assertEquals("onlyBoxed", onlyBoxed(primitive));
        assertEquals("onlyBoxed", onlyBoxed(boxed));

        assertEquals("both1", both(primitive));
        assertEquals("both2", both(boxed));
    }

    private String onlyPrimitive(byte b) {
        return "onlyPrimitive";
    }

    private String onlyBoxed(Byte b) {
        return "onlyBoxed";
    }

    private String both(byte b) {
        return "both1";
    }

    private String both(Byte b) {
        return "both2";
    }


}
