package numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlusPlusTest {
    @Test
    void test() {

        byte b = Byte.MAX_VALUE;
        short s = Short.MAX_VALUE;
        int i = Integer.MAX_VALUE;
        long l = Long.MAX_VALUE;
        float f = Float.MAX_VALUE;
        double d = Double.MAX_VALUE;

        Assertions.assertEquals(Byte.class, box(++b).getClass());
        Assertions.assertEquals(Short.class, box(++s).getClass());
        Assertions.assertEquals(Integer.class, box(++i).getClass());
        Assertions.assertEquals(Long.class, box(++l).getClass());
        Assertions.assertEquals(Float.class, box(++f).getClass());
        Assertions.assertEquals(Double.class, box(++d).getClass());
    }

    private Number box(Number number) {
        return number;
    }


}
