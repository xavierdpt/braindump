package test.java.time.temporal;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

public class TemporalAccessorBuilder {
    public TemporalAccessor build() {
        return new TemporalAccessor() {
            @Override
            public boolean isSupported(TemporalField field) {
                return true;
            }

            @Override
            public long getLong(TemporalField field) {
                return 0;
            }
        };
    }
}
