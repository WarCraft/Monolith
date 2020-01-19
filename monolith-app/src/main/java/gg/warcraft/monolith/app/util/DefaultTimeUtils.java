package gg.warcraft.monolith.app.util;

import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.util.TimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DefaultTimeUtils implements TimeUtils {
    // TODO move into configuration file
    private static final ZoneId TIME_ZONE = ZoneId.of("America/New_York");

    @Override
    public LocalTime getServerTime() {
        return LocalTime.now(TIME_ZONE);
    }

    @Override
    public LocalDate getServerDate() {
        return LocalDate.now(TIME_ZONE);
    }

    @Override
    public LocalDateTime getServerDateTime() {
        return LocalDateTime.now(TIME_ZONE);
    }

    String getReadableAge(long age) {
        if (age < MILLIS_PER_MINUTE) {
            return "less than a minute";
        } else if (age < MILLIS_PER_HOUR) {
            long count = age / MILLIS_PER_MINUTE;
            return count + (count != 1 ? " minutes" : " minute");
        } else if (age < MILLIS_PER_DAY) {
            long count = age / MILLIS_PER_HOUR;
            return count + (count != 1 ? " hours" : " hour");
        } else {
            long count = age / MILLIS_PER_DAY;
            return count + (count != 1 ? " days" : " day");
        }
    }

    @Override
    public String getTimeElapsedSince(long unixTimestamp) {
        long age = System.currentTimeMillis() - unixTimestamp;
        return getReadableAge(age);
    }

    @Override
    public String getTimeToGoUntil(long unixTimestamp) {
        long current = System.currentTimeMillis();
        long age = unixTimestamp - current;
        return getReadableAge(age);
    }

    @Override
    public String getDigitalDisplay(Duration duration) {
        int durationInSeconds = duration.inSeconds();
        int hours = durationInSeconds / SECONDS_PER_HOUR;
        int durationInSecondsMinusHours = durationInSeconds % SECONDS_PER_HOUR;
        int minutes = durationInSecondsMinusHours / SECONDS_PER_MINUTE;
        int seconds = durationInSecondsMinusHours % SECONDS_PER_MINUTE;

        String display = "";
        if (hours != 0) {
            if (hours < 10) {
                display += "0";
            }
            display += hours + ":";
        }
        if (minutes < 10) {
            display += "0";
        }
        display += minutes + ":";
        if (seconds < 10) {
            display += "0";
        }
        display += seconds;
        return display;
    }
}
