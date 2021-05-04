package test.java.time;

import org.junit.Assert;
import org.junit.Test;
import test.Dummy;
import test.java.time.temporal.TemporalAdjusterBuilder;
import test.java.time.temporal.TemporalAmountBuilder;
import test.java.time.temporal.TemporalFieldBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class InstantTest {
    @Test
    public void test() throws IOException, ClassNotFoundException {

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
                .supported(true)
                .build()));

        /*- This one supports nothing */
        assertFalse(instant.isSupported(new TemporalFieldBuilder()
                .supported(false)
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

        /* Adjusters can return any Temporal, but with Instant, the adjuster must always return another Instant */
        Instant adjusted = instant.with(new TemporalAdjusterBuilder()
                .temporal(Instant.now())
                .build());
        assertNotNull(adjusted);

        /* This is how to modify field values on an Instant */

        /* If the value does not change, the same instant instance is returned, but it is an implementation detail */
        assertNotNull(instant_second.with(ChronoField.MILLI_OF_SECOND, 0L));
        assertNotNull(instant_second.with(ChronoField.MICRO_OF_SECOND, 0L));
        assertNotNull(instant_second.with(ChronoField.NANO_OF_SECOND, 0L));
        assertNotNull(instant_second.with(ChronoField.INSTANT_SECONDS, 0L));

        /* If the value changes, a different instance is returned */
        assertNotNull(instant.with(ChronoField.MILLI_OF_SECOND, 0L));
        assertNotNull(instant.with(ChronoField.MICRO_OF_SECOND, 0L));
        assertNotNull(instant.with(ChronoField.NANO_OF_SECOND, 0L));
        assertNotNull(instant.with(ChronoField.INSTANT_SECONDS, 0L));

        /* Other chrono fields are not supported */
        assertThrows(UnsupportedTemporalTypeException.class,
                () -> instant.with(ChronoField.DAY_OF_MONTH, 1L)
        );

        /* Custom adjustment */
        Instant adjusted2 = instant.with(new TemporalFieldBuilder()
                .temporal(null)
                .build(), 0L);
        assertNull(adjusted2);

        /* Truncating */
        assertNotNull(instant.truncatedTo(ChronoUnit.NANOS));
        assertThrows(UnsupportedTemporalTypeException.class,
                () -> instant.truncatedTo(new TemporalUnitBuilder()
                        .duration(Duration.ofSeconds(86401))
                        .build()));
        assertThrows(UnsupportedTemporalTypeException.class,
                () -> instant.truncatedTo(new TemporalUnitBuilder()
                        .duration(Duration.ofNanos(86400L * 1_000_001L))
                        .build()));
        assertNotNull(instant.truncatedTo(ChronoUnit.SECONDS));

        /* plus */
        assertNotNull(instant.plus(new TemporalAmountBuilder().build()));

        assertNotNull(instant.plus(0L, ChronoUnit.NANOS));
        assertNotNull(instant.plus(0L, ChronoUnit.MICROS));
        assertNotNull(instant.plus(0L, ChronoUnit.MILLIS));
        assertNotNull(instant.plus(0L, ChronoUnit.SECONDS));
        assertNotNull(instant.plus(0L, ChronoUnit.MINUTES));
        assertNotNull(instant.plus(0L, ChronoUnit.HOURS));
        assertNotNull(instant.plus(0L, ChronoUnit.HALF_DAYS));
        assertNotNull(instant.plus(0L, ChronoUnit.DAYS));
        assertThrows(UnsupportedTemporalTypeException.class,
                () -> instant.plus(0L, ChronoUnit.CENTURIES));
        assertNotNull(instant.plus(0L, new TemporalUnitBuilder().build()));

        assertNotNull(instant.plusSeconds(0L));
        assertNotNull(instant.plusMillis(0L));
        assertNotNull(instant.plusNanos(0L));

        /* subtract */

        assertNotNull(instant.minus(new TemporalAmountBuilder().build()));
        assertNotNull(instant.minus(0L, new TemporalUnitBuilder().build()));
        assertNotNull(instant.minus(Long.MIN_VALUE, new TemporalUnitBuilder().build()));
        assertNotNull(instant.minusSeconds(0L));

        assertNotNull(instant.minusSeconds(0L));
        if (knowHowToDoThis()) {
            assertNotNull(instant.minusSeconds(Long.MIN_VALUE));
        }
        assertNotNull(instant.minusMillis(0L));
        if (knowHowToDoThis()) {
            assertNotNull(instant.minusMillis(Long.MIN_VALUE));
        }
        assertNotNull(instant.minusNanos(0L));
        if (knowHowToDoThis()) {
            assertNotNull(instant.minusNanos(Long.MIN_VALUE));
        }

        /* query */
        assertNotNull(instant.query(TemporalQueries.precision()));
        assertNull(instant.query(TemporalQueries.chronology()));
        assertNull(instant.query(TemporalQueries.zoneId()));
        assertNull(instant.query(TemporalQueries.zone()));
        assertNull(instant.query(TemporalQueries.offset()));
        assertNull(instant.query(TemporalQueries.localDate()));
        assertNull(instant.query(TemporalQueries.localTime()));
        assertNotNull(instant.query(new TemporalQueryBuilder<>(new Dummy()).build()));

        /**/
        assertNotNull(instant.adjustInto(instant));

        /* until */
        instant.until(instant, ChronoUnit.NANOS);
        instant.until(instant, ChronoUnit.MICROS);
        instant.until(instant, ChronoUnit.MILLIS);
        instant.until(instant, ChronoUnit.SECONDS);
        instant.until(instant, ChronoUnit.MINUTES);
        instant.until(instant, ChronoUnit.HOURS);
        instant.until(instant, ChronoUnit.HALF_DAYS);
        instant.until(instant, ChronoUnit.DAYS);
        assertThrows(UnsupportedTemporalTypeException.class,
                () -> instant.until(instant, ChronoUnit.CENTURIES));
        instant.until(instant,
                new TemporalUnitBuilder().build());

        /**/
        assertNotNull(instant.atOffset(ZoneOffset.UTC));
        assertNotNull(instant.atZone(ZoneId.systemDefault()));

        instant.toEpochMilli();
        Instant.ofEpochSecond(-1, 1).toEpochMilli();
        Instant.ofEpochSecond(-1, 0).toEpochMilli();

        Instant.ofEpochSecond(0).compareTo(Instant.ofEpochSecond(0));
        Instant.ofEpochSecond(0).compareTo(Instant.ofEpochSecond(1));

        instant.isAfter(instant);
        instant.isBefore(instant);

        Instant.ofEpochSecond(0, 0)
                .equals(Instant.ofEpochSecond(1, 0));
        Instant.ofEpochSecond(0, 1)
                .equals(Instant.ofEpochSecond(0, 1));
        Instant.ofEpochSecond(0, 1)
                .equals(Instant.ofEpochSecond(0, 2));
        Instant.ofEpochSecond(0, 1)
                .equals(new Object());

        instant.hashCode();
        instant.toString();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(instant);
        assertNotNull(new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject());

    }

    private boolean knowHowToDoThis() {
        return System.currentTimeMillis() < 0;
    }
}
