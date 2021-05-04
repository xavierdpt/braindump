package test.java.time.temporal;

import test.TestUtil;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class TemporalFieldBuilder {

    private boolean supported;
    private ValueRange range;
    private Long getFrom;
    private Temporal the_temporal;
    private String stringRepresentation;

    public TemporalFieldBuilder stringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
        return this;
    }

    public TemporalFieldBuilder supported(boolean supported) {
        this.supported = supported;
        return this;
    }

    public TemporalFieldBuilder range(ValueRange range) {
        this.range = range;
        return this;
    }

    public TemporalFieldBuilder getFrom(long getFrom) {
        this.getFrom = getFrom;
        return this;
    }

    public TemporalFieldBuilder temporal(Temporal temporal) {
        this.the_temporal = temporal;
        return this;
    }

    public TemporalField build() {
        return new TemporalField() {
            @Override
            public TemporalUnit getBaseUnit() {
                throw TestUtil.notImplemented();
            }

            @Override
            public TemporalUnit getRangeUnit() {
                throw TestUtil.notImplemented();
            }

            @Override
            public ValueRange range() {
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
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return supported;
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                requireNonNull(range);
                return range;
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                requireNonNull(getFrom);
                return getFrom;
            }

            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                throw TestUtil.notImplemented();
            }

            @Override
            public String toString() {
                requireNonNull(stringRepresentation);
                return stringRepresentation;
            }
        };
    }
}
