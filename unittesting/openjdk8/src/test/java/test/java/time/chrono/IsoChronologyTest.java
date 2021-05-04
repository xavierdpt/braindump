package test.java.time.chrono;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;
import test.SeeOther;
import test.TestUtil;
import test.java.time.LocalDateTest;
import test.java.time.temporal.TemporalAccessorBuilder;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.chrono.IsoEra;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IsoChronologyTest {


    @Test
    public void test1() {

        /*-
         * This is the ISO chronology
         *  - it's ID is ISO
         *  - it's calendar type is iso8601
         */

        IsoChronology chronology = IsoChronology.INSTANCE;
        assertEquals("ISO", chronology.getId());
        assertEquals("iso8601", chronology.getCalendarType());
    }


    @Test
    @SeeOther(LocalDateTest.class)
    public void test2() {

        LocalDate localDate;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * Year 0 is 1970, unit is days
         */

        localDate = chronology.dateEpochDay(0L);
        assertEquals(1970, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = chronology.dateEpochDay(1L);
        assertEquals(1970, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(2, localDate.getDayOfMonth());
    }

    @Test
    @SeeOther(LocalDateTest.class)
    public void test3() {

        LocalDate localDate;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * with date(year, month, dayOfMonth)
         * year 0 is really year 0
         */

        localDate = chronology.date(0, 1, 1);
        assertEquals(0, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());
    }

    @Test
    @SeeOther(LocalDateTest.class)
    public void test4() {

        LocalDate localDate;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * with dateYear(year, dayOfMonth)
         * year 0 is really year 0, and the day is the number of the day in the year
         */

        localDate = chronology.dateYearDay(0, 1);
        assertEquals(0, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());
    }

    @Test
    public void test5() {

        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * CE goes from 0 up to infinity
         * but BCE goes from 1 to -infinity
         */

        assertEquals(0L,
                chronology.prolepticYear(IsoEra.CE, 0));
        assertEquals(1L,
                chronology.prolepticYear(IsoEra.CE, 1));
        assertEquals(2L,
                chronology.prolepticYear(IsoEra.CE, 2));

        assertEquals(1L,
                chronology.prolepticYear(IsoEra.BCE, 0));
        assertEquals(0L,
                chronology.prolepticYear(IsoEra.BCE, 1));
        assertEquals(-1L,
                chronology.prolepticYear(IsoEra.BCE, 2));

        /*-
         * therefore
         * - 0 CE = 1 BCE
         * - 1 CE = 0 BCE
         */

        assertEquals(
                chronology.prolepticYear(IsoEra.CE, 0),
                chronology.prolepticYear(IsoEra.BCE, 1));
        assertEquals(
                chronology.prolepticYear(IsoEra.CE, 1),
                chronology.prolepticYear(IsoEra.BCE, 0));

    }

    @Test
    public void test6() {

        LocalDate localDate;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * with the era, the year is converted to the proleptic year
         */

        localDate = chronology.dateYearDay(IsoEra.CE, 0, 1);
        assertEquals(0, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = chronology.dateYearDay(IsoEra.CE, 2, 1);
        assertEquals(2, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = chronology.dateYearDay(IsoEra.BCE, 0, 1);
        assertEquals(1, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = chronology.dateYearDay(IsoEra.BCE, 2, 1);
        assertEquals(-1, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());
    }

    @Test
    public void test7() {

        LocalDate localDate;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * date() is similar to dateYearDay(), but the day is the number of day in the year
         */

        localDate = chronology.date(IsoEra.CE, 0, 1, 1);
        assertEquals(0, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = chronology.date(IsoEra.CE, 2, 1, 1);
        assertEquals(2, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = chronology.date(IsoEra.BCE, 0, 1, 1);
        assertEquals(1, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

        localDate = chronology.date(IsoEra.BCE, 2, 1, 1);
        assertEquals(-1, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());
    }

    @Test
    @SeeOther(LocalDateTest.class)
    public void test8() {
        LocalDate localDate;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * Local dates can be built from any temporal accessor, but temporal accessor are three to deny the query
         * - Here we use a custom temporal accessor that can converts an instant to a localdate,
         *   assuming instant 0 starts at 1970 CE
         * - The instant class denies the TemporalQueries.localDate
         */

        class InstantToLocalDate implements TemporalAccessor {

            private final Instant instant;

            public InstantToLocalDate(Instant instant) {
                this.instant = instant;
            }

            @Override
            public boolean isSupported(TemporalField field) {
                return instant.isSupported(field);
            }

            @Override
            public long getLong(TemporalField field) {
                return instant.getLong(field);
            }

            @Override
            public <R> R query(TemporalQuery<R> query) {
                if (TemporalQueries.localDate() == query) {
                    return (R) IsoChronology.INSTANCE.dateEpochDay(instant.getEpochSecond());
                } else {
                    return instant.query(query);
                }
            }
        }

        localDate = chronology.date(new InstantToLocalDate(Instant.ofEpochSecond(0L)));
        assertEquals(1970, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());

    }

    @Test
    @SeeOther(LocalDateTest.class)
    public void test9() {

        LocalDate localDate;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * The current date can be obtain from the default, a specific zone, or a specific clock
         * There is not much to test here
         */

        localDate = chronology.dateNow();
        assertNotNull(localDate);

        localDate = chronology.dateNow(ZoneId.systemDefault());
        assertNotNull(localDate);

        localDate = chronology.dateNow(Clock.systemDefaultZone());
        assertNotNull(localDate);

    }

    @Test
    //@SeeOther(AbstractChronologyTest.class)
    public void test10() {

        LocalDate localDate;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * This method is a bit magic
         */

        Map<TemporalField, Long> fields = new HashMap<>();
        fields.put(ChronoField.EPOCH_DAY, 3L);
        localDate = chronology.resolveDate(fields, ResolverStyle.STRICT);
        assertEquals(1970, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(4, localDate.getDayOfMonth());

    }

    @Test
    //@SeeOther(LocaleDateTimeTest.class)
    public void test11() {

        LocalDateTime localDateTime;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * The only function that produces an instance LocalDateTime converts temporal accessors to LocalDateTime
         * using LocalDateTime itself
         *
         * This example uses a custom accessor build which says yes to everything, always returns 1L,
         * and collect the fields it has been interrogated on.
         * This produces a local date time from an Epoch Day and a Nano of Day
         */

        List<TemporalField> collectedFields = new ArrayList<>();
        TemporalAccessor collectingTemporalAccessor = buildCollectingTemporalAccessor(collectedFields);
        localDateTime = chronology.localDateTime(collectingTemporalAccessor);
        printFields(collectedFields);

        assertEquals(1970L, localDateTime.getYear());
        assertEquals(1L, localDateTime.getMonthValue());
        assertEquals(2L, localDateTime.getDayOfMonth());
        assertEquals(0L, localDateTime.getHour());
        assertEquals(0L, localDateTime.getMinute());
        assertEquals(0L, localDateTime.getSecond());
        assertEquals(1L, localDateTime.getNano());

        assertThat(collectedFields,
                IsCollectionContaining.hasItems(ChronoField.EPOCH_DAY, ChronoField.NANO_OF_DAY));
    }

    @Test
    //@SeeOther(ZonedDateTimeTest.class)
    public void test12() {

        ZonedDateTime zonedDateTime;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * Building a zoned date time from a temporal accessor produces a datetime with a made-up zone offset
         * and a made up zone id (because our collectiong temporal does not support the zoneid query).
         */

        List<TemporalField> collectedFields = new ArrayList<>();
        zonedDateTime = chronology.zonedDateTime(buildCollectingTemporalAccessor(collectedFields));
        printFields(collectedFields);

        assertEquals(1970L, zonedDateTime.getYear());
        assertEquals(1L, zonedDateTime.getMonthValue());
        assertEquals(1L, zonedDateTime.getDayOfMonth());
        assertEquals(0L, zonedDateTime.getHour());
        assertEquals(0L, zonedDateTime.getMinute());
        assertEquals(2L, zonedDateTime.getSecond());
        assertEquals(1L, zonedDateTime.getNano());
        assertEquals(1L, zonedDateTime.getNano());
        assertEquals("+00:00:01", zonedDateTime.getOffset().getId());
        assertEquals(1L, zonedDateTime.getOffset().getTotalSeconds());
        assertEquals("+00:00:01", zonedDateTime.getZone().getId());

        assertThat(collectedFields,
                IsCollectionContaining.hasItems(ChronoField.OFFSET_SECONDS, ChronoField.INSTANT_SECONDS, ChronoField.NANO_OF_SECOND));
    }

    @Test
    public void test13() {

        ZonedDateTime zonedDateTime;
        IsoChronology chronology = IsoChronology.INSTANCE;

        /*-
         * To convert an instant to a zonedDateTime, specify  the instant and a zone id
         */

        zonedDateTime = chronology.zonedDateTime(Instant.ofEpochMilli(0L), ZoneId.systemDefault());

        // UNIX time starts at 1970
        assertEquals(1970L, zonedDateTime.getYear());

        // First month and first day start at 1
        assertEquals(1L, zonedDateTime.getMonthValue());
        assertEquals(1L, zonedDateTime.getDayOfMonth());

        // Hour starts at 0 but is offset by the offset in the zone id
        assertEquals(1L, zonedDateTime.getHour());

        // Time parts start at 0
        assertEquals(0L, zonedDateTime.getMinute());
        assertEquals(0L, zonedDateTime.getSecond());
        assertEquals(0L, zonedDateTime.getNano());
        assertEquals(0L, zonedDateTime.getNano());

        // Offset is 1 hour and comes from the zone id
        assertEquals("+01:00", zonedDateTime.getOffset().getId());
        assertEquals(3600, zonedDateTime.getOffset().getTotalSeconds());

        // The system time zone is Europe/Paris on my system
        assertEquals("Europe/Paris", zonedDateTime.getZone().getId());
    }

    @Test
    public void test14() {

        IsoChronology isoc = IsoChronology.INSTANCE;

        /*-
         * The ISO Chronology has a public method to know if a year is a leap year
         */

        assertTrue(isoc.isLeapYear(0L));
        assertTrue(isoc.isLeapYear(2000L));

    }

    @Test
    public void test15() {

        IsoChronology isoc = IsoChronology.INSTANCE;

        /*-
         * range actually delegates to the field itself and is independent of the chronology
         */

        ValueRange valueRange = isoc.range(ChronoField.DAY_OF_YEAR);
        assertFalse(valueRange.isFixed());
        assertEquals(1L, valueRange.getMinimum());
        assertEquals(1L, valueRange.getLargestMinimum());
        assertEquals(365L, valueRange.getSmallestMaximum());
        assertEquals(366L, valueRange.getMaximum());
    }

    @Test
    public void test16() {
        IsoChronology isoc = IsoChronology.INSTANCE;

        /*-
         * - eraOf() finds the IsoEra of that value (0L or 1L)
         */

        assertEquals(IsoEra.CE,
                isoc.eraOf(IsoEra.CE.getValue()));

        /*-
         * eras() returns all the eras in IsoEra
         */

        assertThat(isoc.eras(),
                IsCollectionContaining.hasItems(IsoEra.CE, IsoEra.BCE));

    }

    @Test
    public void test17() {
        IsoChronology isoc = IsoChronology.INSTANCE;

        /*-
         * A period has years, months and days,
         * And a LocalDate has years, months, and days
         * But a period is the number of days, months and years between two local dates
         */
        Period period = isoc.period(0, 1, 1);
        assertEquals(0, period.getYears());
        assertEquals(1, period.getMonths());
        assertEquals(1, period.getDays());
    }

    @Test
    public void test18() {

        LocalDate date1;
        LocalDate date2;
        Period period;

        IsoChronology isoc = IsoChronology.INSTANCE;

        // Between January 1st, 0 CE and January 1st, 1 CE

        date1 = isoc.dateYearDay(2000, 1);
        date2 = isoc.dateYearDay(2001, 1);
        period = Period.between(date1, date2);

        assertTrue(isoc.isLeapYear(2000L));
        assertFalse(isoc.isLeapYear(2001L));
        assertEquals("2000-01-01", date1.toString());
        assertEquals("2001-01-01", date2.toString());
        assertEquals("P1Y", period.toString());

        // Between January 1st, 1 CE and January 1st, 2 CE

        date1 = isoc.dateYearDay(2001, 1);
        date2 = isoc.dateYearDay(2002, 1);
        period = Period.between(date1, date2);

        assertFalse(isoc.isLeapYear(2001L));
        assertFalse(isoc.isLeapYear(2002L));
        assertEquals("2001-01-01", date1.toString());
        assertEquals("2002-01-01", date2.toString());
        assertEquals("P1Y", period.toString());

    }

    @Test
    public void testx() {
        System.out.println(Period.of(1, 0, 1));
    }

    @Test
    public void xtest() throws IOException, ClassNotFoundException {

        /* IsoChronology */

        /* This is the ISO chronology */
        /* - it's ID is ISO and it's calendar type is iso8601 */

        IsoChronology isoc = IsoChronology.INSTANCE;


        Period period = isoc.period(0, 1, 1);

        TestUtil.serDeser(isoc);


        /* Chronology */

        Chronology chronology = Chronology.from(new TemporalAccessorBuilder()
                .supported(true)
                .value(1L)
                .build());

        chronology = Chronology.ofLocale(Locale.getDefault());

        chronology = Chronology.of(isoc.getId());

        Set<Chronology> availableChronologies = Chronology.getAvailableChronologies();


    }

    private TemporalAccessor buildCollectingTemporalAccessor(List<TemporalField> collectedFields) {
        return new TemporalAccessorBuilder()
                .supported(true)
                .value(1L)
                .collectFields(collectedFields)
                .build();
    }

    private static void printFields(List<TemporalField> fields) {
        System.out.println("Got fields: " + fields.stream().map(TemporalField::toString).collect(Collectors.joining(", ")));
    }
}
