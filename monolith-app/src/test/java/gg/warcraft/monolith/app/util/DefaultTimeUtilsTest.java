package gg.warcraft.monolith.app.util;

import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.util.TimeUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultTimeUtilsTest {
    private DefaultTimeUtils defaultTimeUtils;

    @Before
    public void beforeEach() {
        defaultTimeUtils = new DefaultTimeUtils();
    }

    @Test
    public void shouldCalculateElapsedTime() {
        long currentTimeMillis = System.currentTimeMillis();

        long threeSecondsAgoTimestamp = currentTimeMillis - 3 * TimeUtils.MILLIS_PER_SECOND;
        String lessThanAMinute = defaultTimeUtils.getTimeElapsedSince(threeSecondsAgoTimestamp);
        assertEquals("less than a minute", lessThanAMinute);

        long sixtyFiveSecondsAgoTimestamp = currentTimeMillis - 65 * TimeUtils.MILLIS_PER_SECOND;
        String oneMinute = defaultTimeUtils.getTimeElapsedSince(sixtyFiveSecondsAgoTimestamp);
        assertEquals("1 minute", oneMinute);

        long oneHundredThirtySecondsAgoTimestamp = currentTimeMillis - 130 * TimeUtils.MILLIS_PER_SECOND;
        String twoMinutes = defaultTimeUtils.getTimeElapsedSince(oneHundredThirtySecondsAgoTimestamp);
        assertEquals("2 minutes", twoMinutes);

        long sixtyFiveMinutesAgoTimestamp = currentTimeMillis - 65 * TimeUtils.MILLIS_PER_MINUTE;
        String oneHour = defaultTimeUtils.getTimeElapsedSince(sixtyFiveMinutesAgoTimestamp);
        assertEquals("1 hour", oneHour);

        long oneHundredThirtyMinutesAgoTimestamp = currentTimeMillis - 130 * TimeUtils.MILLIS_PER_MINUTE;
        String twoHours = defaultTimeUtils.getTimeElapsedSince(oneHundredThirtyMinutesAgoTimestamp);
        assertEquals("2 hours", twoHours);

        long thirtyHoursAgoTimestamp = currentTimeMillis - 30 * TimeUtils.MILLIS_PER_HOUR;
        String oneDay = defaultTimeUtils.getTimeElapsedSince(thirtyHoursAgoTimestamp);
        assertEquals("1 day", oneDay);

        long oneHundredTwentyEightHoursAgoTimestamp = currentTimeMillis - 128 * TimeUtils.MILLIS_PER_HOUR;
        String fiveDays = defaultTimeUtils.getTimeElapsedSince(oneHundredTwentyEightHoursAgoTimestamp);
        assertEquals("5 days", fiveDays);
    }

    @Test
    public void shouldCalculateDigitalDisplay() {
        Duration mockZeroSeconds = mock(Duration.class);
        when(mockZeroSeconds.inSeconds()).thenReturn(0);
        String zeroSecondsDisplay = defaultTimeUtils.getDigitalDisplay(mockZeroSeconds);
        assertEquals("00:00", zeroSecondsDisplay);

        Duration mockFiveMinutes = mock(Duration.class);
        when(mockFiveMinutes.inSeconds()).thenReturn(300);
        String fiveMinutesDisplay = defaultTimeUtils.getDigitalDisplay(mockFiveMinutes);
        assertEquals("05:00", fiveMinutesDisplay);

        Duration mockTwelveMinutesThreeSeconds = mock(Duration.class);
        when(mockTwelveMinutesThreeSeconds.inSeconds()).thenReturn(723);
        String twelveMinutesThreeSecondsDisplay = defaultTimeUtils.getDigitalDisplay(mockTwelveMinutesThreeSeconds);
        assertEquals("12:03", twelveMinutesThreeSecondsDisplay);

        Duration mockThreeHoursFourMinutesTwentySixSeconds = mock(Duration.class);
        when(mockThreeHoursFourMinutesTwentySixSeconds.inSeconds()).thenReturn(11066);
        String threeHoursFourMinutesTwentySixSecondsDisplay =
                defaultTimeUtils.getDigitalDisplay(mockThreeHoursFourMinutesTwentySixSeconds);
        assertEquals("03:04:26", threeHoursFourMinutesTwentySixSecondsDisplay);
    }
}
