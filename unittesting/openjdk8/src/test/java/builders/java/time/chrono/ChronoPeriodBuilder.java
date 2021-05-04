package builders.java.time.chrono;

import test.TestUtil;

import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChronoPeriodBuilder {
    private Chronology chronology;
    private List<TemporalUnit> units;
    private Map<ChronoUnit, Long> values;

    public ChronoPeriodBuilder chronology(Chronology chronology) {
        this.chronology = chronology;
        return this;
    }

    public ChronoPeriodBuilder units(List<TemporalUnit> units) {
        this.units = units;
        return this;
    }

    public ChronoPeriodBuilder values(Map<ChronoUnit, Long> values) {
        this.values = values;
        return this;
    }

    public ChronoPeriod build() {
        return new ChronoPeriod() {
            @Override
            public long get(TemporalUnit unit) {
                if (values == null) {
                    throw TestUtil.notImplemented();
                }
                if (unit instanceof ChronoUnit) {
                    if (values.containsKey(unit)) {
                        return values.get(unit);
                    }
                }
                throw new IllegalArgumentException(Optional.ofNullable(unit).map(TemporalUnit::toString).orElse("null"));
            }

            @Override
            public List<TemporalUnit> getUnits() {
                return Optional.ofNullable(units)
                        .orElseThrow(TestUtil::notImplemented);
            }

            @Override
            public Chronology getChronology() {
                return Optional.ofNullable(chronology)
                        .orElseThrow(TestUtil::notImplemented);
            }

            @Override
            public ChronoPeriod plus(TemporalAmount amountToAdd) {
                throw TestUtil.notImplemented();
            }

            @Override
            public ChronoPeriod minus(TemporalAmount amountToSubtract) {
                throw TestUtil.notImplemented();
            }

            @Override
            public ChronoPeriod multipliedBy(int scalar) {
                throw TestUtil.notImplemented();
            }

            @Override
            public ChronoPeriod normalized() {
                throw TestUtil.notImplemented();
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
