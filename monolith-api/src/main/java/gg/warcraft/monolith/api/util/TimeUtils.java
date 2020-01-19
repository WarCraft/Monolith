package gg.warcraft.monolith.api.util;

import gg.warcraft.monolith.api.core.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This utility is injectable.
 * <p>
 * TimeUtils provides utility methods and exposes constants for time based operations. It also serves as a factory for
 * {@code Duration} objects.
 */
public interface TimeUtils {
    int SECONDS_PER_MINUTE = 60;
    int SECONDS_PER_HOUR = 60 * SECONDS_PER_MINUTE;
    int SECONDS_PER_DAY = 24 * SECONDS_PER_HOUR;

    int TICKS_PER_SECOND = 20;
    int TICKS_PER_MINUTE = 60 * TICKS_PER_SECOND;
    int TICKS_PER_HOUR = 60 * TICKS_PER_MINUTE;
    int TICKS_PER_DAY = 24 * TICKS_PER_HOUR;

    int MILLIS_PER_TICK = 50;
    int MILLIS_PER_SECOND = 1000;
    int MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    int MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    int MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    /**
     * @return The local server time. Never null.
     */
    LocalTime getServerTime();

    /**
     * @return The local server date. Never null.
     */
    LocalDate getServerDate();

    /**
     * @return The local server date time. Never null.
     */
    LocalDateTime getServerDateTime();

    /**
     * @param unixTimestamp The unix timestamp in milliseconds.
     * @return The time elapsed since the unix timestamp in a human readable format. Never null or empty.
     */
    String getTimeElapsedSince(long unixTimestamp);

    /**
     * @param unixTimestamp The unix timestamp in milliseconds.
     * @return The time to go until the unix timestamp in a human readable format. Never null or empty.
     */
    String getTimeToGoUntil(long unixTimestamp);

    /**
     * @param duration The duration. Can not be null.
     * @return A formatted string representing the duration as seen on digital displays. Never null or empty.
     */
    String getDigitalDisplay(Duration duration);
}
