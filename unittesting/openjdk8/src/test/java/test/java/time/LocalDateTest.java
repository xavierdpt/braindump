package test.java.time;

import adapters.java.time.TemporalAccessorAdapter;
import adapters.java.time.temporal.TemporalAdapter;
import org.junit.Test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.IsoChronology;
import java.time.chrono.IsoEra;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.ValueRange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LocalDateTest {


    @Test
    public void testx() {
        LocalDate localDate;

        localDate = LocalDate.MIN;
        assertNotNull(localDate);
        assertEquals(-999_999_999, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = LocalDate.MAX;
        assertNotNull(localDate);
        assertEquals(999_999_999, localDate.getYear());
        assertEquals(12, localDate.getMonthValue());
        assertEquals(31, localDate.getDayOfMonth());

        localDate = LocalDate.now();
        assertTrue(localDate.isAfter(LocalDate.MIN));

        localDate = LocalDate.now(ZoneId.systemDefault());
        assertTrue(localDate.isAfter(LocalDate.MIN));

        localDate = LocalDate.now(Clock.systemDefaultZone());
        assertTrue(localDate.isAfter(LocalDate.MIN));

        localDate = LocalDate.of(1, 2, 3);
        assertEquals(1, localDate.getYear());
        assertEquals(2, localDate.getMonthValue());
        assertEquals(3, localDate.getDayOfMonth());

        localDate = LocalDate.of(1, Month.FEBRUARY, 3);
        assertEquals(1, localDate.getYear());
        assertEquals(2, localDate.getMonthValue());
        assertEquals(3, localDate.getDayOfMonth());

        localDate = LocalDate.ofYearDay(1, 2);
        assertEquals(1, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(2, localDate.getDayOfMonth());

        localDate = LocalDate.ofEpochDay(0);
        assertEquals(1970, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        LocalDate now = LocalDate.now();
        class MyTemporalAccessor1 extends TemporalAccessorAdapter {
            @Override
            public <R> R query(TemporalQuery<R> query) {
                assertEquals(TemporalQueries.localDate(), query);
                return (R) now;
            }
        }
        localDate = LocalDate.from(new MyTemporalAccessor1());
        assertEquals(now, localDate);

        localDate = LocalDate.parse("1970-01-01");
        assertEquals(1970, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        assertEquals(1970, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        assertTrue(localDate.isSupported(ChronoField.DAY_OF_MONTH));
        assertTrue(localDate.isSupported(ChronoUnit.DAYS));

        localDate = LocalDate.of(400, 2, 1);
        ValueRange range = localDate.range(ChronoField.DAY_OF_MONTH);
        assertEquals(ValueRange.of(1L, 29L), range);

        assertEquals(1, localDate.get(ChronoField.DAY_OF_MONTH));
        assertEquals(1L, localDate.getLong(ChronoField.DAY_OF_MONTH));
        assertEquals(IsoChronology.INSTANCE, localDate.getChronology());
        assertEquals(IsoEra.CE, localDate.getEra());
        assertEquals(Month.FEBRUARY, localDate.getMonth());
        assertEquals(32, localDate.getDayOfYear());
        assertEquals(DayOfWeek.TUESDAY, localDate.getDayOfWeek());
        assertTrue(localDate.isLeapYear());
        assertEquals(29, localDate.lengthOfMonth());
        assertEquals(366, localDate.lengthOfYear());

        assertEquals(
                LocalDate.of(400, 1, 1),
                localDate.with(TemporalAdjusters.firstDayOfYear())
        );

        assertEquals(
                LocalDate.of(401, 2, 1),
                localDate.with(ChronoField.YEAR, 401)
        );

        assertEquals(
                LocalDate.of(401, 2, 28),
                LocalDate.of(400, 2, 29)
                        .with(ChronoField.YEAR, 401)
        );

        assertEquals(
                LocalDate.of(401, 2, 28),
                LocalDate.of(400, 2, 29)
                        .withYear(401)
        );

        assertEquals(
                LocalDate.of(400, 3, 28),
                LocalDate.of(400, 2, 28)
                        .withMonth(3)
        );

        assertEquals(
                LocalDate.of(400, 2, 1),
                LocalDate.of(400, 2, 28)
                        .withDayOfMonth(1)
        );
        assertEquals(
                LocalDate.of(400, 1, 1),
                LocalDate.of(400, 2, 28)
                        .withDayOfYear(1)
        );


        assertEquals(
                LocalDate.of(400, 2, 1),
                LocalDate.of(400, 1, 1)
                        .plus(Period.ofMonths(1))
        );

        assertEquals(
                LocalDate.of(400, 3, 1),
                LocalDate.of(400, 2, 1)
                        .plus(Period.ofMonths(1))
        );

        assertEquals(
                LocalDate.of(400, 2, 29),
                LocalDate.of(400, 1, 31)
                        .plus(1L, ChronoUnit.MONTHS)
        );

        assertEquals(
                LocalDate.of(401, 2, 28),
                LocalDate.of(400, 2, 29)
                        .plusYears(1L)
        );

        assertEquals(
                LocalDate.of(401, 2, 28),
                LocalDate.of(400, 2, 29)
                        .plusMonths(12L)
        );

        assertEquals(
                LocalDate.of(401, 2, 27),
                LocalDate.of(400, 2, 29)
                        .plusWeeks(52)
        );


        assertEquals(
                LocalDate.of(401, 2, 28),
                LocalDate.of(400, 2, 29)
                        .plusDays(365)
        );

        assertEquals(
                LocalDate.of(400, 2, 28),
                LocalDate.of(400, 2, 29)
                        .minus(Period.ofDays(1))
        );

        assertEquals(
                LocalDate.of(400, 2, 28),
                LocalDate.of(400, 2, 29)
                        .minus(1L, ChronoUnit.DAYS)
        );

        assertEquals(
                LocalDate.of(399, 2, 28),
                LocalDate.of(400, 2, 29)
                        .minusYears(1L)
        );

        assertEquals(
                LocalDate.of(399, 2, 28),
                LocalDate.of(400, 2, 29)
                        .minusMonths(12L)
        );

        assertEquals(
                LocalDate.of(400, 2, 22),
                LocalDate.of(400, 2, 29)
                        .minusWeeks(1L)
        );

        assertEquals(
                LocalDate.of(400, 2, 22),
                LocalDate.of(400, 2, 29)
                        .minusDays(7L)
        );

        assertEquals(
                localDate,
                localDate.query(TemporalQueries.localDate()));

        class MyTemporal extends TemporalAdapter {
            @Override
            public Temporal with(TemporalField field, long newValue) {
                assertEquals(ChronoField.EPOCH_DAY, field);
                return LocalDate.ofEpochDay(newValue);
            }
        }

        assertEquals(
                LocalDate.of(400, 2, 29),
                LocalDate.of(400, 2, 29)
                        .adjustInto(new MyTemporal()));

        assertEquals(
                5L,
                LocalDate.of(400, 2, 29)
                        .until(LocalDate.of(405, 3, 31), ChronoUnit.YEARS));

        assertEquals(
                Period.of(5, 1, 2),
                LocalDate.of(400, 2, 29)
                        .until(LocalDate.of(405, 3, 31)));

        assertEquals(
                "0400-02-29",
                LocalDate.of(400, 2, 29)
                        .format(DateTimeFormatter.ISO_LOCAL_DATE));

        assertEquals(
                LocalDateTime.of(400, 2, 29, 1, 1),
                LocalDate.of(400, 2, 29)
                        .atTime(LocalTime.of(1, 1)));

        assertEquals(
                LocalDateTime.of(400, 2, 29, 1, 1),
                LocalDate.of(400, 2, 29)
                        .atTime(1, 1));

        assertEquals(
                LocalDateTime.of(400, 2, 29, 1, 1),
                LocalDate.of(400, 2, 29)
                        .atTime(1, 1));

        assertEquals(
                LocalDateTime.of(400, 2, 29, 1, 1, 1),
                LocalDate.of(400, 2, 29)
                        .atTime(1, 1, 1));

        assertEquals(
                LocalDateTime.of(400, 2, 29, 1, 1, 1, 1),
                LocalDate.of(400, 2, 29)
                        .atTime(1, 1, 1, 1));

        assertEquals(
                OffsetDateTime.of(400, 2, 29, 1, 1, 1, 1, ZoneOffset.UTC),
                LocalDate.of(400, 2, 29)
                        .atTime(OffsetTime.of(1, 1, 1, 1, ZoneOffset.UTC)));

        assertEquals(
                LocalDateTime.of(400, 2, 29, 0, 0, 0, 0),
                LocalDate.of(400, 2, 29)
                        .atStartOfDay());

        assertEquals(
                ZonedDateTime.of(400, 2, 29, 0, 0, 0, 0, ZoneId.of("UTC")),
                LocalDate.of(400, 2, 29)
                        .atStartOfDay(ZoneId.of("UTC")));

        assertEquals(
                58L,
                LocalDate.of(1970, 2, 28)
                        .toEpochDay());

        assertTrue(LocalDate.of(1970, 2, 28)
                .compareTo(LocalDate.now()) < 0);

        assertFalse(LocalDate.of(1970, 2, 28).isAfter(LocalDate.now()));
        assertTrue(LocalDate.of(1970, 2, 28).isBefore(LocalDate.now()));

        assertFalse(LocalDate.MIN.isEqual(LocalDate.MAX));
        assertFalse(LocalDate.MIN.equals(LocalDate.MAX));

        assertEquals("1970-02-28",
                LocalDate.of(1970, 2, 28).toString());


    }
}
