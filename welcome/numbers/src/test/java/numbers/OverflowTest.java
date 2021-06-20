package numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OverflowTest {
    @Test
    void test() {

        byte b = Byte.MAX_VALUE;
        ++b;
        Assertions.assertEquals(Byte.MIN_VALUE, b);

        short s = Short.MAX_VALUE;
        ++s;
        Assertions.assertEquals(Short.MIN_VALUE, s);

        int i = Integer.MAX_VALUE;
        ++i;
        Assertions.assertEquals(Integer.MIN_VALUE, i);

        long l = Long.MAX_VALUE;
        ++l;
        Assertions.assertEquals(Long.MIN_VALUE, l);

        float f = Float.MAX_VALUE;
        ++f;
        Assertions.assertEquals(Float.MAX_VALUE, f);
        f *= 10F;
        Assertions.assertEquals(Float.POSITIVE_INFINITY, f);

        double d = Double.MAX_VALUE;
        ++d;
        Assertions.assertEquals(Double.MAX_VALUE, d);
        d *= 10D;
        Assertions.assertEquals(Double.POSITIVE_INFINITY, d);

    }
}
