package numbers;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualityTest {
    @Test
    void test() {
        assertTrue(true);

        // comparison by value
        // note that the suffix is not required here (cast from int => long)
        long l1 = 5;
        long l2 = 5;
        assertTrue(l1 == l2);

        // comparison of reference with same object from constant pool
        // note that the suffix is required here (cast from int => long is implicity,
        // and cast from long => Long is implicit, but this is not transitive)
        Long l3 = 5L;
        Long l4 = 5L;
        assertTrue(l3 == l4);

        // comparison of reference using objects that are not in the pool
        // not that the 5 here is an int which is implicy cast to a long
        Long l5 = Long.MAX_VALUE - 5;
        Long l6 = Long.MAX_VALUE - 5;
        assertFalse(l5 == l6);

        // better equal that uses == or equals as needed
        assertTrue(betterEqual(l1, l2));
        assertTrue(betterEqual(l1, l3));
        assertTrue(betterEqual(l3, l1));
        assertTrue(betterEqual(l3, l4));


    }

    // efficient comparison by value for primitive
    private boolean betterEqual(long l1, long l2) {
        return l1 == l2;
    }

    // use Objects.equals for Long to safely handle null values and delegate to Long.equals
    private boolean betterEqual(Long l1, Long l2) {
        return Objects.equals(l1, l2);
    }

    // disambiguation with explicit cast
    // you can choose to cast l2 to long instead, but you'll have to handle null values
    // or you can also duplicate the method call
    // note: this is the most inefficient path: it will go like this:
    // - betterEqual(long,Long)
    // - betterEqual(Long,Long)
    // - Objects.equals(Object,Object)
    // - Long.equals(Long)
    // - Long.longValue()
    private boolean betterEqual(long l1, Long l2) {
        return betterEqual((Long) l1, l2);
    }

    // other disambiguation
    private boolean betterEqual(Long l1, long l2) {
        return betterEqual(l1, (Long) l2);
    }
}
