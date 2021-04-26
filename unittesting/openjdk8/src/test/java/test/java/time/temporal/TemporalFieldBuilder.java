package test.java.time.temporal;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import test.TestUtil;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;

public class TemporalFieldBuilder {

    private boolean isSupportedBy;
    private ValueRange range;
    private long getFrom;

    public TemporalFieldBuilder isSupportedBy(boolean isSupportedBy) {
        this.isSupportedBy = isSupportedBy;
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
                return range;
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
                return isSupportedBy;
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                return range;
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                return getFrom;
            }

            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                throw TestUtil.notImplemented();
            }

            @Override
            public String toString() {
                throw TestUtil.notImplemented();
            }
        };
    }
}
