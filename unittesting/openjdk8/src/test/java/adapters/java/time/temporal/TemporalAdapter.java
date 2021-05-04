package adapters.java.time.temporal;

import test.TestUtil;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public class TemporalAdapter implements Temporal {
    @Override
    public boolean isSupported(TemporalUnit unit) {
        throw TestUtil.notImplemented();
    }

    @Override
    public Temporal with(TemporalField field, long newValue) {
        throw TestUtil.notImplemented();
    }

    @Override
    public Temporal plus(long amountToAdd, TemporalUnit unit) {
        throw TestUtil.notImplemented();
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        throw TestUtil.notImplemented();
    }

    @Override
    public boolean isSupported(TemporalField field) {
        throw TestUtil.notImplemented();
    }

    @Override
    public long getLong(TemporalField field) {
        throw TestUtil.notImplemented();
    }
}
