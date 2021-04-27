package test.java.time.temporal;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class ChronoFieldTest {
    @Test
    public void test() {

        for (ChronoField x : ChronoField.values()) {
            System.out.println(x.toString());
            System.out.println("- Display name: " + x.getDisplayName(Locale.getDefault()));
            System.out.println("- Base unit: " + x.getBaseUnit().toString());
            System.out.println("- Range unit: " + x.getRangeUnit().toString());
            System.out.println("- Min: " + x.range().getMinimum());
            System.out.println("- Largest Min: " + x.range().getLargestMinimum());
            System.out.println("- Max: " + x.range().getMaximum());
            System.out.println("- Smallest Max: " + x.range().getSmallestMaximum());
            System.out.println("- Dated based: " + x.isDateBased());
            System.out.println("- Time based: " + x.isTimeBased());
        }

        TemporalAccessor temporalAccessor = new TemporalAccessorBuilder().build();

        ChronoField.NANO_OF_SECOND.checkValidValue(0L);
        ChronoField.NANO_OF_SECOND.checkValidIntValue(0L);
        ChronoField.NANO_OF_SECOND.isSupportedBy(temporalAccessor);
        ChronoField.NANO_OF_SECOND.rangeRefinedBy(temporalAccessor);
        ChronoField.NANO_OF_SECOND.getFrom(temporalAccessor);
        ChronoField.NANO_OF_SECOND.adjustInto(Instant.now(), 0L);


    }
}
