package test.java.time;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class TemporalUnitBuilder {

    private Boolean isSupportedBy;

    public TemporalUnitBuilder isSupportedBy(boolean isSupportedBy) {
        this.isSupportedBy = isSupportedBy;
        return this;
    }

    public TemporalUnit build() {
        return new TemporalUnit() {

            @Override
            public boolean isSupportedBy(Temporal temporal) {
                if (isSupportedBy == null) {
                    return TemporalUnit.super.isSupportedBy(temporal);
                }
                return isSupportedBy;
            }

            @Override
            public Duration getDuration() {
                return null;
            }

            @Override
            public boolean isDurationEstimated() {
                return false;
            }

            @Override
            public boolean isDateBased() {
                return false;
            }

            @Override
            public boolean isTimeBased() {
                return false;
            }

            @Override
            public <R extends Temporal> R addTo(R temporal, long amount) {
                return null;
            }

            @Override
            public long between(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
                return 0;
            }

            @Override
            public String toString() {
                return null;
            }
        };
    }
}
