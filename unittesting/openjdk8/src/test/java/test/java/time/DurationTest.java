package test.java.time;

import org.junit.Test;
import test.TestUtil;
import test.java.time.temporal.TemporalAmountBuilder;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class DurationTest {
    @Test
    public void test() throws IOException, ClassNotFoundException {

        /* The duration class represents a duration with seconds and nanoseconds */

        /* Here is the zero duration */

        Duration zero = Duration.ZERO;
        assertEquals(0L, zero.getSeconds());
        assertEquals(0L, zero.getNano());

        /* There are factories for common units up to days */

        Duration oneNano = Duration.ofNanos(1);
        assertEquals(0L, oneNano.getSeconds());
        assertEquals(1L, oneNano.getNano());

        Duration oneMillisecond = Duration.ofMillis(1);
        assertEquals(0L, oneMillisecond.getSeconds());
        assertEquals(1_000_000L, oneMillisecond.getNano());

        Duration oneSecond = Duration.ofSeconds(1);
        assertEquals(1L, oneSecond.getSeconds());
        assertEquals(0L, oneSecond.getNano());

        Duration oneMinute = Duration.ofMinutes(1);
        assertEquals(60L, oneMinute.getSeconds());
        assertEquals(0L, oneMinute.getNano());

        Duration oneHour = Duration.ofHours(1);
        assertEquals(3600L, oneHour.getSeconds());
        assertEquals(0L, oneHour.getNano());

        Duration oneDay = Duration.ofDays(1L);
        assertEquals(86400L, oneDay.getSeconds());
        assertEquals(0L, oneDay.getNano());

        /* And a factory for specifying secons and nanos */

        Duration oneSecondAndOneNano = Duration.ofSeconds(1, 1);
        assertEquals(1L, oneSecondAndOneNano.getSeconds());
        assertEquals(1L, oneSecondAndOneNano.getNano());

        /* There is generic constructor for other units */

        Duration oneMicrosecond = Duration.of(1, ChronoUnit.MICROS);
        assertEquals(0L, oneMicrosecond.getSeconds());
        assertEquals(1000L, oneMicrosecond.getNano());

        /* There are converters for most units, but they ignore the remainder */

        assertEquals(1_000_000_001L, oneSecondAndOneNano.toNanos());
        // nothing for micro seconds
        assertEquals(1_000L, oneSecondAndOneNano.toMillis());
        // use getSeconds() for the seconds
        assertEquals(0L, oneSecondAndOneNano.toMinutes());
        assertEquals(0L, oneSecondAndOneNano.toHours());
        assertEquals(0L, oneSecondAndOneNano.toDays());

        /* There are operators to add and subtract common units */

        Duration oneSecondPlusOneNano = oneSecond.plusNanos(1L);
        assertEquals(1L, oneSecondPlusOneNano.getSeconds());
        assertEquals(1L, oneSecondPlusOneNano.getNano());

        Duration oneSecondPlusOneMilli = oneSecond.plusMillis(1L);
        assertEquals(1L, oneSecondPlusOneMilli.getSeconds());
        assertEquals(1_000_000L, oneSecondPlusOneMilli.getNano());

        Duration oneSecondPlusOneSecond = oneSecond.plusSeconds(1L);
        assertEquals(2L, oneSecondPlusOneSecond.getSeconds());
        assertEquals(0, oneSecondPlusOneSecond.getNano());

        Duration oneSecondPlusOneMinute = oneSecond.plusMinutes(1L);
        assertEquals(61L, oneSecondPlusOneMinute.getSeconds());
        assertEquals(0, oneSecondPlusOneMinute.getNano());

        Duration oneSecondPlusOneHour = oneSecond.plusHours(1L);
        assertEquals(3601L, oneSecondPlusOneHour.getSeconds());
        assertEquals(0, oneSecondPlusOneHour.getNano());

        Duration oneSecondPlusOneDay = oneSecond.plusDays(1L);
        assertEquals(86401L, oneSecondPlusOneDay.getSeconds());
        assertEquals(0, oneSecondPlusOneDay.getNano());

        /* it is also possible to add another duration, or another unit */

        Duration onePlusOneSeconds = oneSecond.plus(oneSecond);
        assertEquals(2L, onePlusOneSeconds.getSeconds());
        assertEquals(0, onePlusOneSeconds.getNano());

        Duration oneSecondOneMicro = oneSecond.plus(1L, ChronoUnit.MICROS);
        assertEquals(1L, oneSecondOneMicro.getSeconds());
        assertEquals(1000L, oneSecondOneMicro.getNano());

        /* Subtraction is similar, here we compare the result what we got with addition */
        assertEquals(oneSecond, oneSecondPlusOneNano.minusNanos(1L));
        assertEquals(oneSecond, oneSecondPlusOneMilli.minusMillis(1L));
        assertEquals(oneSecond, oneSecondPlusOneSecond.minusSeconds(1L));
        assertEquals(oneSecond, oneSecondPlusOneMinute.minusMinutes(1L));
        assertEquals(oneSecond, oneSecondPlusOneHour.minusHours(1L));
        assertEquals(oneSecond, oneSecondPlusOneDay.minusDays(1L));
        assertEquals(oneSecond, onePlusOneSeconds.minus(oneSecond));
        assertEquals(oneSecond, oneSecondOneMicro.minus(1L, ChronoUnit.MICROS));

        /* A duration can be negated, but the negative sign is on the seconds only and the nanos are always a positive displacement added to it */
        Duration negated = oneSecondAndOneNano.negated();
        assertEquals(-2L, negated.getSeconds());
        assertEquals(999_999_999L, negated.getNano());

        /* And there is an absolute value operator */
        assertEquals(oneSecondAndOneNano, oneSecondAndOneNano.abs());
        assertEquals(oneSecondAndOneNano, negated.abs());

        /* One is negative, the other is positive*/
        assertTrue(negated.isNegative());
        assertFalse(oneSecondAndOneNano.isNegative());

        /* Comparison to zero */
        assertTrue(zero.isZero());
        assertFalse(oneSecond.isZero());

        /* To set the seconds and nanos, use with(), which returns a copy */
        Duration oneSecondSetNano = oneSecond.withNanos(1);
        assertEquals(1L, oneSecondSetNano.getSeconds());
        assertEquals(1L, oneSecondSetNano.getNano());

        Duration oneNanoSetSecond = oneNano.withSeconds(1L);
        assertEquals(1L, oneNanoSetSecond.getSeconds());
        assertEquals(1L, oneNanoSetSecond.getNano());

        /* There is a generic get() that knows only about seconds and nanos */
        assertEquals(1L, oneSecond.get(ChronoUnit.SECONDS));
        assertEquals(0L, oneSecond.get(ChronoUnit.NANOS));
        assertThrows(UnsupportedTemporalTypeException.class,
                () -> oneSecond.get(ChronoUnit.MILLIS));

        /* The allowed units are in getUnits() */
        List<TemporalUnit> units = oneSecond.getUnits();
        assertEquals(2, units.size());
        assertThat(units, hasItems(ChronoUnit.NANOS, ChronoUnit.SECONDS));

        /* Durations can also by multiplied and divided by numbers */
        Duration twoSeconds = oneSecond.multipliedBy(2L);
        assertEquals(2L, twoSeconds.getSeconds());
        assertEquals(0L, twoSeconds.getNano());

        assertEquals(oneSecond, twoSeconds.dividedBy(2L));

        /* The difference between two temporals is a duration */
        Instant instant1 = Instant.ofEpochSecond(1L);
        Instant instant2 = Instant.ofEpochSecond(2L);
        Duration between = Duration.between(instant1, instant2);
        assertEquals(1L, between.getSeconds());
        assertEquals(0L, between.getNano());

        /* Temporal and duration makes another temporal */
        /* The documentation says the return type is of the same type as the argument */
        /* They could have used generics for this */
        Instant result = (Instant) oneSecond.addTo(instant1);
        Instant same = (Instant) oneSecond.subtractFrom(result);
        assertEquals(instant1, same);

        /* Durations can be compared */
        assertEquals(-1, oneSecond.compareTo(twoSeconds));
        assertEquals(1, twoSeconds.compareTo(oneSecond));
        assertEquals(0, oneSecond.compareTo(oneSecond));

        /* equals() and hashCode() are defined */
        assertTrue(oneSecond.equals(oneSecond));
        assertFalse(oneSecond.equals(twoSeconds));

        assertEquals(1, oneSecond.hashCode());

        /* toString() returns a particular format which can be parsed */
        assertEquals("PT1.000000001S", oneSecondAndOneNano.toString());
        assertEquals(oneSecondAndOneNano, Duration.parse("PT1.000000001S"));

        /* Duration can be instantiated from any temporal amounts */

        TemporalAmount temporalAmount = new TemporalAmountBuilder(2L, ChronoUnit.MINUTES)
                .build();
        Duration fromTemporal = Duration.from(temporalAmount);
        assertEquals(120L, fromTemporal.getSeconds());
        assertEquals(0L, fromTemporal.getNano());

        /* Finally, it supports serialization and deserialization */

        TestUtil.serDeser(oneDay);

    }
}
