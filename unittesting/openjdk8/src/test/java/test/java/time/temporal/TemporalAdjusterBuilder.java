package test.java.time.temporal;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class TemporalAdjusterBuilder {

    private Temporal the_temporal;

    public TemporalAdjusterBuilder temporal(Temporal temporal) {
        this.the_temporal = temporal;
        return this;
    }

    public TemporalAdjuster build() {
        return temporal -> the_temporal;
    }
}
