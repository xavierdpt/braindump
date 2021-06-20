package numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdditionSameTypeTest {
    @Test
    void test() {

        byte b = Byte.MAX_VALUE;
        short s = Short.MAX_VALUE;
        int i = Integer.MAX_VALUE;
        long l = Long.MAX_VALUE;
        float f = Float.MAX_VALUE;
        double d = Double.MAX_VALUE;

        Assertions.assertEquals(Integer.class, box(b + b).getClass());
        Assertions.assertEquals(Integer.class, box(s + s).getClass());
        Assertions.assertEquals(Integer.class, box(i + i).getClass());
        Assertions.assertEquals(Long.class, box(l + l).getClass());
        Assertions.assertEquals(Float.class, box(f + f).getClass());
        Assertions.assertEquals(Double.class, box(d + d).getClass());
    }

    private Number box(Number number) {
        return number;
    }


}
