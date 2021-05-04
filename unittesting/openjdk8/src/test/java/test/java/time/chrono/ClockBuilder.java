package test.java.time.chrono;

import test.TestUtil;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

public class ClockBuilder {

    private ZoneId zoneId;
    private Clock clock;
    private Instant instant;

    public ClockBuilder zoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public ClockBuilder clock(Clock clock) {
        this.clock = clock;
        return this;
    }

    public ClockBuilder instant(Instant instant) {
        this.instant = instant;
        return this;
    }

    public Clock build() {
        return new Clock() {
            @Override
            public ZoneId getZone() {
                return Optional.ofNullable(zoneId)
                        .orElseThrow(TestUtil::notImplemented);
            }

            @Override
            public Clock withZone(ZoneId zone) {
                return Optional.ofNullable(clock)
                        .orElseThrow(TestUtil::notImplemented);
            }

            @Override
            public Instant instant() {
                return Optional.ofNullable(instant)
                        .orElseThrow(TestUtil::notImplemented);
            }
        };
    }
}
