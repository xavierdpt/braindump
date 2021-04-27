package test.java.time.temporal;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class ChronoUnitTest {
    @Test
    public void test() {

        Instant now = Instant.now();

        for (ChronoUnit unit : ChronoUnit.values()) {
            System.out.println(unit.toString());
            System.out.println("- Duration: " + unit.getDuration().toString());
            System.out.println("- Is duration estimated: " + unit.isDurationEstimated());
            System.out.println("- Is date based: " + unit.isDateBased());
            System.out.println("- Is time based: " + unit.isTimeBased());
            System.out.println("- Is time based: " + unit.isTimeBased());
        }

        Temporal temporal = new TemporalBuilder().build();
        ChronoUnit.SECONDS.isSupportedBy(temporal);
        ChronoUnit.SECONDS.addTo(temporal, 0L);
        ChronoUnit.SECONDS.between(temporal, temporal);

    }
}
