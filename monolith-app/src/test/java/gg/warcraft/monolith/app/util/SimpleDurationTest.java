package gg.warcraft.monolith.app.util;

import gg.warcraft.monolith.api.core.Duration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleDurationTest {

    @Test
    public void inMillis_shouldCorrectlyConvert() {
        int three = 3;

        Duration threeMillis = new SimpleDuration(Duration.Type.MILLIS, three);
        assertEquals(3, threeMillis.inMillis());

        Duration threeTicks = new SimpleDuration(Duration.Type.TICKS, three);
        assertEquals(150, threeTicks.inMillis());

        Duration threeSeconds = new SimpleDuration(Duration.Type.SECONDS, three);
        assertEquals(3000, threeSeconds.inMillis());
    }

    @Test
    public void inTicks_shouldCorrectlyConvert() {
        int sixty = 60;

        Duration sixtyMillis = new SimpleDuration(Duration.Type.MILLIS, sixty);
        assertEquals(1, sixtyMillis.inTicks());

        Duration sixtyTicks = new SimpleDuration(Duration.Type.TICKS, sixty);
        assertEquals(60, sixtyTicks.inTicks());

        Duration sixtySeconds = new SimpleDuration(Duration.Type.SECONDS, sixty);
        assertEquals(1200, sixtySeconds.inTicks());
    }

    @Test
    public void inSeconds_shouldCorrectlyConvert() {
        int twoThousand = 2000;

        Duration twoThousandMillis = new SimpleDuration(Duration.Type.MILLIS, twoThousand);
        assertEquals(2, twoThousandMillis.inSeconds());

        Duration twoThousandTicks = new SimpleDuration(Duration.Type.TICKS, twoThousand);
        assertEquals(100, twoThousandTicks.inSeconds());

        Duration twoThousandSeconds = new SimpleDuration(Duration.Type.SECONDS, twoThousand);
        assertEquals(2000, twoThousandSeconds.inSeconds());
    }
}
