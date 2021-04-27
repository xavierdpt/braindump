package test.java.time;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

public class TemporalQueryBuilder<R> {

    private R result;

    public TemporalQueryBuilder(R dummy) {

        result = dummy;
    }

    public TemporalQuery<R> build() {
        return temporal -> result;
    }
}
