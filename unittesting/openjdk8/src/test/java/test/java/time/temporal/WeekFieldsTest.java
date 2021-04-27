package test.java.time.temporal;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.Locale;

import static org.junit.Assert.assertNotNull;

public class WeekFieldsTest {
    @Test
    public void test() throws IOException {
        assertNotNull(WeekFields.ISO);
        assertNotNull(WeekFields.SUNDAY_START);
        assertNotNull(WeekFields.WEEK_BASED_YEARS);

        WeekFields defaultWeek = WeekFields.of(Locale.getDefault());

        WeekFields.of(DayOfWeek.MONDAY, 1);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(defaultWeek);

        assertNotNull(new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())));

        defaultWeek.getFirstDayOfWeek();
        defaultWeek.getMinimalDaysInFirstWeek();
        defaultWeek.dayOfWeek();
        defaultWeek.weekOfMonth();
        defaultWeek.weekOfYear();
        defaultWeek.weekOfWeekBasedYear();
        defaultWeek.weekBasedYear();
        defaultWeek.hashCode();
        defaultWeek.toString();

    }
}
