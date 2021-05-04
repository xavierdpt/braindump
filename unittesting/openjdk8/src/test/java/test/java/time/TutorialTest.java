package test.java.time;

import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.AbstractChronology;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.Era;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.HijrahEra;
import java.time.chrono.IsoChronology;
import java.time.chrono.IsoEra;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;
import java.time.chrono.MinguoEra;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.chrono.ThaiBuddhistDate;
import java.time.chrono.ThaiBuddhistEra;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.JulianFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TutorialTest {

    @Test
    public void tutorialTest() {

        /* An instant is defined as a number of seconds and milliseconds since the Unix epoch */
        Instant instant = Instant.ofEpochSecond(0L);
        assertEquals(0L, instant.getEpochSecond());
        assertEquals(0L, instant.getNano());
        assertEquals("1970-01-01T00:00:00Z", instant.toString());

        /* A local date is defined as a year with a month and a day in the proleptic gregorian calendar (ISO) */
        LocalDate localDate = LocalDate.of(2000, 1, 1);
        assertEquals(2000, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());
        assertEquals(IsoChronology.INSTANCE, localDate.getChronology());
        assertEquals(IsoEra.CE, localDate.getEra());
        assertEquals("2000-01-01", localDate.toString());

        /* Local date also support months constants */
        assertEquals(Month.JANUARY,
                LocalDate.of(2000, Month.JANUARY, 1)
                        .getMonth());

        /* A local time is defined as hours, minutes, seconds and nanoseconds */
        LocalTime localTime = LocalTime.of(1, 30, 45, 200);
        assertEquals(1, localTime.getHour());
        assertEquals(30, localTime.getMinute());
        assertEquals(45, localTime.getSecond());
        assertEquals(200, localTime.getNano());

        /* A local date time is simply a local date and a local time together */
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        assertEquals(localDate.getYear(), localDateTime.getYear());
        assertEquals(localDate.getMonthValue(), localDateTime.getMonthValue());
        assertEquals(localDate.getDayOfMonth(), localDateTime.getDayOfMonth());
        assertEquals(localTime.getHour(), localDateTime.getHour());
        assertEquals(localTime.getMinute(), localDateTime.getMinute());
        assertEquals(localTime.getSecond(), localDateTime.getSecond());
        assertEquals(localTime.getNano(), localDateTime.getNano());

        /* the era and chronology are not exposed by LocalDateTime, but are effectively present in the underlying localdate */
        assertEquals(localDate, localDateTime.toLocalDate());
        assertEquals(localTime, localDateTime.toLocalTime());

        /* they can also be accessed by other means */
        assertEquals(IsoEra.CE, IsoEra.of(localDateTime.get(ChronoField.ERA)));
        assertEquals(IsoChronology.INSTANCE, localDateTime.query(TemporalQueries.chronology()));


        /* A zone offset is defined as a number of seconds with no transitions */
        ZoneOffset zoneOffset = ZoneOffset.ofHours(2);
        assertEquals(7200, zoneOffset.getTotalSeconds());
        assertTrue(zoneOffset.getRules().isFixedOffset());
        assertEquals(0L, zoneOffset.getRules().getTransitions().size());
        assertEquals(0L, zoneOffset.getRules().getTransitionRules().size());

        /* An offset time is a local time with a zone offset */
        OffsetTime offsetTime = OffsetTime.of(localTime, zoneOffset);
        assertEquals(offsetTime.toLocalTime(), localTime);
        assertEquals(offsetTime.getOffset(), zoneOffset);

        /* An offset date time is a local date time with a zone offset */
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, zoneOffset);
        assertEquals(localDateTime, offsetDateTime.toLocalDateTime());
        assertEquals(zoneOffset, offsetDateTime.getOffset());

        /* A ZoneId is can be defined as UTC, GMT or UT with a zone offset */
        ZoneId zoneId = ZoneId.ofOffset("UTC", zoneOffset);
        assertEquals("UTC+02:00", zoneId.getId());

        /* A zoned date time is a date time with a ZoneId */
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        assertEquals(localDateTime, zonedDateTime.toLocalDateTime());
        assertEquals(zoneId, zonedDateTime.getZone());

        /* A duration is a number of seconds and nanoseconds */
        Duration duration = Duration.ofSeconds(600, 200);
        assertEquals(600, duration.getSeconds());
        assertEquals(200, duration.getNano());

        /* A period is a number of years, months and days */
        Period period = Period.ofWeeks(1);
        assertEquals(0, period.getYears());
        assertEquals(0, period.getMonths());
        assertEquals(7, period.getDays());

        Map<String, TemporalAccessor> temporals = new HashMap<String, TemporalAccessor>() {{
            put("instant", instant);
            put("localDate", localDate);
            put("localTime", localTime);
            put("localDateTime", localDateTime);
            put("zoneOffset", zoneOffset);
            put("offsetTime", offsetTime);
            put("offsetDateTime", offsetDateTime);
            put("zonedDateTime", zonedDateTime);
        }};

        Map<String, TemporalQuery<? extends Object>> temporalQueries =
                new HashMap<String, TemporalQuery<? extends Object>>() {{
                    put("zoneId", TemporalQueries.zoneId());
                    put("chronology", TemporalQueries.chronology());
                    put("precision", TemporalQueries.precision());
                    put("zone", TemporalQueries.zone());
                    put("offset", TemporalQueries.offset());
                    put("localDate", TemporalQueries.localDate());
                    put("localTime", TemporalQueries.localTime());
                }};


        for (Map.Entry<String, TemporalAccessor> e : temporals.entrySet()) {
            TemporalAccessor ta = e.getValue();
            System.out.println(e.getKey());
            for (ChronoField value : ChronoField.values()) {
                if (ta.isSupported(value)) {
                    System.out.println("- " + value.name());
                }
            }
            if (ta instanceof Temporal) {
                for (ChronoUnit value : ChronoUnit.values()) {
                    if (((Temporal) ta).isSupported(value)) {
                        System.out.println("- " + value.name());
                    }
                }
            }
            for (Map.Entry<String, TemporalQuery<?>> tqe : temporalQueries.entrySet()) {
                try {
                    ta.query(tqe.getValue());
                    System.out.println("- " + tqe.getKey());
                } catch (Exception ignored) {
                }
            }
        }


        //  TemporalAdjusters.ofDateAdjuster(null);
        TemporalAdjusters.firstDayOfMonth();
        TemporalAdjusters.lastDayOfMonth();
        TemporalAdjusters.firstDayOfNextMonth();
        TemporalAdjusters.firstDayOfYear();
        TemporalAdjusters.lastDayOfYear();
        TemporalAdjusters.firstDayOfNextYear();
        TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
        TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY);
        TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY);
        TemporalAdjusters.next(DayOfWeek.MONDAY);
        TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY);
        TemporalAdjusters.previous(DayOfWeek.MONDAY);
        TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);


        ChronoField[] chronoFields = new ChronoField[]{
                ChronoField.NANO_OF_SECOND,
                ChronoField.NANO_OF_DAY,
                ChronoField.MICRO_OF_SECOND,
                ChronoField.MICRO_OF_DAY,
                ChronoField.MILLI_OF_SECOND,
                ChronoField.MILLI_OF_DAY,
                ChronoField.SECOND_OF_MINUTE,
                ChronoField.SECOND_OF_DAY,
                ChronoField.MINUTE_OF_HOUR,
                ChronoField.MINUTE_OF_DAY,
                ChronoField.HOUR_OF_AMPM,
                ChronoField.CLOCK_HOUR_OF_AMPM,
                ChronoField.HOUR_OF_DAY,
                ChronoField.CLOCK_HOUR_OF_DAY,
                ChronoField.AMPM_OF_DAY,
                ChronoField.DAY_OF_WEEK,
                ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH,
                ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR,
                ChronoField.DAY_OF_MONTH,
                ChronoField.DAY_OF_YEAR,
                ChronoField.EPOCH_DAY,
                ChronoField.ALIGNED_WEEK_OF_MONTH,
                ChronoField.ALIGNED_WEEK_OF_YEAR,
                ChronoField.MONTH_OF_YEAR,
                ChronoField.PROLEPTIC_MONTH,
                ChronoField.YEAR_OF_ERA,
                ChronoField.YEAR,
                ChronoField.ERA,
                ChronoField.INSTANT_SECONDS,
                ChronoField.OFFSET_SECONDS
        };

        ChronoUnit[] chronoUnits = new ChronoUnit[]{
                ChronoUnit.NANOS,
                ChronoUnit.MICROS,
                ChronoUnit.MILLIS,
                ChronoUnit.SECONDS,
                ChronoUnit.MINUTES,
                ChronoUnit.HOURS,
                ChronoUnit.HALF_DAYS,
                ChronoUnit.DAYS,
                ChronoUnit.WEEKS,
                ChronoUnit.MONTHS,
                ChronoUnit.YEARS,
                ChronoUnit.DECADES,
                ChronoUnit.CENTURIES,
                ChronoUnit.MILLENNIA,
                ChronoUnit.ERAS,
                ChronoUnit.FOREVER
        };

        Class<?>[] classes = new Class[]{
                Clock.class,
                DayOfWeek.class,
                Month.class,
                MonthDay.class,
                Year.class,
                YearMonth.class,
                AbstractChronology.class,
                ChronoLocalDate.class,
                ChronoLocalDateTime.class,
                Chronology.class,
                ChronoPeriod.class,
                ChronoZonedDateTime.class,
                Era.class,
                HijrahChronology.class,
                HijrahDate.class,
                HijrahEra.class,
                IsoChronology.class,
                IsoEra.class,
                JapaneseChronology.class,
                JapaneseDate.class,
                JapaneseEra.class,
                MinguoChronology.class,
                MinguoDate.class,
                MinguoEra.class,
                ThaiBuddhistChronology.class,
                ThaiBuddhistDate.class,
                ThaiBuddhistEra.class,
                DateTimeFormatter.class,
                DateTimeFormatterBuilder.class,
                DecimalStyle.class,
                FormatStyle.class,
                ResolverStyle.class,
                SignStyle.class,
                TextStyle.class,
                IsoFields.class,
                JulianFields.class,
                Temporal.class,
                TemporalAccessor.class,
                TemporalField.class,
                TemporalUnit.class,
                ValueRange.class,
                WeekFields.class
        };
    }
}
