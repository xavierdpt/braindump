package test.java.time.temporal;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.List;

public class TemporalAmountBuilder {
    private long value;
    private ChronoUnit unit;

    @Deprecated
    public TemporalAmountBuilder() {
    }

    public TemporalAmountBuilder(long value, ChronoUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public TemporalAmount build() {
        return new TemporalAmount() {
            @Override
            public long get(TemporalUnit unit) {
                return value;
            }

            @Override
            public List<TemporalUnit> getUnits() {
                return Collections.singletonList(unit);
            }

            @Override
            public Temporal addTo(Temporal temporal) {
                return temporal.plus(value, unit);
            }

            @Override
            public Temporal subtractFrom(Temporal temporal) {
                return temporal.minus(value, unit);
            }
        };
    }
}
