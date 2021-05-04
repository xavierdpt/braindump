package test.java.time.chrono;

import org.junit.Test;

import java.time.chrono.IsoEra;

import static org.junit.Assert.assertEquals;

public class IsoEraTest {
    @Test
    public void test() {

        /* IsoEra has only two values, which are equal to the enum ordinal */
        assertEquals(0, IsoEra.BCE.getValue());
        assertEquals(1, IsoEra.CE.getValue());

        /* The of() method takes the enum ordinal as input */
        for (IsoEra era : IsoEra.values()) {
            assertEquals(era, IsoEra.of(era.getValue()));
        }
    }
}
