package test.java.time.zone;


import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransition;
import java.time.zone.ZoneOffsetTransitionRule;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;

public class ZoneOffsetTransitionRuleTest {
    @Test
    public void test() {
        Month month = Month.JANUARY;
        int dayOfMonthIndicator = 1;
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        LocalTime localTime = LocalTime.MIN;
        boolean timeEndOfDay = false;
        TimeDefinition timeDefinition = TimeDefinition.WALL;
        ZoneOffset standardOffset = ZoneOffset.MIN;
        ZoneOffset offsetBefore = ZoneOffset.MAX;
        ZoneOffset offsetAfter = ZoneOffset.UTC;

        ZoneOffsetTransitionRule z = ZoneOffsetTransitionRule.of(
                month,
                dayOfMonthIndicator,
                dayOfWeek,
                localTime,
                timeEndOfDay,
                timeDefinition,
                standardOffset,
                offsetBefore,
                offsetAfter
        );

        Month month1 = z.getMonth();

        int dayOfMonthIndicator1 = z.getDayOfMonthIndicator();

        DayOfWeek dayOfWeek1 = z.getDayOfWeek();

        LocalTime localTime1 = z.getLocalTime();

        boolean midnightEndOfDay = z.isMidnightEndOfDay();

        TimeDefinition timeDefinition1 = z.getTimeDefinition();

        ZoneOffset standardOffset1 = z.getStandardOffset();

        ZoneOffset offsetBefore1 = z.getOffsetBefore();

        ZoneOffset offsetAfter1 = z.getOffsetAfter();

        int year = 2000;
        ZoneOffsetTransition transition = z.createTransition(year);

        LocalDateTime dateTime = TimeDefinition.WALL.createDateTime(LocalDateTime.MIN, ZoneOffset.MIN, ZoneOffset.MAX);
    }
}
