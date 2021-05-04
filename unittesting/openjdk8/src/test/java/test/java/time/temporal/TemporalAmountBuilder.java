package test.java.time.temporal;

import test.TestUtil;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TemporalAmountBuilder {
    private long value;
    private ChronoUnit unit;
    private List<TemporalUnit> units;
    private HashMap<TemporalUnit, Long> values;

    public TemporalAmountBuilder() {
    }

    @Deprecated
    public TemporalAmountBuilder(long value, ChronoUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public TemporalAmountBuilder units(List<TemporalUnit> units) {
        this.units = units;
        return this;
    }

    public TemporalAmountBuilder values(HashMap<TemporalUnit, Long> values) {
        this.values = values;
        return this;
    }

    public TemporalAmount build() {
        return new TemporalAmount() {
            @Override
            public long get(TemporalUnit unit) {
                if (values == null) {
                    throw TestUtil.notImplemented();
                }
                return values.get(unit);
            }

            @Override
            public List<TemporalUnit> getUnits() {
                return Optional.ofNullable(units)
                        .orElseThrow(TestUtil::notImplemented);
            }

            @Override
            public Temporal addTo(Temporal temporal) {
                throw TestUtil.notImplemented();
            }

            @Override
            public Temporal subtractFrom(Temporal temporal) {
                throw TestUtil.notImplemented();
            }
        };
    }
}
