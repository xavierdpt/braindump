package test.java.time;

import adapters.java.time.TemporalAccessorAdapter;
import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.Test;
import test.TestUtil;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ZoneIdTest {

    @Test
    public void test1() {

        /*- System zone ids */
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        if (TestUtil.verbose()) {
            System.out.println("Available zone ids:");
            zoneIds.stream()
                    .sorted()
                    .map(x -> "- " + x)
                    .forEach(System.out::println);
        }
        assertThat(zoneIds, hasItem("Europe/Paris"));

    }

    @Test
    public void test2() {

        /*- Set default zone id to have reproducible results */
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));

        /*- Obtain system default zone id */
        ZoneId defaultZoneId = ZoneId.systemDefault();
        assertEquals("Europe/Paris", defaultZoneId.getId());
    }

    @Test
    public void test3() {

        /*- Get ZoneId by ID */
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        assertEquals("Europe/Paris", zoneId.getId());
    }

    @Test
    public void test4() {

        /*- Get ZoneId by alias */
        ZoneId zoneId = ZoneId.of("MyZone", new HashMap<String, String>() {{
            put("MyZone", "Europe/Paris");
        }});
        assertEquals("Europe/Paris", zoneId.getId());
    }

    @Test
    public void test5() {

        /*- Create zone ids from zone offsets */

        {
            ZoneId zoneId = ZoneId.ofOffset("UTC",
                    ZoneOffset.ofTotalSeconds(1));
            assertEquals("UTC+00:00:01", zoneId.getId());
        }
        {
            ZoneId zoneId = ZoneId.ofOffset("",
                    ZoneOffset.ofTotalSeconds(1));
            assertEquals("+00:00:01", zoneId.getId());
        }

    }

    @Test
    public void test6() {

        /*- Create zone ids from temporal accessor */

        class MyTemporalAccessor extends TemporalAccessorAdapter {
            @Override
            public <R> R query(TemporalQuery<R> query) {
                if (query == TemporalQueries.zone()) {
                    return (R) ZoneId.of("Europe/Paris");
                } else {
                    return null;
                }
            }
        }

        ZoneId zoneId = ZoneId.from(new MyTemporalAccessor());
        assertEquals("Europe/Paris", zoneId.getId());
    }

    @Test
    public void test7() {

        /*- Display name */

        ZoneId zoneId = ZoneId.of("Europe/Paris");
        Locale rootLocale = Locale.ROOT;
        assertEquals("Central European Time", zoneId.getDisplayName(TextStyle.FULL, rootLocale));
        assertEquals("Central European Time", zoneId.getDisplayName(TextStyle.FULL_STANDALONE, rootLocale));
        assertEquals("Europe/Paris", zoneId.getDisplayName(TextStyle.NARROW, rootLocale));
        assertEquals("CET", zoneId.getDisplayName(TextStyle.NARROW_STANDALONE, rootLocale));
        assertEquals("CET", zoneId.getDisplayName(TextStyle.SHORT, rootLocale));
        assertEquals("CET", zoneId.getDisplayName(TextStyle.SHORT_STANDALONE, rootLocale));
    }

    @Test
    public void test8() {
        /*- Normalize a zone id by returning the offset for Instant.EPOCH when the offset is fixed */
        /*- Return the same zone id otherwise */
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        assertEquals(zoneId, zoneId.normalized());
    }

    @Test
    public void test9() {
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        assertEquals("Europe/Paris", zoneId.toString());
    }
}
