package test.java.time;

import test.TestUtil;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class TemporalUnitBuilder {

    private Boolean isSupportedBy;
    private Duration duration;

    public TemporalUnitBuilder isSupportedBy(boolean isSupportedBy) {
        this.isSupportedBy = isSupportedBy;
        return this;
    }

    public TemporalUnitBuilder duration(Duration duration) {
        this.duration = duration;
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
                return duration;
            }

            @Override
            public boolean isDurationEstimated() {
                throw TestUtil.notImplemented();
            }

            @Override
            public boolean isDateBased() {
                throw TestUtil.notImplemented();
            }

            @Override
            public boolean isTimeBased() {
                throw TestUtil.notImplemented();
            }

            @Override
            public <R extends Temporal> R addTo(R temporal, long amount) {
                return temporal;
            }

            @Override
            public long between(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
                return 0L;
            }

            @Override
            public String toString() {
                throw TestUtil.notImplemented();
            }
        };
    }
}
