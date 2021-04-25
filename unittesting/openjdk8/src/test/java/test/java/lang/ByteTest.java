package test.java.lang;

import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ByteTest {

    @Test
    public void test() {
        Calendar instance = Calendar.getInstance();
        assertNotNull(instance);
        assertNotNull(Calendar.getInstance(TimeZone.getDefault()));
        assertNotNull(Calendar.getInstance(Locale.getDefault()));
        assertNotNull(Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()));
        assertTrue(Calendar.getAvailableLocales().length > 0);

        assertNotNull(instance.getTime());
        instance.setTime(instance.getTime());
        instance.getTimeInMillis();
        instance.set(Calendar.MONTH, instance.get(Calendar.MONTH));
        instance.set(2021, 6, 5);
        instance.set(2021, 6, 5, 10, 10);
        instance.set(2021, 6, 5, 10, 10, 10);
        instance.clear();
        instance.clear(Calendar.MONTH);
        instance.isSet(Calendar.MONTH);
        instance.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        instance.getDisplayNames(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        instance.getAvailableCalendarTypes();
        instance.getCalendarType();
        instance.equals(instance.clone());
        instance.hashCode();
        instance.before(instance);
        instance.after(instance);
        instance.compareTo(instance);
        instance.roll(Calendar.MONTH, 1);
        instance.setTimeZone(TimeZone.getDefault());
        instance.getTimeZone();
        instance.setFirstDayOfWeek(1);
        instance.setMinimalDaysInFirstWeek(1);
        instance.isWeekDateSupported();
        instance.getActualMinimum(Calendar.MONTH);
        instance.getActualMaximum(Calendar.MONTH);
        instance.toString();
        instance.toInstant();


    }

}
