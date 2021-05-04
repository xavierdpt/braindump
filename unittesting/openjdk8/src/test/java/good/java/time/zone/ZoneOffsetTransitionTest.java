package good.java.time.zone;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ZoneOffsetTransitionTest {

    @Test
    public void testGap() {

        /*- Let's create date time for January 1st 2020, 09:30:45 */
        LocalDateTime baseDate = LocalDateTime.of(
                LocalDate.of(2020, Month.JANUARY, 2),
                LocalTime.of(9, 30, 45)
        );
        assertEquals("2020-01-02T09:30:45", baseDate.toString());

        /*- The offset transition goes from + 30 minutes to + 45 minutes */
        ZoneOffset offsetBefore = ZoneOffset.ofHoursMinutesSeconds(0, 30, 0);
        assertEquals("+00:30", offsetBefore.toString());

        ZoneOffset offsetAfter = ZoneOffset.ofHoursMinutesSeconds(0, 45, 0);
        assertEquals("+00:45", offsetAfter.toString());

        /*- Here is the zone offset transition */
        ZoneOffsetTransition z = ZoneOffsetTransition.of(baseDate, offsetBefore, offsetAfter);
        assertEquals("Transition[Gap at 2020-01-02T09:30:45+00:30 to +00:45]",
                z.toString());

        /*- This transition is a gap */
        assertTrue(z.isGap());
        assertFalse(z.isOverlap());

        /*- There are getters for the offsets of the transition */
        assertEquals(offsetBefore, z.getOffsetBefore());
        assertEquals(offsetAfter, z.getOffsetAfter());

        /*- This transition as a duration of 15 minutes */
        /*- This is a bit misleading terminology, because the transition itself happens instantaneously */
        assertEquals(Duration.ofMinutes(15L), z.getDuration());

        /*- The before and after offsets are valid for overlaps but not for gaps */
        assertFalse(z.isValidOffset(offsetBefore));
        assertFalse(z.isValidOffset(offsetAfter));

        /*- The before date time is the same as the base date*/
        assertEquals(baseDate, z.getDateTimeBefore());
        /*- The after date time is the base date modified by the offset difference */
        assertEquals(baseDate.plusMinutes(15L), z.getDateTimeAfter());

        /*- The instant is the moment at which the transition occurs, which is the before date without the before offset,
         * or equivalently the after date without the after offset
         */
        Instant instant = z.getInstant();
        assertEquals("2020-01-02T09:00:45Z", instant.toString());
        assertEquals(baseDate.toInstant(offsetBefore), z.getInstant());
        assertEquals(instant.getEpochSecond(), z.toEpochSecond());
    }

    @Test
    public void testOverlap() {

        /*- This is an example of an overlap */

        LocalDateTime baseDate = LocalDateTime.of(
                LocalDate.of(2020, Month.JANUARY, 2),
                LocalTime.of(9, 30, 45)
        );
        assertEquals("2020-01-02T09:30:45", baseDate.toString());

        ZoneOffset offsetBefore = ZoneOffset.ofHoursMinutesSeconds(0, 45, 0);
        assertEquals("+00:45", offsetBefore.toString());

        ZoneOffset offsetAfter = ZoneOffset.ofHoursMinutesSeconds(0, 30, 0);
        assertEquals("+00:30", offsetAfter.toString());

        ZoneOffsetTransition z = ZoneOffsetTransition.of(baseDate, offsetBefore, offsetAfter);
        assertEquals("Transition[Overlap at 2020-01-02T09:30:45+00:45 to +00:30]",
                z.toString());

        assertTrue(z.isOverlap());
        assertFalse(z.isGap());

        assertEquals(offsetAfter, z.getOffsetAfter());
        assertEquals(offsetBefore, z.getOffsetBefore());

        assertEquals(Duration.ofMinutes(-15L), z.getDuration());

        /*- gap => no valid offsets */
        assertTrue(z.isValidOffset(offsetAfter));
        assertTrue(z.isValidOffset(offsetBefore));

        /*- Before date is same as base date*/
        assertEquals(baseDate, z.getDateTimeBefore());
        /*- After date is date modified by offset difference */
        assertEquals(baseDate.minusMinutes(15L), z.getDateTimeAfter());

        /*- Instant at which the transition occurs */
        Instant instant = z.getInstant();
        assertEquals("2020-01-02T08:45:45Z", instant.toString());
        assertEquals(baseDate.toInstant(offsetBefore), z.getInstant());
        assertEquals(instant.getEpochSecond(), z.toEpochSecond());
    }
}
