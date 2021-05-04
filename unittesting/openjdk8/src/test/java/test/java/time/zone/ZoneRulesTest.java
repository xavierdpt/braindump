package test.java.time;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransition;
import java.time.zone.ZoneOffsetTransitionRule;
import java.time.zone.ZoneRules;
import java.util.ArrayList;
import java.util.List;

public class ZoneRulesTest {
    @Test
    public void test1() {


        Instant anInstant = Instant.EPOCH;
        LocalDateTime aLocalDateTime = LocalDateTime.MIN;
        ZoneOffset aZoneOffset = ZoneOffset.MIN;

        ZoneRules zoneRules = ZoneRules.of(
                ZoneOffset.ofTotalSeconds(1),
                ZoneOffset.ofTotalSeconds(2),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );


        ZoneRules zoneRules1 = ZoneRules.of(aZoneOffset);


        ZoneOffset zoneOffset = zoneRules.getOffset(anInstant);

        ZoneOffset zoneOffset1 = zoneRules.getOffset(aLocalDateTime);

        List<ZoneOffset> zoneOffsets = zoneRules.getValidOffsets(aLocalDateTime);

        ZoneOffsetTransition zoneOffsetTransition = zoneRules.getTransition(aLocalDateTime);

        ZoneOffset zoneOffset2 = zoneRules.getStandardOffset(anInstant);

        Duration duration = zoneRules.getDaylightSavings(anInstant);

        boolean b = zoneRules.isDaylightSavings(anInstant);

        boolean b1 = zoneRules.isValidOffset(aLocalDateTime, aZoneOffset);

        ZoneOffsetTransition zoneOffsetTransition1 = zoneRules.nextTransition(anInstant);

        ZoneOffsetTransition zoneOffsetTransition2 = zoneRules.previousTransition(anInstant);

        List<ZoneOffsetTransition> zoneOffsetTransitions = zoneRules.getTransitions();

        List<ZoneOffsetTransitionRule> zoneOffsetTransitionRules = zoneRules.getTransitionRules();


    }
}
