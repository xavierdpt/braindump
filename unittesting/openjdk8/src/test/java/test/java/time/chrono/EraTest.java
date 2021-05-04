package test.java.time.chrono;

import org.junit.Test;
import test.Dummy;
import test.java.time.TemporalQueryBuilder;
import test.java.time.temporal.TemporalBuilder;
import test.java.time.temporal.TemporalFieldBuilder;

import java.time.chrono.Era;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalQueries;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class EraTest {

    @Test
    public void test() {

        /* Here is an Era that returns 1 */

        Era era1 = new Era() {
            @Override
            public int getValue() {
                return 1;
            }
        };
        assertEquals(1, era1.getValue());

        /* They can also be defined by a lambda */

        /* It can also be defined as a lambda, if that makes sense */
        Era era2 = () -> 2;
        assertEquals(2, era2.getValue());

        /* The only supported ChronoField is ERA */

        /*- ERA is supported*/
        assertTrue(era1.isSupported(ChronoField.ERA));

        /*- All other chrono fields are not supported */
        assertTrue(Arrays.stream(ChronoField.values())
                .filter(((Predicate<ChronoField>) ChronoField.ERA::equals).negate())
                .noneMatch(era1::isSupported));

        /*- Custom fields can support ERA if they want */
        assertTrue(era1.isSupported(new TemporalFieldBuilder()
                .supported(true)
                .build()));
        assertFalse(era1.isSupported(new TemporalFieldBuilder()
                .supported(false)
                .build()));

        /* Range is delegated to the field itself by default */
        assertEquals(ChronoField.ERA.range(), era1.range(ChronoField.ERA));

        /* For era, get() is identical to getValue() */
        assertEquals(era1.getValue(), era1.get(ChronoField.ERA));

        /* - for other fields, it is equal to whatever they do with it */
        assertEquals(5, era1.get(new TemporalFieldBuilder()
                .range(ValueRange.of(0L, 10L))
                .getFrom(5L)
                .build()));

        /* getLong additional checks the other Chrono fields */
        assertEquals(era1.getValue(), era1.getLong(ChronoField.ERA));
        for (ChronoField chronoField : ChronoField.values()) {
            if (ChronoField.ERA.equals(chronoField)) {
                continue;
            }
            assertThrows(UnsupportedTemporalTypeException.class,
                    () -> era1.getLong(chronoField));
        }
        /*- custom fields can do whatever they want */
        assertEquals(7L, era1.getLong(new TemporalFieldBuilder()
                .getFrom(7L)
                .build()));

        /* precision() query gives back ERAS */
        assertEquals(ChronoUnit.ERAS, era1.query(TemporalQueries.precision()));

        /*- other queries do whatever they want */
        assertEquals(Dummy.DUMMY,
                era1.query(new TemporalQueryBuilder<Dummy>(Dummy.DUMMY).build()));

        /* adjustInto delegate the adjustement to the temporal */
        Temporal temporal2 = new TemporalBuilder().build();
        Temporal temporal1 = new TemporalBuilder()
                .with(temporal2)
                .build();
        assertEquals(temporal2, era1.adjustInto(temporal1));

        /* getDisplayName is identical for all eras (unless overriden) and uses DateTimeFormatterBuilder */
        assertEquals("Anno Domini", era1.getDisplayName(TextStyle.FULL, Locale.getDefault()));
    }
}
