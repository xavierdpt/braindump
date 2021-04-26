package test.java.time;

import org.junit.Assert;
import org.junit.Test;
import test.java.time.temporal.TemporalFieldBuilder;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class InstantTest {
    @Test
    public void test() {

        /* Creates an instant for the current time */
        Instant instant = Instant.now();
        assertNotNull(instant);

        /* Creates an instant for the current time with a specific clock */
        Instant instant_clock = Instant.now(Clock.systemUTC());
        assertNotNull(instant_clock);

        /* Creates an instant from a number of seconds */
        Instant instant_second = Instant.ofEpochSecond(0);
        assertNotNull(instant_second);

        /* Subsumed */
        Instant instant_second_nano = Instant.ofEpochSecond(0, 0);
        assertNotNull(instant_second_nano);

        /* Subsumed */
        Instant instant_milli = Instant.ofEpochMilli(0);
        assertNotNull(instant_milli);

        /* Create an instant from an instant */
        Instant from_instant = Instant.from(instant);
        assertEquals(instant, from_instant);

        /* Create an instant from a temporal accessor */
        Instant from_temporal_accessor = Instant.from(new TemporalAccessor() {
            @Override
            public boolean isSupported(TemporalField field) {
                return true;
            }

            @Override
            public long getLong(TemporalField field) {
                return 0;
            }
        });
        assertNotNull(from_temporal_accessor);

        /* Create an instant from a temporal accessor that does not support nanoseconds */
        assertThrows(DateTimeException.class,
                () -> Instant.from(new TemporalAccessor() {
                    @Override
                    public boolean isSupported(TemporalField field) {
                        return false;
                    }

                    @Override
                    public long getLong(TemporalField field) {
                        return 0;
                    }
                }));

        /* Creates an instant from a String */
        Instant instant_parsed = Instant.parse(instant.toString());
        assertNotNull(instant_parsed);

        /* Creates an instant with too many seconds */
        assertThrows(DateTimeException.class,
                () -> Instant.ofEpochSecond(Instant.MAX.getEpochSecond() + 1));

        /* Creates an instant with not enough seconds */
        assertThrows(DateTimeException.class,
                () -> Instant.ofEpochSecond(Instant.MIN.getEpochSecond() - 1));

        /* Instant 0 is the epoch */
        Assert.assertEquals(Instant.EPOCH, Instant.ofEpochSecond(0));

        /* Instants supports the following chrono fields*/
        assertTrue(instant.isSupported(ChronoField.INSTANT_SECONDS));
        assertTrue(instant.isSupported(ChronoField.NANO_OF_SECOND));
        assertTrue(instant.isSupported(ChronoField.MICRO_OF_SECOND));
        assertTrue(instant.isSupported(ChronoField.MILLI_OF_SECOND));
        assertFalse(instant.isSupported(ChronoField.DAY_OF_MONTH));

        /* Other fields can support instants as they wish */

        /*- This one supports everything */
        assertTrue(instant.isSupported(new TemporalFieldBuilder()
                .isSupportedBy(true)
                .build()));

        /*- This one supports nothing */
        assertFalse(instant.isSupported(new TemporalFieldBuilder()
                .isSupportedBy(false)
                .build()));

        /* Null fields do not break */
        assertFalse(instant.isSupported((TemporalField) null));

        /* Instants support time based chrono units and days*/
        assertTrue(instant.isSupported(ChronoUnit.SECONDS));
        assertTrue(instant.isSupported(ChronoUnit.DAYS));
        assertFalse(instant.isSupported(ChronoUnit.MONTHS));
        assertFalse(instant.isSupported((TemporalUnit) null));
        assertTrue(instant.isSupported(new TemporalUnitBuilder()
                .isSupportedBy(true)
                .build()));
        assertFalse(instant.isSupported(new TemporalUnitBuilder()
                .isSupportedBy(false)
                .build()));

        /* Each field has a range */
        ValueRange second_range = instant.range(ChronoField.INSTANT_SECONDS);
        assertNotNull(second_range);

        /* get instant fields as integers */

        /*- works for Chrono subsecond fields */
        assertTrue(instant.get(ChronoField.NANO_OF_SECOND) > Integer.MIN_VALUE);
        assertTrue(instant.get(ChronoField.MICRO_OF_SECOND) > Integer.MIN_VALUE);
        assertTrue(instant.get(ChronoField.MILLI_OF_SECOND) > Integer.MIN_VALUE);

        /*- Chrono second always throw */
        assertThrows(DateTimeException.class,
                () -> instant.get(ChronoField.INSTANT_SECONDS));

        /*- Other chrono fields always throw */
        assertThrows(UnsupportedTemporalTypeException.class,
                () -> instant.get(ChronoField.DAY_OF_MONTH));

        /*- Custom temporal field are supported */
        assertTrue(instant.get(new TemporalFieldBuilder()
                .range(ValueRange.of(0, 1))
                .build()) > Integer.MIN_VALUE);

        /* get instant fields as longs */

        /*- works for Chrono subsecond fields */
        assertTrue(instant.getLong(ChronoField.NANO_OF_SECOND) > Long.MIN_VALUE);
        assertTrue(instant.getLong(ChronoField.MICRO_OF_SECOND) > Long.MIN_VALUE);
        assertTrue(instant.getLong(ChronoField.MILLI_OF_SECOND) > Long.MIN_VALUE);
        assertTrue(instant.getLong(ChronoField.INSTANT_SECONDS) > Long.MIN_VALUE);

        /*- Other chrono fields always throw */
        assertThrows(UnsupportedTemporalTypeException.class,
                () -> instant.getLong(ChronoField.DAY_OF_MONTH));

        /*- Custom temporal field are supported */
        assertTrue(instant.getLong(new TemporalFieldBuilder()
                .getFrom(0L)
                .build()) > Long.MIN_VALUE);

        /* Get epoch seconds */
        assertTrue(instant.getEpochSecond() > Long.MIN_VALUE);

        /* Get nano seconds */
        assertTrue(instant.getNano() > Integer.MIN_VALUE);

        /* Stopped at with(TemporalAdjuster)*/

    }
}
