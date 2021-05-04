package test.java.time;

import builders.java.time.chrono.ChronoPeriodBuilder;
import builders.java.time.chrono.ChronologyBuilder;
import org.junit.Test;
import test.java.time.temporal.TemporalAmountBuilder;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PeriodTest {

    @Test
    public void test1() {
        {
            Period z = Period.ZERO;
            assertEquals(0, z.getYears());
            assertEquals(0, z.getMonths());
            assertEquals(0, z.getDays());
        }
        {
            Period period = Period.of(1, 1, 1);
            assertEquals(1, period.getYears());
            assertEquals(1, period.getMonths());
            assertEquals(1, period.getDays());
        }
        {
            Period period = Period.ofDays(1);
            assertEquals(0, period.getYears());
            assertEquals(0, period.getMonths());
            assertEquals(1, period.getDays());
        }
        {
            Period period = Period.ofWeeks(1);
            assertEquals(0, period.getYears());
            assertEquals(0, period.getMonths());
            assertEquals(7, period.getDays());
        }
        {
            Period period = Period.ofMonths(1);
            assertEquals(0, period.getYears());
            assertEquals(1, period.getMonths());
            assertEquals(0, period.getDays());
        }
        {
            Period period = Period.ofYears(1);
            assertEquals(1, period.getYears());
            assertEquals(0, period.getMonths());
            assertEquals(0, period.getDays());
        }
    }

    @Test
    public void test2() {
        /*- Period:from() */
        {
            /*-
             * Period => Period
             */
            Period base = Period.ZERO;
            Period period = Period.from(base);
            assertEquals(0, period.getYears());
            assertEquals(0, period.getMonths());
            assertEquals(0, period.getDays());
        }
        {
            /*-
             * ChronoPeriod => Chronology must be ISO, fields can only be YEARS, MONTHS, and/or DAYS
             */
            ChronoPeriod base = new ChronoPeriodBuilder()
                    .chronology(IsoChronology.INSTANCE)
                    .units(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS))
                    .values(new HashMap<ChronoUnit, Long>() {{
                        put(ChronoUnit.YEARS, 1L);
                        put(ChronoUnit.MONTHS, 1L);
                        put(ChronoUnit.DAYS, 1L);
                    }})
                    .build();
            Period period = Period.from(base);
            assertEquals(1, period.getYears());
            assertEquals(1, period.getMonths());
            assertEquals(1, period.getDays());
        }
        {
            /*-
             * TemporalAmount => Fields can only be YEARS, MONTHS, and/or DAYS
             */
            TemporalAmount base = new TemporalAmountBuilder()
                    .units(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS))
                    .values(new HashMap<TemporalUnit, Long>() {{
                        put(ChronoUnit.YEARS, 1L);
                        put(ChronoUnit.MONTHS, 1L);
                        put(ChronoUnit.DAYS, 1L);
                    }})
                    .build();
            Period period = Period.from(base);
            assertEquals(1, period.getYears());
            assertEquals(1, period.getMonths());
            assertEquals(1, period.getDays());
        }
    }

    @Test
    public void test3() {
        Period period = Period.parse("P1Y1M1W1D");
        assertEquals(1, period.getYears());
        assertEquals(1, period.getMonths());
        assertEquals(8, period.getDays());
    }

    @Test
    public void test4() {
        Period period = Period.between(
                LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 2)
        );
        assertEquals(0, period.getYears());
        assertEquals(0, period.getMonths());
        assertEquals(1, period.getDays());
    }

    @Test
    public void test5() {
        Period period = Period.of(1, 2, 3);
        assertEquals(1, period.get(ChronoUnit.YEARS));
        assertEquals(2, period.get(ChronoUnit.MONTHS));
        assertEquals(3, period.get(ChronoUnit.DAYS));
    }

    @Test
    public void xtest() {

        IsoChronology chronology = IsoChronology.INSTANCE;

        Period oneMonthOneDay = Period.of(0, 1, 1);

        Temporal january28th2001 = chronology.date(2001, 1, 28);
        Temporal march1st2001 = oneMonthOneDay.addTo(january28th2001);
        Temporal january31th2001 = oneMonthOneDay.subtractFrom(march1st2001);
        assertEquals("2001-01-28", january28th2001.toString());
        assertEquals("2001-03-01", march1st2001.toString());
        assertEquals("2001-01-31", january31th2001.toString());

        Temporal january28th2004 = chronology.date(2004, 1, 28);
        Temporal february29th2004 = oneMonthOneDay.addTo(january28th2004);
        Temporal january28th2004Again = oneMonthOneDay.subtractFrom(february29th2004);
        assertEquals("2004-01-28", january28th2004.toString());
        assertEquals("2004-02-29", february29th2004.toString());
        assertEquals("2004-01-28", january28th2004Again.toString());
    }
}
