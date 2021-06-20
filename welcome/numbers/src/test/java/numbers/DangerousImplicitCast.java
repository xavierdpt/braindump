package numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DangerousImplicitCast {

    @Test
    void testLongToFloatAndBack() {

        // not safe
        Long failingFor = null;
        for (long l = Long.MAX_VALUE; l > Long.MIN_VALUE; --l) {
            if ((long) (float) l != l) {
                failingFor = l;
                break;
            }
        }
        assertNotNull(failingFor);
    }

    @Test
    void testLongToDoubleAndBack() {

        // not safe
        Long failingFor = null;
        for (long l = Long.MAX_VALUE; l > Long.MIN_VALUE; --l) {
            if ((long) (double) l != l) {
                failingFor = l;
                break;
            }
        }
        assertNotNull(failingFor);
    }

    @Test
    void testIntToFloatAndBack() {

        // not safe
        Integer failingFor = null;
        for (int i = Integer.MAX_VALUE; i > Integer.MIN_VALUE; --i) {
            if ((int) (float) i != i) {
                failingFor = i;
                break;
            }
        }
        assertNotNull(failingFor);
    }

    @Test
    void testIntToDoubleAndBack() {

        // safe, but this is accidental
        Integer failingFor = null;
        int c = Integer.MIN_VALUE;
        for (int i = Integer.MAX_VALUE; i > Integer.MIN_VALUE; --i) {
            ++c;
            if ((int) (double) i != i) {
                failingFor = i;
                break;
            }
        }
        assertNull(failingFor);
    }
}
