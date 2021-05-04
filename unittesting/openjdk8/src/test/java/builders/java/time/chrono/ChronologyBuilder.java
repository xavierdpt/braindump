package builders.java.time.chrono;

import test.TestUtil;

import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.Era;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Map;

public class ChronologyBuilder {
    public Chronology build() {
        return new Chronology() {
            @Override
            public String getId() {
                throw TestUtil.notImplemented();
            }

            @Override
            public String getCalendarType() {
                throw TestUtil.notImplemented();
            }

            @Override
            public ChronoLocalDate date(int prolepticYear, int month, int dayOfMonth) {
                throw TestUtil.notImplemented();
            }

            @Override
            public ChronoLocalDate dateYearDay(int prolepticYear, int dayOfYear) {
                throw TestUtil.notImplemented();
            }

            @Override
            public ChronoLocalDate dateEpochDay(long epochDay) {
                throw TestUtil.notImplemented();
            }

            @Override
            public ChronoLocalDate date(TemporalAccessor temporal) {
                throw TestUtil.notImplemented();
            }

            @Override
            public boolean isLeapYear(long prolepticYear) {
                throw TestUtil.notImplemented();
            }

            @Override
            public int prolepticYear(Era era, int yearOfEra) {
                throw TestUtil.notImplemented();
            }

            @Override
            public Era eraOf(int eraValue) {
                throw TestUtil.notImplemented();
            }

            @Override
            public List<Era> eras() {
                throw TestUtil.notImplemented();
            }

            @Override
            public ValueRange range(ChronoField field) {
                throw TestUtil.notImplemented();
            }

            @Override
            public ChronoLocalDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
                throw TestUtil.notImplemented();
            }

            @Override
            public int compareTo(Chronology other) {
                throw TestUtil.notImplemented();
            }
        };
    }
}
