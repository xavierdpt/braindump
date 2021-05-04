package adapters.java.time;

import test.TestUtil;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

public abstract class TemporalAccessorAdapter implements TemporalAccessor {

    @Override
    public boolean isSupported(TemporalField field) {
        throw TestUtil.notImplemented();
    }

    @Override
    public long getLong(TemporalField field) {
        throw TestUtil.notImplemented();
    }

}
