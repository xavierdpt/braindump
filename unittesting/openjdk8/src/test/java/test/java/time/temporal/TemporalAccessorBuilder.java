package test.java.time.temporal;

import test.TestUtil;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class TemporalAccessorBuilder {

    Boolean supported;
    Long value;
    private List<TemporalField> collectFields;

    public TemporalAccessorBuilder supported(boolean supported) {
        this.supported = supported;
        return this;
    }

    public TemporalAccessorBuilder value(long value) {
        this.value = value;
        return this;
    }

    public TemporalAccessorBuilder collectFields(List<TemporalField> fields) {
        collectFields = fields;
        collectFields.clear();
        return this;
    }

    public TemporalAccessor build() {
        return new TemporalAccessor() {
            @Override
            public boolean isSupported(TemporalField field) {
                return Optional.ofNullable(supported)
                        .orElseThrow(TestUtil::notImplemented);
            }

            @Override
            public long getLong(TemporalField field) {
                Optional.ofNullable(collectFields)
                        .ifPresent(fields -> fields.add(field));
                return Optional.ofNullable(value)
                        .orElseThrow(TestUtil::notImplemented);
            }
        };
    }
}
